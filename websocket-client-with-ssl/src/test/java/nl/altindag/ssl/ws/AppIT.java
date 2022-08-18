package nl.altindag.ssl.ws;

import nl.altindag.console.ConsoleCaptor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppIT {

    @Test
    void shouldReceiveAResponseFromTheServerWithSsl() throws Exception {
        try(ConsoleCaptor consoleCaptor = new ConsoleCaptor()) {
            App.main(null);

            assertThat(consoleCaptor.getStandardOutput())
                    .contains(
                            "connected",
                            "Received the following message from the server: Hello there!",
                            "closed"
                    );

            assertThat(consoleCaptor.getErrorOutput()).doesNotContain("got error");
        }
    }

}
