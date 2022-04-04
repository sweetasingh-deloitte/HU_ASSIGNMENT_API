package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import xlsInfoData.XlsInfoData;

import static io.restassured.RestAssured.given;

public class PaginationTest {

    private  static final String LOG_FILE = "log4j.properties";

    // TO ADD LOGS IN OUR PROGRAM
    private static Logger log  = LogManager.getLogger(PaginationTest.class);

    @Test(priority=4)
    public void testPagination2()
    {
        //log.info("Validating paging for limit 2");
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

        Response limit = request.given().get("/task?limit=2");
        limit.prettyPrint();

        System.out.println("paging for 2 is successful");


    }
    @Test(priority=5)
    public void testPagination5()
    {
        //log.info("Validating paging for limit 5");

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

        Response limit = request.given().get("/task?limit=5");
        limit.prettyPrint();

        System.out.println("paging for 5 is successful");


    }
    @Test(priority=6)
    public void testPagination10()
    {
        //log.info("Validating paging for limit 10");

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

        Response limit = request.given().get("/task?limit=10");
        limit.prettyPrint();

        System.out.println("paging for 10 is successful");


    }

}
