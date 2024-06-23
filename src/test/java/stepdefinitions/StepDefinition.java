package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;

public class StepDefinition extends Utils{
	
	static ResponseSpecification resSpec;
	RequestSpecification reqResponse;
	Response resResponse;
	TestDataBuild td = new TestDataBuild();
	static String placeId;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
        
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqResponse = given().log().all().spec(requestSpecification()).body(td.addPlacePayload(name,language,address));
        
        //System.out.println(response);
	}
	
	@When("user calls {string} using {string} http request")
	public void user_calls_using_http_request(String resource, String requestName) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		if(requestName.equalsIgnoreCase("POST")) {
			resResponse = reqResponse.when().post(resourceAPI.getResource())
					.then().log().all().spec(resSpec).extract().response();
		}
		else if(requestName.equalsIgnoreCase("GET")) {
			resResponse = reqResponse.when().get(resourceAPI.getResource())
					.then().log().all().spec(resSpec).extract().response();
		}
		
	}
	@Then("The API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer int1) {
		assertEquals(resResponse.getStatusCode(), 200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		assertEquals(getJsonPath(resResponse, key), value);
	}
	
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
		
		placeId = getJsonPath(resResponse, "place_id");
		reqResponse = given().log().all().spec(requestSpecification()).queryParam("place_id",placeId);
		user_calls_using_http_request(resource, "GET");
		String actualName = getJsonPath(resResponse, "name");
		assertEquals(actualName, name);
	}
	
	@Given("deletePlace payload")
	public void delete_place_payload() throws IOException {
	    
		reqResponse = given().log().all().spec(requestSpecification()).body(td.deletePlacePayload(placeId));
	}


}
