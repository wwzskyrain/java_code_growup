package jdk.util.extend;

/**
 * @author erik.wang
 * @date 2020-04-11 21:37
 */
public class Cat extends Animal {

    @Override
    public void run() {
        System.out.println("Cat is run...");
    }

    public static void main(String[] args) {


        Animal animal = new Cat();

        animal.run();

        System.out.println("-----------------");

        animal.start();

    }
}
