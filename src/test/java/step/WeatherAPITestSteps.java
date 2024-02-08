package step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherAPITestSteps {
    private Response response;
    private final String APIKey = "19b878ebc82648559cc202845243001";

    @Given("Установили базовый URL {string}")
    public void setAPIEndpoint(String url) {
        RestAssured.baseURI = url;
    }

    @When("Запросили текущую погоду для города {string}")
    public void requestCurrentWeather(String city) {
        response = given()
                .when()
                .get("/current.json?key=" + APIKey + "&q=" + city)
                .then()
                .extract().response();
    }

    @Then("^Проверили температуру в градусах Цельсия (\\d+.\\d+)$")
    public void verifyTemperature(float expectedTemp) {
        Float actualTemp = response.jsonPath().get("current.temp_c");
        assertEquals(expectedTemp, actualTemp);
    }

    @When("Сделали запрос с невалидным путем {string}")
    public void requestCurrentWeatherWithInvalidPath(String path) {
        response = given()
                .when()
                .get("/current.json?key=" + APIKey + path)
                .then()
                .extract().response();
        response.prettyPrint();
    }

    @Then("Проверили получение ошибки {int}")
    public void проверилиПолучениеОшибкиStatusCode(Integer expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }
}
