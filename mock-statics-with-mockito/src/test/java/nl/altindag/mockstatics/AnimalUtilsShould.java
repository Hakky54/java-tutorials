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
package nl.altindag.mockstatics;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalUtilsShould {

    @Test
    void animalUtilsTest() {
        Animal kangal = AnimalUtils.getKangal();
        try (MockedStatic<AnimalUtils> mockedAnimalUtils = Mockito.mockStatic(AnimalUtils.class, invocation -> {
            Method method = invocation.getMethod();
            if ("getAnimal".equals(method.getName())) {
                return invocation.callRealMethod();
            } else if ("getGermanShepherd".equals(method.getName())) {
                return kangal;
            } else {
                return invocation.getMock();
            }
        })) {
            Animal animal = AnimalUtils.getAnimal();
            assertThat(animal.getName()).isEqualTo("kangal");
        }
    }

}
