package xlsInfoData;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XlsInfoData {

    public static String getData(String sSheetName, String sTestCaseID) {

        String sJson = null;
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        XSSFRow row = null;
        boolean bFlag = false;
        String path = null;
        try {
            //path = System.getProperty("user.dir") + "C:\\Users\\sweetasingh\\IdeaProjects\\Main_Assigment_API\\TestData\\TestData.xlsx";
            File file = new File("C:\\Users\\sweetasingh\\IdeaProjects\\Main_Assigment_API\\TestData\\TestData.xlsx");
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sSheetName);
            row = sheet.getRow(0);
            for (int head = 0; head < row.getLastCellNum(); head++) {
                String headValue = row.getCell(head).getStringCellValue();
                if (headValue.equals("Test Case ID")) {
                    int icell = row.getCell(head).getColumnIndex();
                    int rowsCount = sheet.getLastRowNum();
                    for (int row1 = 1; row1 <= rowsCount; row1++) {
                        String cellName = sheet.getRow(row1).getCell(icell).getStringCellValue();
                        if (!cellName.isEmpty()) {
                            if (cellName.equals(sTestCaseID)) {
                                for (int head1 = 0; head1 < row.getLastCellNum(); head1++) {
                                    String headValue1 = row.getCell(head1).getStringCellValue();
                                    if (headValue1.equals("Json")) {
                                        sJson = sheet.getRow(row1).getCell(head1).getStringCellValue();
                                        bFlag = true;
                                    }
                                }
                                if (bFlag == true) {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sJson;
    }
}
    /*public static String createUserJson(String sSheetName,String sTestCaseID)
    {
        String job=null;
        String sJson=null;
        String jSon=null;
        try
        {
            sJson=getData(sSheetName,sTestCaseID);
            jSon =sJson.replaceAll();
            job=jSon.replaceAll();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return job;
    }*/


