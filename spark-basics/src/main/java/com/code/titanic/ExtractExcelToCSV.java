package com.code.titanic;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExtractExcelToCSV {

	public static void convertExcelToCsv() throws IOException,
			EncryptedDocumentException, InvalidFormatException {

		File file = new File("newFile.txt");
		PrintStream stream = new PrintStream(file);
		Workbook wb = WorkbookFactory.create(new File("titanic3.xls"));
		DataFormatter fmt = new DataFormatter();
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			for (Row row : sheet) {
				for (int j = 0; j < 14; j++) {
					Cell cell = row.getCell(j, Row.RETURN_BLANK_AS_NULL);
					if (cell != null) {
						String value = fmt.formatCellValue(cell);
						if (!value.trim().isEmpty()) {
							stream.print(value + "|");
						}
					} else {
						stream.print("|");
					}
				}
				stream.println("");
			}

		}
		DelLstLine();
	}

	public static void DelLstLine() {
		try {
			RandomAccessFile raf = new RandomAccessFile("newFile.txt", "rw");
			long length = raf.length();
			raf.setLength(length - 16);
			raf.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/*
	 * public static void main(String[] args) throws EncryptedDocumentException,
	 * InvalidFormatException { try { convertExcelToCsv(); } catch (IOException
	 * e) { e.printStackTrace(); } }
	 */
}