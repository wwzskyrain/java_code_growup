package jdk.util.proxy.reflect;

public class Person implements Behave {

    @Override
    public void eat(String something) {
        System.out.println("Person eating "+ something);
    }

    @Override
    public void run(Integer meters) {

        System.out.println("Person has run "+ meters +" m.");
    }
}
