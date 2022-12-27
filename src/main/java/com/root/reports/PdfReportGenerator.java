package com.root.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.root.binding.response.InsurancePlanResponse;

public class PdfReportGenerator {

	public void exportReport(List<InsurancePlanResponse> plans, HttpServletResponse response)
			throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("List of Plans", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 1.5f, 1.8f, 2.5f, 1.8f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		  Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		  font1.setColor(Color.WHITE);
		 

		cell.setPhrase(new Phrase("Plan Id", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Holder Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font1));
		table.addCell(cell);

		for (InsurancePlanResponse insResponse : plans) {
			table.addCell(String.valueOf(insResponse.getPlanId()));
			table.addCell(insResponse.getPlanName());
			table.addCell(insResponse.getPlanHolderName());
			table.addCell(insResponse.getPlanHolderSsn().toString());
			table.addCell(String.valueOf(insResponse.getPlanStatus()));
		}

		document.add(table);
		document.close();
	}

	
	/*
	 * public static ByteArrayInputStream exportReport(List<InsurancePlanResponse>
	 * plans) {
	 * 
	 * Document document = new Document(); ByteArrayOutputStream out = new
	 * ByteArrayOutputStream();
	 * 
	 * try {
	 * 
	 * PdfPTable table = new PdfPTable(5); table.setWidthPercentage(60);
	 * table.setWidths(new int[]{3, 3, 3, 5, 5});
	 * 
	 * Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	 * 
	 * PdfPCell hcell; hcell = new PdfPCell(new Phrase("Plan Id", headFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Plan Name", headFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Plan Holder Name", headFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Plan Holder SSN", headFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Plan Status", headFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
	 * 
	 * for (InsurancePlanResponse response : plans) {
	 * 
	 * PdfPCell cell;
	 * 
	 * cell = new PdfPCell(new Phrase(response.getPlanId().toString()));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase(response.getPlanName()));
	 * //cell.setPaddingLeft(5); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT); table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase(response.getPlanHolderName())); //
	 * cell.setPaddingLeft(5); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT); table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase(String.valueOf(response.getPlanHolderSsn())));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_RIGHT); cell.setPaddingRight(5);
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase(response.getPlanStatus()));
	 * //cell.setPaddingLeft(5); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT); table.addCell(cell); }
	 * 
	 * PdfWriter.getInstance(document, out); document.open(); document.add(table);
	 * 
	 * document.close();
	 * 
	 * } catch (DocumentException ex) {
	 * 
	 * ex.printStackTrace(); }
	 * 
	 * return new ByteArrayInputStream(out.toByteArray()); }
	 */
}
