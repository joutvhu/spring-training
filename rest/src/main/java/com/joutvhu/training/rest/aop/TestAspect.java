package com.joutvhu.training.rest.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class TestAspect {
    @Before(value = "execution(* com.joutvhu.training.rest.service.AopService.notWorkWithPrivate())")
    public void beforeAdvice1() {
        log.debug("beforeAdvice notWorkWithPrivate");
    }

    @Before(value = "execution(* com.joutvhu.training.rest.service.AopService.notWorkWithProtected())")
    public void beforeAdvice2() {
        log.debug("beforeAdvice notWorkWithProtected");
    }

    @Before(value = "execution(* com.joutvhu.training.rest.service.AopService.workWithPublic())")
    public void beforeAdvice3() {
        log.debug("beforeAdvice workWithPublic");
    }

    @Before(value = "execution(* com.joutvhu.training.rest.service.AopService.notWorkWithFinal())")
    public void beforeAdvice4() {
        log.debug("beforeAdvice notWorkWithFinal");
    }
}
