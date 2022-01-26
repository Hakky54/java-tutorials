package nl.altindag.server.controller;

import nl.altindag.server.aspect.AdditionalCertificateValidations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @AdditionalCertificateValidations(allowedCommonNames = {"black-hole"}, notAllowedCommonNames = {})
    @GetMapping(value = "/hello", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

}
