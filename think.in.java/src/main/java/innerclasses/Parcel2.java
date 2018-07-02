package innerclasses;//: innerclasses/Parcel2.java
// Returning a reference to an inner class.

public class Parcel2 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("Tasmania");
        Parcel2 q = new Parcel2();
        // Defining references to inner classes:
        Contents c = q.contents();



//      1.能不能通过c来访问外部类实例q呢？-- no，不能
        Destination d = q.to("Borneo");

        Contents contents = q.new Contents();




//        new Parcel2.Contents();
//        2.    报错：innerclasses.Parcel2.this cannont be referenced from a static context.
//        可见，内部类有点"实例变量"的意思。

    }
} /* Output:
Tasmania
*///:~
