package innerclasses;//: innerclasses/TestParcel.java

class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    protected class PDestination implements Destination {
        private String label;

        private PDestination(String whereTo) {
            label = whereTo;
        }

        public String readLabel() {
            return label;
        }
    }

    public Destination destination(String s) {
        return new PDestination(s);
    }

    public PContents contents() {
        return new PContents();
    }
}

public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();

        Destination d = p.destination("Tasmania");

        // Illegal -- can't access private class:
        //! Parcel4.PContents pc = p.new PContents();

//       总结:
//        1.不能用私有内部类的构造函数
//        2.不能用内部私有类来声明引用类型。

//       so. 只能用接口类接受内部类实例PContents了
        Contents contents = p.contents();
    }
} ///:~
