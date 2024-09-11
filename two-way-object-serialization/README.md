# Two-way object serialization while using one model with Jackson and Spring Boot
Some projects may have requirements for different types of serialization. A server needs to accept and respond with camel case objects and needs to send a different cased objects to a downstream API.
A straight forward approach would be having objects defined for every casing, in this case Employee for the RestController and EmployeeDao for the downstream api, but that would lead to more maintenance.
Having duplicate code in the code base is bad and maintaining the changes is error-prone.    
It would be better to have a single object called Employee which can be used to serialize as camel-case and upper-snake-case or any other casing strategy. In this way the developer just only needs to maintain one model.

The flow would be like the diagram below:

![alt text](https://github.com/Hakky54/java-tutorials/blob/main/two-way-object-serialization/images/flow.png?raw=true)

#### Requirements
 - Java 21
 - Terminal

#### Start the server
```
mvn spring-boot:run
```

#### Save an object
Send a POST request to http://localhost:8080/api/employee with the following body:
```json
{
  "id": 1,
  "name": "Peter",
  "surname": "Jackson",
  "startDate": "2021-16-12T15:34:31"
}
```
Analyse the server logs, it will print the serialized object for the downstream api like the example below:
```json
{
  "ID": 1,
  "NAME": "Peter",
  "SURNAME": "Jackson",
  "START_DATE": "2021-16-12T15:34:31"
}
```

#### Get the object
Send a GET request to http://localhost:8080/api/employee It will return the following response:
```json
{
  "id": 1,
  "name": "Peter",
  "surname": "Jackson",
  "startDate": "2021-16-12T15:34:31"
}
```
