package restAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithJson {
	@Test
	public void PostCall() {
		
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification request = RestAssured.given();
		
		JSONObject PostBody = new JSONObject();
		PostBody.put("name", "Praveen");
		PostBody.put("salary", "3000");
		
        Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON)
        		                                                 .body(PostBody.toString())
        		                                                 .post("employees/create");
        
        String ResponseBody = response.body().asString();
        System.out.println(ResponseBody);
        
       // =========Assertion======================
        
        int ActResponse = response.statusCode();
		int ExResponse = 201;
		AssertJUnit.assertEquals(ActResponse, ExResponse);
		JsonPath jpath = response.jsonPath();
		
        String Names = jpath.get("name");
		

		String Exnmae = "Praveen";
		
		AssertJUnit.assertEquals(Names, Exnmae);
		
		
	}

}
