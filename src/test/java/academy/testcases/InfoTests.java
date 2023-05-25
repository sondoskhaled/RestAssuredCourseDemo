package academy.testcases;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class InfoTests {
    @Test
    public void getCoursesInfoTests(){
        HashMap<String,String> headersInfo = new HashMap<>();
        headersInfo.put("type" , "WEB");
        headersInfo.put("language" , "JAVA");

        Header typeHeader = new Header("type" , "WEB");
        Header languageHeader = new Header("language" , "JAVA");
        Headers infoHeaders = new Headers(typeHeader , languageHeader);
        given()
                .baseUri("https://todo.qacart.com")
                //.header("language","JAVA")
                //.header(typeHeader)
                //.headers(infoHeaders)
                .headers(headersInfo)
                .log().all()
        .when()
                .get("/api/v1/Info/courses")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("count" , equalTo(1));
    }

    @Test
    public void getLecturesInfoTests(){

        HashMap <String,String> query = new HashMap<>();
        query.put("type","PAID");
        query.put("mode" , "VIDEO");

        given()
                .baseUri("https://todo.qacart.com")
                //.queryParam("type","PAID")
                //.queryParams("type","PAID" , "mode" , "VIDEO")
                .queryParams(query)
                .log().all()
                .when()
                .get("/api/v1/Info/lectures")
                .then()
                .log().all()
                .assertThat().statusCode(200);

    }
}
