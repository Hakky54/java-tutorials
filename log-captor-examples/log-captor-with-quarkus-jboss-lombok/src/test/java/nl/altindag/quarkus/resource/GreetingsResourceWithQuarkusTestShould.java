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
import nl.altindag.log.LogCaptor;
import nl.altindag.log.exception.LogCaptorException;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class GreetingsResourceWithQuarkusTestShould {

    @Test
    void capturingLogFailsBecauseOfDifferentClassloadersFromQuarkusTest() {
        assertThatThrownBy(LogCaptor::forRoot)
                .isInstanceOf(LogCaptorException.class)
                .hasMessageContaining("Multiple classloaders are being used. " +
                        "The Logging API is created by the following classloader: [jdk.internal.loader.ClassLoaders$AppClassLoader], " +
                        "while it should have been created by the following classloader: [io.quarkus.bootstrap.classloading.QuarkusClassLoader].");

        given()
            .when().get("/hello")
            .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }

}
