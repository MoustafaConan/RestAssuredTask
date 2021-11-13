package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.Random;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

// Ctrl Alt L format code
// Ctrl Alt S settings
// Ctrl Shift F10 run code
public class GetRandomFact {
    Response response;

    @Given("I Entered the URL")
    public void I_Entered_the_end_point() {
        // Write code here that turns the phrase above into concrete action
        RestAssured.baseURI = "https://cat-fact.herokuapp.com/";
        RestAssured.defaultParser = Parser.JSON;
    }

    @When("I send a get request")
    public void I_send_a_get_request() {
        String API = "facts/random/";
        response = given().
                    queryParam("animal_type", "cat").
                    queryParam("amount", "1").
                when().
                    get(API).
                then().
                    extract().response();
    }


    @Then("I assert that a random fact has text")
    public void iAssertARandomFactHasText() {
        JsonPath responseBody = response.then().extract().jsonPath();
        String textValue = responseBody.get("text");
        System.out.println(textValue);

        Assertions.assertTrue(!textValue.isEmpty());

    }

}