package nl.altindag.spring.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FooService {

    private static final Logger LOGGER = LogManager.getLogger(FooService.class);

    public void hello() {
        LOGGER.info("Hello there friend!");
    }

}
