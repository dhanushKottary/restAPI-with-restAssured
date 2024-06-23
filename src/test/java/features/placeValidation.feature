Feature: Validating place API's

@AddPlaceAPI @AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" using "POST" http request
    Then The API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"
    
Examples: 
    |  name | language | address |    
    |House A| English  | Street 1|
    |House B| Kannada  | Street 2|
    
 
@DeletePlaceAPI @Regression
Scenario: Verify if deletePlace functionality is working
    Given deletePlace payload
    When user calls "DeletePlaceAPI" using "POST" http request
    Then The API call is successful with status code 200 
    And "status" in response body is "OK"   