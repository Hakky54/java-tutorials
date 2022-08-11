package nl.altindag.quarkus.resource;

import io.quarkus.test.junit.QuarkusTest;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class GreetingsResourceWithQuarkusTestShould {

    @Test
    void capturingLogFailsBecauseOfDifferentClassloadersFromQuarkusTest() {
        assertThatThrownBy(LogCaptor::forRoot)
                .isInstanceOf(IllegalArgumentException.class)
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
