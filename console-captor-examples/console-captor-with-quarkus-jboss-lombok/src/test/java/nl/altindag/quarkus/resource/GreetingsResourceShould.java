/*
 * Copyright 2022 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
