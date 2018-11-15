package jdk.util.proxy.staticproxy;

public class HumenImpl implements Humen,Animal {

    @Override
    public void eat(String food) {

        System.out.println("Class HumenImpl:eat " + food);
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
        System.out.println("Class HumenImpl:drink " + something);
    }

    @Override
    public String toString() {
        return "HumenImpl toString method is invoked !";
    }
}
