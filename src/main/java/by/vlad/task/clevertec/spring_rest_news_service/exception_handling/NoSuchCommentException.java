package by.vlad.task.clevertec.spring_rest_news_service.exception_handling;

public class NoSuchCommentException extends RuntimeException {

    public NoSuchCommentException(String message) {
        super(message);
    }
}
