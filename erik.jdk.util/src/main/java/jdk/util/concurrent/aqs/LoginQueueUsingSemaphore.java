package jdk.util.concurrent.aqs;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class LoginQueueUsingSemaphore {

    private Semaphore semaphore;

    public LoginQueueUsingSemaphore(int slotLimit) {
        //同时登录人数上限
        semaphore = new Semaphore(slotLimit);
    }

    void login()  {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void logout() {
        semaphore.release();
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }


    public static class User implements Runnable {

        public String name;
        public LoginQueueUsingSemaphore loginQueue;

        public User(String name) {
            this.name = name;
        }

        public void run() {
            loginQueue.login();
            System.out.println(this.name + "登录了>>>>>>, 还有槽位：" + loginQueue.availableSlots());
            Random random = new Random(System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(20));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.name + "logout了<<<<<，还有槽位：" + loginQueue.availableSlots());
            loginQueue.logout();
        }

    }

    public static void main(String[] args) {

        LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(5);
        int count = 10;
        ExecutorService executorService
                = Executors.newFixedThreadPool(count);

        for (int i = 0; i < 10; i++) {
            User user = new User("useName-" + i);
            user.loginQueue = loginQueue;
            executorService.submit(user);
        }
        executorService.shutdown();
    }

}