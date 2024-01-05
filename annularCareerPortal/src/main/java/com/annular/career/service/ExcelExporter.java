package com.annular.career.service;
//
//import java.io.IOException;
//import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Row;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.annular.career.model.Candidate;
//import com.annular.career.repository.CandidateRepository;
//
//import jakarta.servlet.ServletOutputStream;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class ExcelExporter {
//	
//	@Autowired
//	private static CandidateRepository canRepo;
//
//	public static void export (HttpServletResponse response) throws IOException {
//		List<Candidate> candidate= canRepo.getAllCandidate();
//
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet sheet = workbook.createSheet("candidate Info");
//		HSSFRow row = sheet.createRow(0);
//		Row headerRow = sheet.createRow(0);
//		row.createCell(0).setCellValue("Candidate ID");
//		row.createCell(1).setCellValue("First Name");
//		row.createCell(2).setCellValue("Last Name");
//		row.createCell(3).setCellValue("Phone Number");
//		
//		int dataRowIndex = 1;
//		
//		for(Candidate candidates:candidate) {
//			HSSFRow dataRow = sheet.createRow(dataRowIndex);
//			dataRow.createCell(0).setCellValue(candidates.getCandId());
//			dataRow.createCell(1).setCellValue(candidates.getFirstName());
//			
//			dataRow.createCell(2).setCellValue(candidates.getLastName());
//			dataRow.createCell(3).setCellValue(candidates.getPhoneNo());
//			dataRowIndex++;
//		}
//		
//		
//		
////		headerRow.createCell(4).setCellValue("Email");
////		headerRow.createCell(5).setCellValue("Educational Qualification");
////		headerRow.createCell(6).setCellValue("Key Skills");
////		headerRow.createCell(7).setCellValue("Position");
////		headerRow.createCell(8).setCellValue("Experience");
////		headerRow.createCell(9).setCellValue("Passout Year");
////		headerRow.createCell(10).setCellValue("Current CTC");
////		headerRow.createCell(11).setCellValue("Expected CTC");
////		headerRow.createCell(12).setCellValue("Notice Period");
//		
//		
//		
//
//		// Create data rows
////		int rowNum = 1;
////		for (Candidate candidate : candidates) {
////			HSSFRow row = sheet.createRow(rowNum++);
////			row.createCell(0).setCellValue(candidate.getFirstName());
////			row.createCell(1).setCellValue(candidate.getMiddleName());
////			row.createCell(2).setCellValue(candidate.getLastName());
////			row.createCell(3).setCellValue(candidate.getPhoneNo());
////			row.createCell(4).setCellValue(candidate.getEmail());
////			row.createCell(5).setCellValue(candidate.getEducationalQual());
////			row.createCell(6).setCellValue(candidate.getKeySkills());
////			row.createCell(7).setCellValue(candidate.getPosition());
////			row.createCell(8).setCellValue(candidate.getExperience());
////			row.createCell(9).setCellValue(candidate.getPassout());
////			row.createCell(10).setCellValue(candidate.getCctc());
////			row.createCell(11).setCellValue(candidate.getEctc());
////			row.createCell(12).setCellValue(candidate.getNoticePeriod());
////		}
////
////		// Set response headers
////		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
////		response.setHeader("Content-Disposition", "attachment; filename=candidates.xlsx");
////
////		// Write to response output stream
//		ServletOutputStream ops = response.getOutputStream();
//		workbook.write(ops);
//		workbook.close();
//		ops.close();
//		
//	}
//}
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.annular.career.model.Candidate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ExcelExporter {

    @GetMapping("/download-excel")
    public ResponseEntity<byte[]> downloadExcel() throws IOException {
        // Create a new Excel workbook and sheet

        HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("candidate Info");
		HSSFRow row = sheet.createRow(0);
		Row headerRow = sheet.createRow(0);
		row.createCell(0).setCellValue("Candidate ID");
		row.createCell(1).setCellValue("First Name");
		row.createCell(2).setCellValue("Last Name");
		row.createCell(3).setCellValue("Phone Number");
        
        // Write the workbook to a ByteArrayOutputStream
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);

        // Set response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "sample.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(stream.toByteArray());
    }

	
}