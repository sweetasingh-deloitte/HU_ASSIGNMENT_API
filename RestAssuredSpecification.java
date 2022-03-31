import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;


import java.io.File;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredSpecification {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void setup() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("").
                addHeader("Content-Type", "application/json");
        requestSpecification = with().spec(requestSpecBuilder.build());

        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON);
        responseSpecification = specBuilder.build();


    }

    @Test(priority = 1)
    public void test_GET() {

        RequestSpecification requestSpecification = with().
                baseUri("https://jsonplaceholder.typicode.com").
                header("Content-Type", "application/json");
        Response response = requestSpecification.get("/posts");

        int statusCode = response.getStatusCode();
        System.out.println("Status code for get request is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
        responseSpecification.body("userId[39]", equalTo(4));
        String s1;
        int k = 1;
        JSONArray j = new JSONArray(response.asString());
        for (int l = 0; l < j.length(); l++) {
            Object o = j.getJSONObject(l).get("title");
            if (!(o instanceof String)) {
                k = 0;
                break;
            }
        }
        assertThat(k,is(equalTo(1)));

    }

    @Test(priority = 2)
    public void test_PUT() {
        File file = new File("src//test//resources//putCall.json");
        requestSpecification = RestAssured.with().
                baseUri("https://reqres.in/api").
                header("Content-Type", "application.json").body(file);
        Response response = requestSpecification.post("/users");
        assertThat(response.statusCode(), equalTo(201));
        responseSpecification.body("name", equalTo("Arun")).body("job", equalTo("Manager"));
    }
}
