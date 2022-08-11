package nl.altindag.jboss;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class FooService {

    public void hello() {
        log.info("Hello there friend!");
    }

}
