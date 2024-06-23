package resources;

import java.util.Arrays;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	
	public AddPlace addPlacePayload(String name, String language, String address) {
		
		AddPlace ap = new AddPlace();
		Location location = new Location();
		String[] types = {"shoe park", "shoe"};
		location.setLat(-38.383494);
		location.setLng(33.427362);
		ap.setLocation(location);
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress(address);
		ap.setWebsite("http://www.dhanushprabhakarkottary.com");
		ap.setLanguage(language);
		ap.setTypes(Arrays.asList(types));
		return ap;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
	}
}
