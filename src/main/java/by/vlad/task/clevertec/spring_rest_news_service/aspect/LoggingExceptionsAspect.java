package by.vlad.task.clevertec.spring_rest_news_service.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("!test")
public class LoggingExceptionsAspect {

    @AfterThrowing(value = "execution(* by.vlad.task.clevertec.spring_rest_news_service.controller.*.*(..))",
    throwing = "exception")
    public void afterThrowingExceptionAdvice(Throwable exception) {
        System.out.println("--------------------------");
        System.out.println("was thrown exception " + exception);
        System.out.println("Message of exception is \"" + exception.getMessage() + "\"");
        System.out.println("--------------------------");
    }
}
