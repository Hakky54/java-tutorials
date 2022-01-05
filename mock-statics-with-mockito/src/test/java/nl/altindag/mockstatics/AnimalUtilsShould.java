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
