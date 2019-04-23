package jdk.util.proxy.staticproxy;

public class HumanImpl implements Humen,Animal {

    @Override
    public void eat(String food) {

        System.out.println("Class HumanImpl:eat " + food);
    }

    public void additionalMethod() {

        System.out.println("additionalMethod!!");

    }

    @Override
    public String doing(String something) {
        return something;
    }

    @Override
    public void drink(String something) {
        System.out.println("Class HumanImpl:drink " + something);
    }

    @Override
    public String toString() {
        return "HumanImpl toString method is invoked !";
    }
}
