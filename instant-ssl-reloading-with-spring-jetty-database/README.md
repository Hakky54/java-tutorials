# Instant SSL Reloading 🔐
A server configured with ssl has a key material and trust material. These materials are generated from a keypair and certificate which always have an expiration date.
In a traditional server configuration a reboot of the server is required to apply the latest key material and trust material changes when the keystore and truststore are changed.
A downtime is therefore unavoidable. This project demonstrates with a basic setup how update the server certificate from an external source without the need of restarting your server. In this way you can achieve zero downtime.

The repository contains:
 - Server, based on Spring Boot with Jetty as a server engine
 - PostgreSQL DB

The server will be initially configured with dummy ssl material. The dummy ssl material will be replaced during the startup.
It will be calling the database every 10 seconds to get the ssl material. The ssl material are in pem format.

### SSL Updating entrypoint for the server:
 - [DatabaseBasedSslUpdateService](src/main/java/nl/altindag/server/service/DatabaseBasedSslUpdateService.java)

#### Requirements
 - Java 11
 - Terminal
 - Docker

#### Start the database
1. run `mvn clean install` on the [root directory of this repository](https://github.com/Hakky54/java-tutorials/)
2. run `mvn exec:java` on the [instant-server-ssl-reloading-with-spring-jetty-database](.)

#### Start the server
```
mvn spring-boot:run
```
Visit the server with the following url on your browser: https://localhost:8443/api/hello to view the certificate. It will be updated every 10 seconds, but this logic can be enhanced by validating if the content has been updated on the database before updating the server ssl configuration.
