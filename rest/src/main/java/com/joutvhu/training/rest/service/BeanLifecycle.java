package com.joutvhu.training.rest.service;

import com.joutvhu.training.rest.security.jwt.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Log4j2
public class BeanLifecycle implements
        BeanNameAware,
        BeanClassLoaderAware,
        BeanFactoryAware,
        ApplicationContextAware,
        BeanPostProcessor,
        InitializingBean,
        DisposableBean {
    @Value("${server.servlet.context-path}")
    private String serverPath;

    private ProductService productService;

    private JwtService jwtService;

    public BeanLifecycle(JwtService jwtService) {
        log.debug("0 BeanLifecycle");
    }

    /**
     * Populate Properties
     */
    public void setProductService(ProductService productService) {
        log.debug("1 setProductService");
        this.productService = productService;
    }

    @Override
    public void setBeanName(String name) {
        log.debug("2 setBeanName {}", name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.debug("3 setBeanClassLoader {}", classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.debug("4 setBeanFactory {}", beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("5 setApplicationContext {}", applicationContext);

    }

    @PostConstruct
    public void postConstruct() {
        log.debug("6 postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("7 afterPropertiesSet");
    }

    public void customInit() {
        log.debug("8 customInit");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        log.debug("9* postProcessBeforeInitialization {} {}", beanName, bean);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        log.debug("10* postProcessAfterInitialization {} {}", beanName, bean);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    public void ready() {
        log.debug("11 ready");
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("12 preDestroy");
    }

    @Override
    public void destroy() throws Exception {
        log.debug("13 destroy");
    }

    public void customDistroy() {
        log.debug("14 customDistroy");
    }
}
