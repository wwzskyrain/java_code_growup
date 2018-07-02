package jdk.util.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {


    public static void main(String[] args) {

        try {
            for (Method method : Main.class
                    .getClassLoader()
                    .loadClass(("jdk.util.annotations.AnnotationExample"))
                    .getMethods()) {

                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(MethodInfo.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {

                            System.out.printf("method = %s and methodAnnotation = %s \n",method,anno);
                        }
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("Method with revision no 1 = " + method);
                        }

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
