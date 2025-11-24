# Trust Me 🔐
A proof-of-concept GUI for prompting an user when a certificate is not trusted yet. The ssl configuration will be reloaded during runtime.

This GUI app demonstrates the feature of [Trusting additional new certificates at runtime](https://github.com/Hakky54/ayza?tab=readme-ov-file#trust-additional-new-certificates-at-runtime) from the library [ayza](https://github.com/Hakky54/ayza)
It might occur that your truststore has outdated certificates and is not easy to maintain or it just calls servers which has recently updated their certificates. 
This option demonstrates how to integrate it in your GUI app, and it will prompt when the certificate is not trusted yet, which gives the option to the end-user to either trust or reject it.

## Demo
![alt text](https://github.com/Hakky54/java-tutorials/blob/main/trust-me/images/demo.gif?raw=true)

## Running locally

### Minimum requirements
- JDK 21
- Maven
- Terminal

Although this project requires JDK 21, the [library](https://github.com/Hakky54/ayza) itself is compatible with JDK 8 and therefor will work with that version.

Run the following commands in your terminal:

```bash
mvn clean package
mvn spring-boot:run
```

## Contributing

There are plenty of ways to contribute to this project:

* Give it a star
* Submit a PR
