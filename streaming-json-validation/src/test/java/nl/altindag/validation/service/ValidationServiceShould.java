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

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ValidationServiceShould {

    @Test
    void validate() {
        var validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

        var objectMapper = new ObjectMapper();
        var validationService = new ValidationService(validator, objectMapper);

        URL url = Thread.currentThread().getContextClassLoader().getResource("example.json");
        assertThat(url).isNotNull();
        assertThatCode(() -> validationService.validate(url)).doesNotThrowAnyException();
    }

}
