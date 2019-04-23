package jdk.util.proxy.cglibproxy;

import jdk.util.proxy.staticproxy.Humen;

public class HumanImpl implements Humen {

    @Override
    public void eat(String food) {
        System.out.println("eat " + food);
    }

    public void additionalMethod() {

        System.out.println("additionalMethod!!");

    }

    @Override
    public void drink(String something) {

    }

    @Override
    public String toString() {
        return "HumanImpl toString method is invoked !";
    }
}
