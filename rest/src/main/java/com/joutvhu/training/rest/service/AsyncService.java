package com.joutvhu.training.rest.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AsyncService {
    @Async
    public void testAsync1() {
        log.debug("Start testAsync1");
//        this.testAsync2();
//        this.testAsync3();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1000; j++);
            log.debug("testAsync1 " + i);
        }
        log.debug("End testAsync1");
    }

    @Async
    public void testAsync2() {
        log.debug("Start testAsync2");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 1000; j++);
            log.debug("testAsync2 " + i);
        }
        log.debug("End testAsync2");
    }

    @Async
    public void testAsync3() {
        log.debug("Start testAsync3");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1000; j++);
            log.debug("testAsync3 " + i);
        }
        log.debug("End testAsync3");
    }

    @Async
    public void testAsync4() {
        log.debug("Start testAsync4");
        for (int j = 0; j < 1000; j++);
        log.debug("End testAsync4");
    }

    @Async
    public void testAsync5() {
        log.debug("Start testAsync5");
        for (int j = 0; j < 1000; j++);
        log.debug("End testAsync5");
    }

    @Async
    public void testAsync6() {
        log.debug("Start testAsync6");
        for (int j = 0; j < 1000; j++);
        log.debug("End testAsync6");
    }
}
