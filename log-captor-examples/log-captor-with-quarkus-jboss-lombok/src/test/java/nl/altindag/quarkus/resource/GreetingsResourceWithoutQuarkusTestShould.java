package nl.altindag.quarkus.resource;

import nl.altindag.log.LogCaptor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GreetingsResourceWithoutQuarkusTestShould {

    @Test
    void sayHello() {
        LogCaptor logCaptor = LogCaptor.forClass(GreetingResource.class);

        new GreetingResource().hello();

        Assertions.assertThat(logCaptor.getLogs()).contains("Saying hello from the logger!");
    }

}
