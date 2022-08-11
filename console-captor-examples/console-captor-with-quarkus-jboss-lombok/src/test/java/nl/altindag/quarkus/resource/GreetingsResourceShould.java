package nl.altindag.quarkus.resource;

import io.quarkus.test.junit.QuarkusTest;
import nl.altindag.console.ConsoleCaptor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class GreetingsResourceShould {

    @Test
    void successfullyCapturesLogs() {
        try(ConsoleCaptor consoleCaptor = new ConsoleCaptor()) {

            new GreetingResource().hello();

            List<String> standardOutput = consoleCaptor.getStandardOutput();
            assertThat(standardOutput).isNotEmpty();
            assertTrue(standardOutput.stream().anyMatch(message -> message.contains("Saying hello from the logger!")));
        }
    }

    @Test
    void successfullyCapturesLogsAlternativeWhileUsingRestAssured() {
        try(ConsoleCaptor consoleCaptor = new ConsoleCaptor()) {

            given()
                    .when().get("/hello")
                    .then()
                    .statusCode(200)
                    .body(is("Hello from RESTEasy Reactive"));

            List<String> standardOutput = consoleCaptor.getStandardOutput();
            assertThat(standardOutput).isNotEmpty();
            assertTrue(standardOutput.stream().anyMatch(message -> message.contains("Saying hello from the logger!")));
        }
    }

}
