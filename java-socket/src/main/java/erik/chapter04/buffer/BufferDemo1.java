package erik.chapter04.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo1 {

    public static void main(String[] args) {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/nali/project_erik/java_code_growup/java-socket/src/main/java/erik/chapter04/buffer/BufferDemo1.java", "r")) {

            FileChannel fileChannel = randomAccessFile.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            while (fileChannel.read(byteBuffer) != -1) {

                byteBuffer.flip();

                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }
                byteBuffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
