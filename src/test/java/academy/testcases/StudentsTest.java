package academy.testcases;

import academy.pojo.LoginPojo;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StudentsTest {
    RequestSpecification requestSpecification;
    @BeforeClass
    public void setRequestSpec(){
         requestSpecification = given()
                .baseUri("https://todo.qacart.com")
                .log().all();
    }
    @Test
    public void loginTests(){
        /**String body = "{\n" +
                "    \"email\" : \"hatem@example.com\",\n" +
               "    \"password\" : \"123456\"\n" +
                "}";*/
        //File bodyFile = new File("src/test/resources/loginData.json");

        /**HashMap<String,String> bodyData = new HashMap<>();
        bodyData.put("email", "hatem@example.com");
        bodyData.put("password" , "123456");*/

        /**LoginPojo body = new LoginPojo();
        body.setEmail("hatem@example.com");
        body.setPassword("123456");**/

        LoginPojo body = new LoginPojo("hatem@example.com" , "123456");

            given()
                    .spec(requestSpecification)
                    .contentType(ContentType.JSON)
                    .body(body)

                    .when()
                    .post("/api/v1/Students/login")
                    .then()
                    .log().all()
                    .assertThat().statusCode(200);



    }
    
    @Test
    public void urlencodedTest (){
        HashMap<String,String> formParamData = new HashMap<>();
        formParamData.put("email", "hatem@example.com");
        formParamData.put("password" , "123456");


        given()
                .spec(requestSpecification)
                .contentType(ContentType.URLENC)
                .formParams(formParamData)
                .when()
                .post("/api/v1/Students/login")
                .then()
                .log().all()
                .assertThat().statusCode(200);
        
    }

    @Test
    public void formDataTest (){

        File file = new File("src/test/resources/loginData.json");

        given()
                .spec(requestSpecification)
                .contentType(ContentType.MULTIPART)
                //.multiPart("file",file)
                .when()
                .post("/api/v1/Students/login")
                .then()
                .log().all()
                .assertThat().statusCode(200);

    }


}
