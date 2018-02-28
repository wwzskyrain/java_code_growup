package io;
// 比 anonymous inner class更简洁的是Lambda表达式

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList4 {
  public static void main(final String[] args) {
    File path = new File(".");
    String[] list;
    if(args.length == 0)
      list = path.list();
    else
    list=path.list((file,name) -> Pattern.compile(args[0]).matcher(name).matches());

    Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
    for(String dirItem : list)
      System.out.println(dirItem);
  }
} /* Output:
DirectoryDemo.java
DirList.java
DirList2.java
DirList3.java
*///:~
