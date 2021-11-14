
package Steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class GetRandomFactFromFacts extends BaseSteps {
    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("I send a get request for facts")
    public void I_send_a_get_request() {
        String API = "facts/";
        response =
                given().
                        when().
                        get(API).
                        then().
                        extract().response();
    }

    @Then("I assert that a random fact from facts has text")
    public void iAssertARandomFactHasText() {

        //Create array of objects
        JsonPath responseBody = response.jsonPath();
        List<Object>  list = responseBody.getList("$");
        JSONArray jsonArray=new JSONArray(list);

        //Get a random fact
        int rand= getRandomNumber(jsonArray.length());
        JSONObject jsonObject = jsonArray.getJSONObject(rand);

        //log the chosen fact
        String payload = jsonObject.toString();
        payload = prettyPrintJSON(payload);
        scenario.log("The Response body of chosen fact \n");
        scenario.log(payload);

        //Assert that the text property is not empty
        String TextValue = jsonObject.get("text").toString().toString();
        Assert.assertTrue(!TextValue.isEmpty());

    }

    private int getRandomNumber(int upper) {
        Random rand = new Random(); //instance of random class
        int upperbound = upper;

        int int_random = rand.nextInt(upperbound);
         return int_random;
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

