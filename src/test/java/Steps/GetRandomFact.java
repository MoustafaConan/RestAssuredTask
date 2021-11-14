package Steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

//GetRandomFact
public class GetRandomFact extends BaseSteps {

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("I Entered the URL")
    public void I_Entered_the_end_point() {
        RestAssured.baseURI = "https://cat-fact.herokuapp.com/";
        RestAssured.defaultParser = Parser.JSON;
    }

    @When("I send a get request")
    public void I_send_a_get_request() {
        String API = "facts/random/";
        
        //Get response
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

       //Attaching the response body to the report
        String payload = responseBody.get().toString();
        payload = prettyPrintJSON(payload);
        scenario.log("The Response body is \n");
        scenario.log(payload);

        //Assert that the chosen cat text property isn't empty
        String textValue = responseBody.get("text");
        Assertions.assertTrue(!textValue.isEmpty());

    }

    //print a pretty json
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
