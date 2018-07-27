package erik.chapter04.buffer;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class BufferDemo {   // 从channel(文件channel)读数据到buffer，然后从buffer中读出来数据。
    public static void main(String args[]) throws Exception {
        FileInputStream fin = new FileInputStream("/Users/nali/project_erik/java_code_growup/java-socket/src/main/java/erik/chapter04/BufferDemo.java");
        FileChannel fc = fin.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化", buffer);

        fc.read(buffer);    //read into buffer
        output("调用read()", buffer);

        buffer.flip();      //shift to get model
        output("调用flip()", buffer);

        while (buffer.remaining() > 0) {
            byte b = buffer.get();  //read（get） from buffer 。
            System.out.print(((char) b));
        }
        output("调用get()", buffer);

        buffer.clear();
        output("调用clear()", buffer);

        fin.close();
    }

    public static void output(String step, Buffer buffer) {
        System.out.println(step + " : ");
        System.out.print("capacity: " + buffer.capacity() + ", ");
        System.out.print("position: " + buffer.position() + ", ");
        System.out.println("limit: " + buffer.limit());
        System.out.println();
    }
} 