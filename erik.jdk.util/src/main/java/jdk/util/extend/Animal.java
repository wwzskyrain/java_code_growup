package jdk.util.extend;

/**
 * @author erik.wang
 * @date 2020-04-11 21:36
 */
public class Animal {

    public void run(){
        System.out.println("Animal is run...");
    }

    public void start(){
        System.out.println("Animal is start...");
        run();
        //这里run()会调用Animal的run，也可能调用Cat等子类的run。
    }

}
