package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
    
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        
        String path = ".//testData//OpenCartTestData.xlsx";
        
        ExcelUtility xlutility = new ExcelUtility(path);
        
        int totalRows = xlutility.getRowCount("sheet1");
        int totalCols = xlutility.getCellCount("sheet1", 1);
        
        String[][] loginData = new String[totalRows][totalCols];
        
        for (int r = 1; r <= totalRows; r++) { // Start from row 1
            for (int c = 0; c < totalCols; c++) {
                loginData[r - 1][c] = xlutility.getCellData("sheet1", r, c);
            }
        }
        return loginData;
    }
}
