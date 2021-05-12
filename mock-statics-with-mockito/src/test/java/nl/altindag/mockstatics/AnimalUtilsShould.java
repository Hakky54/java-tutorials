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
            } else {
                return invocation.getMock();
            }
        })) {
            mockedAnimalUtils.when(AnimalUtils::getGermanShepherd).thenReturn(kangal);
            Animal animal = AnimalUtils.getAnimal();
            assertThat(animal.getName()).isEqualTo("kangal");
        }
    }

}
