package com.root.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;

import com.root.binding.response.InsurancePlanResponse;
@Configuration
public class ExcelReportGenerator {
	

	public void export(List<InsurancePlanResponse> plans, HttpServletResponse response) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Plans");
		XSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Plan_Id");
		headerRow.createCell(1).setCellValue("Plan_Name");
		headerRow.createCell(2).setCellValue("Plan_Holder_Name");
		headerRow.createCell(3).setCellValue("Plan_Holder_Ssn");
		headerRow.createCell(4).setCellValue("Plan_Status");

		for (int i = 0; i < plans.size(); i++) {
			XSSFRow row = sheet.createRow(i + 1);

			row.createCell(0).setCellValue(plans.get(i).getPlanId());
			row.createCell(1).setCellValue(plans.get(i).getPlanName());
			row.createCell(2).setCellValue(plans.get(i).getPlanHolderName());
			row.createCell(3).setCellValue(plans.get(i).getPlanHolderSsn());
			row.createCell(4).setCellValue(plans.get(i).getPlanStatus());
		}
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
