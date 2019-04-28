package jdk.util.concurrent.family.syn;

import jdk.util.concurrent.family.common.Plate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author erik.wang
 * @date 2019/04/27
 **/
public class FamilyPlateGameSynVersion {

    /**
     * 这里面有很多细节。
     * 1.
     * @param args
     */
    public static void main(String[] args) {


//      无论保留【1】【2】【3】那一句，程序都可以完美运行
        Plate plate = new Plate(null);      //【1】
//        Plate plate = new Plate(new Fruit("apple"));  //【2】
//        Plate plate = new Plate(new Fruit("orange")); //【3】

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new ThreadMother(plate));
        executorService.submit(new ThreadFather(plate));
        executorService.submit(new ThreadSon(plate));
        executorService.submit(new ThreadDaughter(plate));

        System.out.println("main thread sleep");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }

    }

}
