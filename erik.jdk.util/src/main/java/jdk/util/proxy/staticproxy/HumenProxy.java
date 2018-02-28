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

        System.out.println("cook");

    }

    private void after() {
        System.out.println("after eat, sweep");
    }


    public static void main(String[] args) {

        HumenProxy humenProxy = new HumenProxy();

        humenProxy.eat("rice");

    }

}
