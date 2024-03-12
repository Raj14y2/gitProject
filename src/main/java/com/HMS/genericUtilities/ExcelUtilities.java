package com.HMS.genericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.experimental.theories.DataPoints;
import org.testng.annotations.DataProvider;


public class ExcelUtilities
{
	/**
	 *
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static String readExcelData(String sheet,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis= new FileInputStream(ipathConstants.ExcelPath);
		Workbook book= WorkbookFactory.create(fis);
		Sheet sh= book.getSheet(sheet);
		String value = sh.getRow(row).getCell(cell).getStringCellValue();
		return value;
	}

	public static int LastRowCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(ipathConstants.ExcelPath);
		Workbook book= WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheet);
		int lastRowNum = sh.getLastRowNum();
		return lastRowNum;
	}
	public static int LastCellCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(ipathConstants.ExcelPath);
		Workbook book= WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheet);
		int lastCellNum = sh.getRow(0).getLastCellNum();
		return lastCellNum;
	}


	public static void readMultipleExcelData(String sheet,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(ipathConstants.ExcelPath);
		Workbook book= WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheet);
		int lastRowCount = ExcelUtilities.LastRowCount(sheet);
		int cellcount = ExcelUtilities.LastCellCount(sheet);
		String cellval=null;
		for(int i=0;i<lastRowCount;i++)
		{
			for(int j=0;j<cellcount;j++)
			{
				cellval= sh.getRow(i).getCell(cell).getStringCellValue();
				System.out.println(cellval+" ");
			}
		}
	}

	public static String createDataInExcel(String sheet,int row,int cell,String val) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(ipathConstants.ExcelPath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheet);
		sh.createRow(row).createCell(cell).setCellValue(val);
		FileOutputStream fio=new FileOutputStream(ipathConstants.ExcelPath);
		book.write(fio);
		return val;

	}

	public static HashMap<String, String> readMultipledataMaps(String sheet,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(ipathConstants.ExcelPath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheet);
		int rowcount = sh.getLastRowNum();

		HashMap<String, String> h=new HashMap<String, String>();

		for(int i=0;i<rowcount;i++)
		{
			String key= sh.getRow(i).getCell(cell).getStringCellValue();
			String value=sh.getRow(i).getCell(cell+1).getStringCellValue();
			h.put(key, value);

		}
		return h;
	}



	//dataprovider  from excel

	
	public static Object[][] fetchDataExcelDp(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fi=new FileInputStream(ipathConstants.ExcelPath);
		Workbook book = WorkbookFactory.create(fi);
		Sheet sh= book.getSheet(sheet);
		int lr = sh.getLastRowNum()+1;
		short lc = sh.getRow(0).getLastCellNum();
		Object[][] obj=new Object[lr][lc];

		for(int i=0;i<lr;i++)
		{
			for(int j=0;j<lc;j++)
			{
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;

	}
}
