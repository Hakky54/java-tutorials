package nl.altindag.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FooService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooService.class);

    public void hello() {
        LOGGER.info("Hello there friend!");
    }

}
