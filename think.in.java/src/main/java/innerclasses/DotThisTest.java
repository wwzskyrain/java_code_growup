package innerclasses;

public class DotThisTest {

    public static void main(String[] args) {

        DotThis dotThis = new DotThis();

        DotThis.Inner inner = dotThis.inner();

        DotThis dotThis1 = inner.outer();



    }

}
