package nl.altindag.tutorials.mockstatics;

public class AnimalUtils {

    private AnimalUtils() {
    }

    public static Dog getGermanShepherd() {
        return new Dog("german shepherd");
    }

    public static Dog getKangal() {
        return new Dog("kangal");
    }

    public static Animal getAnimal() {
        return getGermanShepherd();
    }

}
