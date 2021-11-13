/*
package Steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;

import static org.junit.Assert.*;


import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class GetRandomFactFromFacts {
    Response response;
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("I Entered the URL")
    public void I_Entered_the_end_point() {
        // Write code here that turns the phrase above into concrete action
        RestAssured.baseURI = "https://cat-fact.herokuapp.com/";
        //RestAssured.defaultParser = Parser.JSON;
    }

    @When("I send a get request")
    public void I_send_a_get_request() {
        String API = "facts/random/";
        response =
                given().
                        queryParam("animal_type", "cat").
                        queryParam("amount", "1").
                when().
                get(API).
                then().
                        extract().response();
    }

    @Then("I assert that a random fact has text")
    public void iAssertARandomFactHasText() {
        JsonPath responseBody = response.jsonPath();
        List<Object>  list = responseBody.getList("$");
        JSONArray jsonArray=new JSONArray(list);
       int rand= getRandomNumber();
       JSONObject jsonObject = jsonArray.getJSONObject(rand);

        String TextValue = jsonObject.get("Text").toString().toString();
        Assert.assertTrue(!TextValue.isEmpty());

    }

    private int getRandomNumber() {
        Random rand = new Random(); //instance of random class
        int upperbound = 25;
        //generate random values from 0-24
         int int_random = rand.nextInt(upperbound);
         return int_random;
    }

    //Thanks to this guy https://stackoverflow.com/questions/4105795/pretty-print-json-in-java
    public static String prettyPrintJSON(String unformattedJsonString) {
        StringBuilder prettyJSONBuilder = new StringBuilder();
        int indentLevel = 0;
        boolean inQuote = false;
        for (char charFromUnformattedJson : unformattedJsonString.toCharArray()) {
            switch (charFromUnformattedJson) {
                case '"':
                    // switch the quoting status
                    inQuote = !inQuote;
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    break;
                case ' ':
                    // For space: ignore the space if it is not being quoted.
                    if (inQuote) {
                        prettyJSONBuilder.append(charFromUnformattedJson);
                    }
                    break;
                case '{':
                case '[':
                    // Starting a new block: increase the indent level
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    indentLevel++;
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    break;
                case '}':
                case ']':
                    // Ending a new block; decrese the indent level
                    indentLevel--;
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    ;
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    break;
                case ',':
                    // Ending a json item; create a new line after
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    if (!inQuote) {
                        appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                        appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    }
                    break;
                default:
                    prettyJSONBuilder.append(charFromUnformattedJson);
            }
        }
        return prettyJSONBuilder.toString();
    }

    private static void appendIndentedNewLine(int indentLevel, StringBuilder stringBuilder) {
        stringBuilder.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            // Assuming indention using 2 spaces
            stringBuilder.append("  ");
        }
    }

}
*/
