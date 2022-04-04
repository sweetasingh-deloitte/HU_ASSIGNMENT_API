package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import xlsInfoData.XlsInfoData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NegativeTest {

    private  static final String LOG_FILE = "log4j.properties";

    // TO ADD LOGGING IN OUR PROGRAM
    private static Logger log  = LogManager.getLogger(NegativeTest.class);
    @Test(priority = 7)
    public void existingUserRegister() {
        log.info("Registering same user to verify the error");
        Response response = given()
                .auth()
                .basic("email", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(XlsInfoData.getData("Sheet1", "TC001"))
                .post("/user/register");
        String jsonString = response.getBody().asString();


        assertThat(jsonString, jsonString.contains("E11000 duplicate key error collection"));
        assertThat(response.statusCode(), equalTo(400));
    }

    @Test(priority = 8)
    public void nonExistingUserLogin() {
        log.info("Registering with non existing user");
        Response response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(XlsInfoData.getData("Sheet1", "TC003"))
                .post("/user/login");
        String jsonString = response.getBody().asString();
        assertThat(jsonString, equalTo("\"Unable to login\""));
        assertThat(response.statusCode(), equalTo(400));
    }

    @Test(priority = 9)
    public void wrongRequestBody() {
        log.info("Logging in with the wrong request body");
        Response response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(XlsInfoData.getData("Sheet1", "TC004"))
                .post("/user/login");
        String jsonString = response.getBody().asString();
        assertThat(response.statusCode(), equalTo(400));
    }
}
