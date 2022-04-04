package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.log4j.Priority;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.logging.log4j.Logger;


import org.testng.annotations.Test;
import xlsInfoData.XlsInfoData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import static io.restassured.RestAssured.given;


public class AddTaksTest {

    private  static final String LOG_FILE = "log4j.properties";

    // TO ADD LOGGING IN OUR PROGRAM
    private static Logger log  = LogManager.getLogger(AddTaksTest.class);

    @Test(priority=3)
    public void addTasks() throws IOException {


        log.info("Adding 20 Tasks");

        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request = RestAssured.given();
        Response response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(XlsInfoData.getData("Sheet1", "TC002"))
                .post("/user/login");

        String jsonString = response.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");

        File file1 = new File("C:\\Users\\sweetasingh\\IdeaProjects\\Main_Assigment_API\\TestData\\Tasks.xls");
        FileInputStream inputStream = new FileInputStream(file1);
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = wb.getSheetAt(0);
        int rowCount = sheet1.getLastRowNum();
        for (int i = 0; i <= rowCount; i++) {
            HSSFRow row1 = sheet1.getRow(i);
            HSSFCell cell1 = row1.getCell(0);
            String task = cell1.getStringCellValue();
            Response addUser = request.body(task).post("/task");
            String resultString = addUser.getBody().asString();
            addUser.prettyPrint();

            log.info("task added");

            System.out.println(response.asString());
        }
    }
}