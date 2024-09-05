# Instant SSL Reloading 🔐
A server configured with ssl has a key material and trust material. These materials are generated from a keypair and certificate which always have an expiration date.
In a traditional server configuration a reboot of the server is required to apply the latest key material and trust material changes when the keystore and truststore are changed.
A downtime is therefore unavoidable. This project demonstrates with a basic setup how update the server certificate from an external source without the need of restarting your server. In this way you can achieve zero downtime.

The repository contains:
 - Server, based on Vert.x

### SSL Updating entrypoint for the server:
The server has currently one way to update the existing ssl material:
- File based aka file change listener, see here for the implementation: [FilesBasedSslUpdateService](src/main/java/nl/altindag/server/service/FileBasedSslUpdateService.java)

- REST, This option is hosted in a separate module within this repository, see here: [AdminController]((https://github.com/Hakky54/java-tutorials/blob/main/instant-server-ssl-reloading/server/src/main/java/nl/altindag/server/controller/AdminController.java)
- Databased based, aka database change listener. This option is hosted in a separate module within this repository, see here: [Instant SSL Reloading With Database](https://github.com/Hakky54/java-tutorials/tree/main/instant-ssl-reloading-with-spring-jetty-database)

#### Requirements
 - Java 17
 - Terminal

#### Configure SSL Update Service
The server will check every minute for changes on the ssl material, in this case the `identity.jks` and `truststore.jks`. You will need to specify where the service should look for these files. Please change the properties below in [FileBasedSslUpdateService](src/main/java/nl/altindag/server/service/FileBasedSslUpdateService.java).
```java
private static final Path identityPath = Path.of("/path/to/your/identity.jks");
private static final Path trustStorePath = Path.of("/path/to/your/truststore.jks");
private static final char[] identityPassword = "secret".toCharArray();
private static final char[] trustStorePassword = "secret".toCharArray();
```

#### Start the server
1. run `mvn clean install` on the [root directory of this repository](https://github.com/Hakky54/java-tutorials/)
2. run `mvn exec:java` on the [vertx-server maven module](.)

Visit the server with the following url on your browser: https://localhost:8443/
Open the certificate details in your browser by clicking on the lock logo (on Chrome). You will see a similar certificate detail as shown below:

![alt text](https://github.com/Hakky54/java-tutorials/blob/main/instant-server-ssl-reloading-with-vertx/vertx-server/images/before-reloading.png?raw=true)

Please note down the expiration date. Afterwords you will compare it when you have run the admin application.

#### Refresh the server certificates
Wait for one minute and if the keystores are found and have been updated, then the ssl configuration will be updated.
Refresh your browser tab and open the certificate details again and compare the expiration date with the one you have noted down.
You should have a similar certificate detail as shown below:

![alt text](https://github.com/Hakky54/java-tutorials/blob/main/instant-server-ssl-reloading-with-vertx/vertx-server/images/after-reloading.png?raw=true)