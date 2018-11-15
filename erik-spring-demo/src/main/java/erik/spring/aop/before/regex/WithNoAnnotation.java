package erik.spring.aop.before.regex;

public class WithNoAnnotation {

    public void test(){
        System.out.println("WithNoAnnotation+test()...");

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        StackTraceElement stackTraceElement0 = stackTraceElements[1];
        for (int i = 0; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            System.out.printf("%d:className=%s,fileName=%s,lineNumber=%s,method=%s\n",
                    i,element.getClassName(),element.getFileName(),element.getLineNumber(),element.getMethodName());
        }
        System.out.println(stackTraceElements);
    }


}
