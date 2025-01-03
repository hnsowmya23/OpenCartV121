package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	String path;
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook; 
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		
		fi=new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet =workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;	
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellCount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
		 data = formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File xlfile= new File(path);
		if(!xlfile.exists()) {
			fo=new FileOutputStream(path);//Creates an empty Excel file.
			workbook=new XSSFWorkbook();//Creates a new Excel workbook in memory
			workbook.write(fo);//Writes the empty workbook to the file system
		}
		fi= new FileInputStream(path);// Opens the Excel file for reading.
		workbook= new XSSFWorkbook(fi);//Loads the workbook from the Excel file into memory.
		
		if(workbook.getSheetIndex(sheetName)==-1) {
			workbook.createSheet(sheetName);
			workbook.getSheet(sheetName);
		
		
		if(sheet.getRow(rowNum)==null) {
			sheet.createRow(rowNum);
			sheet.getRow(rowNum);
			
			cell=row.createCell(colNum);
			cell.setCellValue(data);
			fo= new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		}
		}
	}
	
	public void fileGreenColors(String sheetName, int rowNum, int colNum) throws IOException {
		fi= new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();	
	}
	
	public void fileRedColors(String sheetName, int rowNum, int colNum) throws IOException {
		fi= new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();	
	}

}
