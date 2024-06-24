package datadrivenfromexcel;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetDataFromExcel {

	static Object[] arr;
	public static void main(String[] args) throws InvalidFormatException, IOException {
		
		File file = new File(System.getProperty("user.dir")+"\\ExcelData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
        XSSFSheet sheet = workBook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		Row headerRow = rows.next();
		int testcaseColumn = 0;
		for(int i=0; i<headerRow.getPhysicalNumberOfCells();i++) {
			Cell column = headerRow.getCell(i);
			if(column.getStringCellValue().equalsIgnoreCase("Testcases")) {
				testcaseColumn = i;
				break;
			}
		}

		while(rows.hasNext()) {
			Row row = rows.next();
			if(row.getCell(testcaseColumn).getStringCellValue().equalsIgnoreCase("Purchase")) {
				for(int i=1; i<row.getPhysicalNumberOfCells(); i++) {
					arr = storeDataInArray(row.getPhysicalNumberOfCells()-1, row);
					System.out.println("Value "+i+" :"+row.getCell(i).getStringCellValue());
					
				}
				break;
			}
		}
		System.out.println("Data in the array: ");
		for(int i=0; i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		
	}
	
	public static Object[] storeDataInArray(int cols, Row row) {
		Object[] arr = new Object[cols];
		Iterator<Cell> cells = row.cellIterator();
		cells.next();
		int k=0;
		while(cells.hasNext()) {
			Cell cell = cells.next();
			String cellVal = cell.getStringCellValue();
			arr[k] = cellVal;
			k++;
		}
		
		return arr;
	}

}
