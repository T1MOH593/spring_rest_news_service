package by.vlad.task.clevertec.spring_rest_news_service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("!test")
public class LoggingControllerMethodsAspect {

    @Before("execution(* by.vlad.task.clevertec.spring_rest_news_service.controller.*.*(..))")
    public void beforeDaoMethodAdvice(JoinPoint joinPoint) {
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("--------------------------------------");
        System.out.println("Application is invoking method " + methodSignature.getName());
        System.out.println("--------------------------------------");
    }
}
