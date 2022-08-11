package nl.altindag.jboss;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FooServiceShould {

    @Test
    void sayHello() {
        LogCaptor logCaptor = LogCaptor.forClass(FooService.class);

        FooService fooService = new FooService();
        fooService.hello();

        assertThat(logCaptor.getLogs())
                .hasSize(1)
                .contains("Hello there friend!");
    }

}
