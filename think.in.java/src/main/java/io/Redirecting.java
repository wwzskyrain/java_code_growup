package io;//: io/Redirecting.java
// Demonstrates standard I/O redirection.

import java.io.*;

public class Redirecting {
    public static void main(String[] args)
            throws IOException {
        PrintStream console = System.out; //留下一个引用,最后还要修复"重定向".
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("Redirecting.java"));
        PrintStream out = new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream("test.out")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null)
            System.out.println(s);
        out.close(); // Remember this!
        System.setOut(console);
    }
} ///:~
