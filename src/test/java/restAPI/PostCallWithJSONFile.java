package restAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCallWithJSONFile {
	@Test
	public void PostCall() throws IOException {
		
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification request = RestAssured.given();
		
		//JSONObject PostBody = new JSONObject();
		//PostBody.put("name", "Praveen");
		//PostBody.put("salary", "3000");
		
		String JsonBody = ReadJsonFile("data.json");
		
        Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON)
        		                                                 .body(JsonBody)
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
	
	public String ReadJsonFile(String FileName) throws IOException {
		String data = new String(Files.readAllBytes(Paths.get(FileName)));
		return data;
	}

}
