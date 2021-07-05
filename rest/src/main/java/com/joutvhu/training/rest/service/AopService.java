package com.joutvhu.training.rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AopService {
    private void notWorkWithPrivate() {
        log.debug("AopService.private");
    }

    protected void notWorkWithProtected() {
        log.debug("AopService.protected");
    }

    public void workWithPublic() {
        log.debug("Start AopService.public");
        this.notWorkWithPrivate();
        this.notWorkWithProtected();
        log.debug("End AopService.public");
    }

    public final void notWorkWithFinal() {
        log.debug("AopService.final");
    }

    public void notWorkWhenCallInASameObject() {
        log.debug("Start AopService.sameObject");
        this.workWithPublic();
        log.debug("End AopService.sameObject");
    }
}
