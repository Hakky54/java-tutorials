package nl.altindag.tutorials.mockstatics;

public class Dog implements Animal {

    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
