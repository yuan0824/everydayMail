package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yuan
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("execution(* request(..))")
    public void log(){}

    @AfterReturning(value = "log()" , returning = "json")
    public void doLog(String json) {
        log.info(json);
    }
}
