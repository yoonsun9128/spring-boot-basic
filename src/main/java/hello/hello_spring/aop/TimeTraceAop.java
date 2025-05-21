package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    //*에 뭘 넣는냐에 따라 달라질 수 있음
    @Around("execution(* hello.hello_spring..*(..))")
//    @Around("execution(* hello.hello_spring.service..*(..))") 서비스에 속한 애들만 보고싶을때
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }

    }
}
