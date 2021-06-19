package com.joutvhu.training.rest.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @see <a href="https://docs.spring.io/spring-framework/docs/2.5.x/reference/aop.html">AOP Doc</a>
 *      <a href="https://www.baeldung.com/spring-aop-pointcut-tutorial">AOP Pointcut</a>
 */
@Log4j2
@Aspect
@Component
public class BenchmarkAspect {
    @Pointcut("execution(* get*(..))")
    public void nameStartWithGet() { }

    @Pointcut("within(com.joutvhu.training.rest.service..*)")
    public void inPackageService() { }

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public void inRepositoryAnnotation() { }

    @Pointcut("@annotation(com.joutvhu.training.rest.aop.Benchmark)")
    public void annotationBenchmark() { }

    @Pointcut("this(com.joutvhu.training.rest.service.ProductService)")
    public void fromProductService() { }

    @Pointcut("target(com.joutvhu.training.rest.service.ProductService)")
    public void instanceOfProductService() { }

    @Pointcut("@target(org.springframework.web.bind.annotation.RestController)")
    public void hasRestController() { }

    @Pointcut("args(java.io.Serializable)")
    public void argsIsProduct() { }

    @Pointcut("@args(org.springframework.web.bind.annotation.RequestBody)")
    public void argsHasProduct() { }

    @Before(value = "annotationBenchmark()")
    public void beforeAdvice() {
        log.debug("2 beforeAdvice");
    }

    @After("@annotation(com.joutvhu.training.rest.aop.Benchmark)")
    public void afterAdvice() {
        log.debug("4 afterAdvice");
    }

    /**
     * ProceedingJoinPoint.proceed() {
     *     @Before
     *     try {
     *         runMethod();
     *         @AfterReturning
     *     } catch {
     *         @AfterThrowing
     *     }
     *     @After
     * }
     */
    @Around("@annotation(com.joutvhu.training.rest.aop.Benchmark)")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("1 before aroundAdvice");
        Object r = pjp.proceed();
        log.debug("5 after aroundAdvice: {}", r);
        return r;
    }

    @AfterReturning(value = "@annotation(com.joutvhu.training.rest.aop.Benchmark)", returning = "result")
    public void afterReturningAdvice(Object result) {
        log.debug("3 afterReturningAdvice");
    }

    @AfterThrowing(value = "@annotation(com.joutvhu.training.rest.aop.Benchmark)", throwing = "ex")
    public void afterThrowingAdvice(Throwable ex) {
        log.debug("3 afterThrowingAdvice");
    }
}
