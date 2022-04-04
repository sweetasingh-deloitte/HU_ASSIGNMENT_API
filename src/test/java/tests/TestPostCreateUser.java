package tests;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import xlsInfoData.XlsInfoData;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TestPostCreateUser {

    private  static final String LOG_FILE = "log4j.properties";

    // TO ADD LOGGING IN OUR PROGRAM
    private static Logger log  = LogManager.getLogger(TestPostCreateUser.class);

    ResponseSpecification responseSpecification;

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON);
        responseSpecification = responseSpecBuilder.build();
    }


    @Test(priority = 1)
    public void createUser() throws IOException {
        Response response = given()
                .auth()
                .basic("email", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(XlsInfoData.getData("Sheet1", "TC001"))
                .post("/user/register");
        assertThat(response.statusCode(), equalTo(201));
        log.info("Account registered");
        System.out.println(response.asString());
    }

    @Test(priority = 2)
    public void login() {

        Response response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(XlsInfoData.getData("Sheet1", "TC002"))
                .post("/user/login");
        assertThat(response.statusCode(), equalTo(200));
        response.prettyPrint();

        System.out.println(response.asString());
        log.info("Successfully logged in");

        //Validating Credentials
        /*JSONObject arr = new JSONObject(response.asString());
        System.out.println();
        assertThat(arr.getJSONObject("user").get("email"),equalTo(email));

        if(arr.getJSONObject("user").get("email").equals(email))
        {
            System.out.println("valid credential as email are same");
            log.info("valid credential as email are same");
        }

        else
        {
            System.out.println("invalid credential as email are not same");
            log.info("invalid credential as email are not same");
        }*/
    }

}
