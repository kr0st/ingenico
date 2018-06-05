package lv.javaguru.courses.ingenico.lecture8.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class MonitoredAspect {

    @Pointcut("((execution(* *(..)) && @annotation(Monitored))")
    public void execute() {}

    @Around("execute()")
    public Object doLog(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        log.debug("method : {}, args : {}", pjp.getSignature().toShortString(), pjp.getArgs());
        try {
            Object result = pjp.proceed();
            log.debug("result : {}, execution : {}ms", result, System.currentTimeMillis() - start);
            return result;
        } catch (Throwable t) {
            log.debug("completed exceptionally, execution : {}ms", System.currentTimeMillis() - start);
            throw t;
        }
    }

}
