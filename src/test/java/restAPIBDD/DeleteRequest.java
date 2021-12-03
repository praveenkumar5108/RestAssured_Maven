package restAPIBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteRequest {
    @Test
	public void Deletecall() {
    	
    	RestAssured.given()
        .baseUri("http://localhost:7000")
        .when()
        .delete("/employees/11")
        .then()
        .statusCode(200)
        .log()
        .all();
		
	}

}
