package jdk.util.concurrent.family.common;

public class Plate {
    Fruit fruit;

    public Plate(Fruit fruit) {
        this.fruit = fruit;
    }

    public boolean isEmpty() {
        return fruit == null;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }
}