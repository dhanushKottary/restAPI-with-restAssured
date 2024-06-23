package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws IOException {
		
		StepDefinition sd = new StepDefinition();
		if(StepDefinition.placeId == null) {
			
			sd.add_place_payload_with("Kottary", "Tulu", "kempugudde,Neermarga");
			sd.user_calls_using_http_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Kottary", "GetPlaceAPI");
		}
	}

}
