package innerclasses;//: innerclasses/InheritInner.java
// Inheriting an inner class.

class WithInner {
    class Inner {
    }
}

public class InheritInner extends WithInner.Inner {

//    InheritInner() {} //any constructor will not compile successfully unless it suppler a enclosingClassReference.super().
//每一个构造器，都要显示指明：通过该构造器创建出的这个继承类实例  所隐式引用的外围类的实例。
        InheritInner(WithInner wi) {
        wi.super();
    }



    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
} ///:~
