package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
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
     *
     * (* means all )
     * all class of controller package all method and any parameter(..)
     * */
    @Before(value = "execution(* com.example.demo.controller.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint)
    {
        logger.info("inside before advice");
    }
}
