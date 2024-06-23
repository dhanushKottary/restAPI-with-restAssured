package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec;
	
   public RequestSpecification requestSpecification() throws IOException {
	   
	   if(reqSpec == null)
	   {
	   PrintStream stream = new PrintStream(new FileOutputStream("Logging.txt"));
	   //RestAssured.baseURI = getPropertyObject().getProperty("baseUri");	
	   reqSpec =  new RequestSpecBuilder().setBaseUri(getPropertyObject().getProperty("baseUri"))
               .addHeader("Content-Type","application/json")
               .addQueryParam("key", "qaclick123")
               .addFilter(RequestLoggingFilter.logRequestTo(stream))
               .addFilter(ResponseLoggingFilter.logResponseTo(stream))
               .build();
     
	   return reqSpec;
	   }
	   return reqSpec;
   }
   
   public Properties getPropertyObject() throws IOException {
	   Properties props = new Properties();
	   FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\test.properties");
	   props.load(fis);
	   return props;
   }
   
   public String getJsonPath(Response response, String key) {
	   String resString = response.asString();
		JsonPath js = new JsonPath(resString);
		return js.get(key).toString();
   }

}
