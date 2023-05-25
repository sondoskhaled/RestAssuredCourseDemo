package academy.testcases;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CoursesTest {
    // we can put this code in @BeforeClass
    RequestSpecification requestSpecification = given()
            .baseUri("https://todo.qacart.com")
            .log().all();
    @Test
    public void getCourseDetails(){
        given()
                .spec(requestSpecification)
                //.header("Authorization","Bearer ey25jvvv")
                .auth().oauth2("ey2545hbjh") // oauth2 = bearer
                .when()
                .get("/api/v1/Courses/6319b5655ce1f40db1b73738")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
