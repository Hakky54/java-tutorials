package nl.altindag.jul;

import java.util.logging.Logger;

public class FooService {

    private static final Logger LOGGER = Logger.getLogger(FooService.class.getName());

    public void hello() {
        LOGGER.info("Hello there friend!");
    }

}
