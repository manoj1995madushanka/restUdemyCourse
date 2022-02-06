package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * before advice example
     * <p>
     * (* means all )
     * all class of controller package all method and any parameter(..)
     */
    @Before(value = "execution(* com.example.demo.controller.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("inside before advice");
    }

    /**
     * before advice with one method parameter
     */
    @Before(value = "execution(* com.example.demo.controller.*.*(..)) and args(object)")
    public void beforeAdviceWithOneParam(JoinPoint joinPoint, Object object) {
        logger.info("inside before advice");
    }

    /**
     * before advice with two method parameter
     */
    @Before(value = "execution(* com.example.demo.controller.*.*(..)) and args(object1,object2)")
    public void beforeAdviceWithTwoParam(JoinPoint joinPoint, Object object1,Object object2) {
        logger.info("inside before advice");
    }

    /**
     * after advice example
     * */
    @After(value = "execution(* com.example.demo.controller.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("after before advice");
    }
}
