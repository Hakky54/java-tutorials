package nl.altindag.console.log4j;

import nl.altindag.console.ConsoleCaptor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FooServiceShould {

    @Test
    void sayHello() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        FooService fooService = new FooService();
        fooService.hello();

        assertThat(consoleCaptor.getStandardOutput()).hasSize(1);
        assertThat(consoleCaptor.getStandardOutput().get(0)).contains("Hello there friend!");
    }

}
