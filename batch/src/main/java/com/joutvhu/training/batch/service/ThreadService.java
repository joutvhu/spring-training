package com.joutvhu.training.batch.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
@Service
public class ThreadService {
    private final Object lock1 = new Object();
    private final Lock lock2 = new ReentrantLock();
    private final Object lock3 = new Object();
    private final Object lock4 = new Object();
    private volatile Integer x = 0;

    public void execute() {
        log.debug("Start ThreadService");
        Thread thread1 = new Thread(this::testThread1);
        thread1.start();

        Thread thread2 = new Thread(this::testThread2);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("ThreadService 0 = " + x);
        log.debug("End ThreadService");
    }

    public void testThread1() {
        log.debug("Start testThread1");
        for (int i = 0; i < 100; i++) {
            log.debug("testThread1 " + i);
            synchronized (lock1) {
                int v = x;
                try {
                    Thread.sleep(9);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                v--;
                log.debug("testThread1 set x = " + v);
                x = v;
            }
        }
        this.testThread3(1);
        this.testThread4();
        log.debug("End testThread1");
    }

    public void testThread2() {
        log.debug("Start testThread2");
        for (int i = 0; i < 100; i++) {
            log.debug("testThread2 " + i);
            synchronized (lock1) {
                int v = x;
                try {
                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                v++;
                log.debug("testThread1 set x = " + v);
                x = v;
            }
        }
        this.testThread3(2);
        this.testThread4();
        log.debug("End testThread2");
    }

    public synchronized void testThread3(int k) {
        log.debug("Start testThread3 " + k);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("End testThread3 " + k);
    }

    public void testThread4() {
        try {
            while (!lock2.tryLock());
            log.debug("Start testThread4");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("End testThread4");
        } finally {
            lock2.unlock();
        }
        log.debug("End testThread4");
    }

    public void deadlock1() {
        log.debug("Start deadlock1");
        synchronized (lock3) {
            log.debug("lock3 looked");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock4) {
                log.debug("lock4 looked");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        log.debug("End deadlock1");
    }

    public void deadlock2() {
        log.debug("Start deadlock2");
        synchronized (lock4) {
            log.debug("lock4 looked");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock3) {
                log.debug("lock3 looked");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        log.debug("End deadlock2");
    }
}
