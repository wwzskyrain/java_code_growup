package innerclasses;//: innerclasses/DotThis.java
// Qualifying access to the outer-class object.

public class DotThis {
    private Integer agePrivate = new Integer(20);

    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis outer() {
            return DotThis.this;    //返回外部类实例的引用
            // A plain "this" would be Inner's "this"
        }

        public Inner returnInner() {
            return this;        //返回内部类实体的引用
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dotThis = new DotThis();
        Inner dti = dotThis.inner();
        DotThis dotThis2 = dti.outer();

        System.out.println(dotThis.agePrivate);
        System.out.println(dotThis2.agePrivate);


    }
} /* Output:
DotThis.f()
*///:~
