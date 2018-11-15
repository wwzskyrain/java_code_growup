package jdk.util.proxy.staticproxy;

public class HumenProxy implements Humen {

    private Humen humen;

    public HumenProxy() {
        humen = new HumenImpl();
    }

    @Override
    public void eat(String food) {

        before();
        humen.eat(food);
        after();

    }

    private void before() {

        System.out.println("before eat,first cook food");

    }

    private void after() {
        System.out.println("afterreturning eat, sweep");
    }

    @Override
    public void drink(String something) {
        System.out.println("Class HumenProxy :drink" + something);
    }

    public static void main(String[] args) {

        HumenProxy humenProxy = new HumenProxy();

        humenProxy.eat("rice");

    }

}
