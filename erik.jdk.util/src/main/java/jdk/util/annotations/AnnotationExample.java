package jdk.util.annotations;

public class AnnotationExample {


    @Override       //Override 是Source级别的注解，在运行时是解析不到的。
    @MethodInfo(author = "erik.wnag",comments = "FamilyPlateGameLockVersion method",date = "march 26 2018")
    public String toString() {
        return "over toString method";
    }

    @Deprecated
    @MethodInfo(author = "erik.wang",comments = "old method",date = "march 26 2018")
    public static void oldMethod(){
        System.out.println("Old method ,do not use it again!");
    }

}
