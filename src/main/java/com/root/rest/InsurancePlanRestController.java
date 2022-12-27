package com.root.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.root.binding.request.InsurancePlanRequest;
import com.root.binding.response.InsurancePlanResponse;
import com.root.reports.ExcelReportGenerator;
import com.root.reports.PdfReportGenerator;
import com.root.service.InsurancePlanService;

@RestController
public class InsurancePlanRestController {

	@Autowired
	private InsurancePlanService service;

	@Autowired
	private ExcelReportGenerator generator;

	@PostMapping("/plans")
	public ResponseEntity<List<InsurancePlanResponse>> getPlans(@RequestBody InsurancePlanRequest request) {
		List<InsurancePlanResponse> searchPlans = service.searchPlans(request);
		return new ResponseEntity<List<InsurancePlanResponse>>(searchPlans, HttpStatus.OK);
	}

	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> uniquePlanNames = service.getUniquePlanNames();
		return new ResponseEntity<List<String>>(uniquePlanNames, HttpStatus.OK);
	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanStattus() {
		List<String> uniquePlanStatus = service.getUniquePlanStatus();
		return new ResponseEntity<List<String>>(uniquePlanStatus, HttpStatus.OK);
	}

	@GetMapping("/plans/export/excel")
	public void exportIntoExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		/*
		 * List<InsurancePlanResponse> listOfResponse = service.findAllPlans();
		 * generator.export(listOfResponse,response);
		 */
		List<InsurancePlanResponse> plans = service.searchPlans(null);
		generator.export(plans, response);

	}
	
	 @GetMapping("/plans/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=plans_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List<InsurancePlanResponse> plans = service.searchPlans(null);
	         
	     PdfReportGenerator exporter=new PdfReportGenerator();
	        exporter.exportReport(plans, response);
	         
	    }
	
	

	/*
	 * @GetMapping(value = "/plans/export/pdf", produces =
	 * MediaType.APPLICATION_PDF_VALUE) public ResponseEntity<InputStreamResource>
	 * exportToPdf() {
	 * 
	 * List<InsurancePlanResponse> searchPlans = service.searchPlans(null);
	 * 
	 * ByteArrayInputStream bis = PdfReportGenerator.exportReport(searchPlans);
	 * 
	 * HttpHeaders httpHeaders = new HttpHeaders();
	 * httpHeaders.add("Content-Disposition", "inline; filename=planreport.pdf");
	 * 
	 * return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.
	 * APPLICATION_PDF) .body(new InputStreamResource(bis)); }
	 */
}
