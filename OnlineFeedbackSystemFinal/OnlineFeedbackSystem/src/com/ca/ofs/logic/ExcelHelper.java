package com.ca.ofs.logic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelHelper {

	public static Workbook getReportInExcel(ConsolidatedFeedback cf,
			String title) {
		Workbook wb = new HSSFWorkbook();
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("report.xls");
			CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet = wb.createSheet(title);

			Row row = sheet.createRow((short) 0);
			Cell cell = row.createCell(0);
			cell.setCellValue(1);

			row.createCell(1).setCellValue(1.2);
			row.createCell(2).setCellValue(
					createHelper.createRichTextString("This is a string"));
			row.createCell(3).setCellValue(true);

			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

}
