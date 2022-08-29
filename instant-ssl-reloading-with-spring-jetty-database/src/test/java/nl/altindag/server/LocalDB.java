package nl.altindag.server;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.concurrent.TimeUnit;

public class LocalDB {

    private static final int containerPort = 5432 ;
    private static final int localPort = 5432 ;
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("db/db-init.sql")
            .withExposedPorts(containerPort)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))
            ));

    public static void main(String[] args) throws InterruptedException {
        postgreSQLContainer.start();

        System.out.println("JDBC url is: " + postgreSQLContainer.getJdbcUrl());

        TimeUnit.HOURS.sleep(1);
    }


}
