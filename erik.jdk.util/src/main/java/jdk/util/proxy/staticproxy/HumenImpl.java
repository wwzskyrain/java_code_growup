package jdk.util.proxy.staticproxy;

public class HumenImpl implements Humen {

    @Override
    public void eat(String food) {
        System.out.println("eat " + food);
    }

    public void additionalMethod() {

        System.out.println("additionalMethod!!");

    }

    @Override
    public String toString() {
        return "HumenImpl toString method is invoked !";
    }
}
