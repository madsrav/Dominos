package com.dominos_generic;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	
	/*
	 * Taking data from external resource
	 */

	public String excelFile(String path,String sheetName, int row, int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(path);
		Workbook book =	WorkbookFactory.create(fis);
		String value =book.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
		book.close();
		return value ;	
	
	}
	
	
}
