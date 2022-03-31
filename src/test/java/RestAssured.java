import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class RestAssured {

    @Test
    public void test_get()
    {
        given().
                baseUri("https://jsonplaceholder.typicode.com").
        when().
                get("/posts").
        then().
                statusCode(200).
        and().
                contentType(ContentType.JSON);
        //and().
                //body("[2]", hasKey("title"));

    }

    @Test
    public void test_put()
    {
        File putJson=new File("src//test//resources//putcall.json");
        given().
                baseUri("https://reqres.in/api").
                body(putJson).
        when().
                put("/users").
        then().
                assertThat().
                statusCode(200).
        and().
                contentType(ContentType.JSON);

    }
}
