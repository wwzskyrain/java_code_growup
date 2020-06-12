package jdk.util.concurrent.lock.spin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author erik.wang
 * @date 2020-06-12 23:38
 */
public class McsLockThread extends Thread {

    private final Logger logger = LoggerFactory.getLogger(MCSLock.class);

    MCSLock mcsLock;

    public McsLockThread(MCSLock mcsLock) {
        this.mcsLock = mcsLock;
    }


    @Override
    public void run() {
        MCSLock.MCSNode currentMcsNode = new MCSLock.MCSNode();
        logger.info("request lock");
        mcsLock.lock(currentMcsNode);
        logger.info("do something...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("do something over and unlock ...");
        mcsLock.unlock(currentMcsNode);

    }

}
