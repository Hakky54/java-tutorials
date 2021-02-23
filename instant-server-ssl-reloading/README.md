# Instant SSL Reloading 🔐
A server configured with ssl has a key material and trust material. These materials are generated from a keypair and certificate which always have an expiration date.
In a traditional server configuration a reboot of the server is required to apply the latest key material and trust material changes when the keystore and truststore are changed.
A downtime is therefore unavoidable. This project demonstrates with a basic setup how update the server certificate from an external source without the need of restarting your server. In this way you can achieve zero downtime.

The repository contains:
 - Server, based on Spring Boot with Jetty as a server engine
 - Admin, which will send a http request containing the new certificates as a keystore file

#### Requirements
 - Java 11
 - Terminal

#### Start the server
```
mvn spring-boot:run -pl server
```
Visit the server with the following url on your browser: https://localhost:8443/api/hello
Open the certificate details in your browser by clicking on the lock logo (on Chrome). You will see a similar certificate detail as shown below:

![alt text](https://github.com/Hakky54/java-tutorials/tree/main/instant-server-ssl-reloading/images/before-reloading.png?raw=true)

Please note down the expiration date. Afterwords you will compare it when you have run the admin application.

#### Refresh the server certificates
The admin will fetch the new keystores from the resource directory and will send it as a POST request to the server.
Execute the following command to apply the instant ssl reloading
```
mvn exec:java -pl admin
```
Refresh your browser tab and open the certificate details again and compare the expiration date with the one you have noted down.
You should have a similar certificate detail as shown below:

![alt text](https://github.com/Hakky54/java-tutorials/tree/main/instant-server-ssl-reloading/images/after-reloading.png?raw=true)