package by.vlad.task.clevertec.spring_rest_news_service.exception_handling;

public class NoSuchNewsException extends RuntimeException {

    public NoSuchNewsException(String message) {
        super(message);
    }
}
