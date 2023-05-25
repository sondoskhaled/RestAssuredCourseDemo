package testcases;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase {
    @Test
    public void test(){
        given().baseUri("https://6469bf3e183682d614459aef.mockapi.io/api/v1")
            .when().get("/users")
            .then().log().all()
            .assertThat().statusCode(200)
            .assertThat().body("[0].name",equalTo("Ramiro Koss"))
            .assertThat().body("name", hasItem("Jaime Shanahan"))
            .assertThat().body("name", hasItems("Colin West" , "Dr. Tyrone Wilkinson"))
            .assertThat().body("name", not(hasItems("Colin" , "Wilkinson")))
            // contains (all items in the same order)
            //containsInAnyOrder (all items in any order)
            .assertThat().body("name", not(empty()))
            .assertThat().body("name", hasSize(15))
            .assertThat().body("name.size()", equalTo(15))
            .assertThat().body("createdAt", everyItem(startsWith("2023")))
            .assertThat().body("[0]",hasKey("id"))
            .assertThat().body("[0]",hasValue("Malawi"))
            .assertThat().body("[0]",hasEntry("id","1"))

        ;

    }

    @Test
    public void extract(){
        Response res = given()
                .baseUri("https://6469bf3e183682d614459aef.mockapi.io/api/v1")
                .when()
                .get("/users")
                .then().extract().response();
        String name = res.path("[0].name");
        String name1 = JsonPath.from(res.asString()).getString("[0].name");
        String name2 = given()
                .baseUri("https://6469bf3e183682d614459aef.mockapi.io/api/v1")
                .when()
                .get("/users")
                .then().extract().response().path("[0].name");
        System.out.println(res.asString());
        System.out.println(name);
        System.out.println(name1);
        System.out.println(name2);
    }

    @Test
    public void logging(){
        given()
                .baseUri("https://6469bf3e183682d614459aef.mockapi.io/api/v1")
                .log().all() // log().all() or log().body() or log().parameters or log().method
                .when()
                .get("/users")
                .then().log().all()// log().all() or log().body() or log().status

                // log().ifError() if we need to logging only if there is an error
                // log().ifValidationFails() if we need to logging only if there is an error in validation
        ;
    }
}
