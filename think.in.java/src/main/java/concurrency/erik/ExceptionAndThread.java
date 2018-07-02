package concurrency.erik;

public class ExceptionAndThread {

    public static void main(String[] args) {

        try{
            Thread currentThread = Thread.currentThread();
            System.out.printf("main:thread name:%s is interrupted:%b\n",currentThread.getName(),currentThread.isInterrupted());

            throw new RuntimeException("from main");
        }catch (Exception e){
            Thread currentThread = Thread.currentThread();
            System.out.printf("catch:thread name:%s is interrupted:%b\n",currentThread.getName(),currentThread.isInterrupted());
            System.out.printf("catch over.");
        }

    }

}
