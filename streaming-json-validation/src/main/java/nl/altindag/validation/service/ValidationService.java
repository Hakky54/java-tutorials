/*
 * Copyright 2022 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.validation.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import nl.altindag.validation.model.GitHubRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ValidationService {

    private final Validator validator;
    private final ObjectMapper objectMapper;

    public ValidationService(Validator validator, ObjectMapper objectMapper) {
        this.validator = validator;
        this.objectMapper = objectMapper;
    }

    public void validate(URL url) {
        JsonFactory jsonFactory = objectMapper.getFactory();
        try(InputStream inputStream = url.openStream();
            JsonParser parser = jsonFactory.createParser(inputStream)) {

            Set<ConstraintViolation<GitHubRepository>> aggregatedConstraintViolations = new HashSet<>();
            do {
                JsonToken token = parser.nextToken();
                if (token == JsonToken.START_OBJECT) {
                    GitHubRepository gitHubRepository = objectMapper.readValue(parser, GitHubRepository.class);
                    Set<ConstraintViolation<GitHubRepository>> constraintViolations = validator.validate(gitHubRepository);
                    aggregatedConstraintViolations.addAll(constraintViolations);
                }
            } while (parser.currentToken() != JsonToken.END_OBJECT && parser.currentToken() != JsonToken.END_ARRAY);

            if (!aggregatedConstraintViolations.isEmpty()) {
                throw new ConstraintViolationException(aggregatedConstraintViolations);
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
