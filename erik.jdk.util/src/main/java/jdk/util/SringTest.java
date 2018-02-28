package jdk.util;

public class SringTest {


    public static void main(String[] args) {

        String data = "/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/bin:/Library/Frameworks/Python.framework/Versions/3.6/bin:/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home/bin:/Library/Frameworks/Python.framework/Versions/3.6/bin:/Library/Frameworks/Python.framework/Versions/3.6/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Frameworks/Mono.framework/Versions/Current/Commands:/Applications/Wireshark.app/Contents/MacOS:/Users/nali/Library/apache-maven-3.5.0/bin:/Users/nali/Library/apache-tomcat-8.5.16/bin:/Users/nali/Library/apache-maven-3.5.0/bin:/Users/nali/Library/apache-tomcat-8.5.16/bin::/Users/nali/Library/apache-maven-3.5.0/bin:/Users/nali/Library/apache-tomcat-8.5.16/bin:";

        String[] pathes = data.split(":");

        for(int i=0;i<pathes.length;i++){
            System.out.println(pathes[i]);
        }

    }

}
