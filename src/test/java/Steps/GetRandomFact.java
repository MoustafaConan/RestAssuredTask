package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Random;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

// Ctrl Alt L format code
// Ctrl Alt S settings
// Ctrl Shift F10 run code
public class GetRandomFact {
    Response response;

    @Given("I Entered the end point")
    public void I_Entered_the_end_point() {
        // Write code here that turns the phrase above into concrete action
        RestAssured.baseURI = "https://cat-fact.herokuapp.com/";
        RestAssured.defaultParser = Parser.JSON;
    }

    @When("I send a get request")
    public void I_send_a_get_request() {
        String API = "facts/";
        response = given().when().get(API).then().extract().response();
    }

    @Then("I assert that a random fact has text")
    public void iAssertARandomFactHasText() {
        JsonPath jsonResponse = response.jsonPath();
        List<Object> responseList = jsonResponse.getList("$");
        JSONArray jsonArray = new JSONArray(responseList);

        // Pick a random fact
        int int_random = getRandomNumber(jsonArray);
        JSONObject randomFact = jsonArray.getJSONObject(int_random);
        Assertions.assertTrue(!randomFact.getString("text").isEmpty());
    }

    private int getRandomNumber(JSONArray jsonArray) {
        Random rand = new Random(); //instance of random class
        int upperbound = jsonArray.length();
        int int_random = rand.nextInt(upperbound);
        return int_random;
    }
}