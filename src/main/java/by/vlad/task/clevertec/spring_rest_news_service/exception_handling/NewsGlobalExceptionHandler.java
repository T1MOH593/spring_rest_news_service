package by.vlad.task.clevertec.spring_rest_news_service.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NewsGlobalExceptionHandler {

    /**
     * @param exception is handling exception throwing where client enters wrong ID
     * @return error page with the message of exception and 404 status
     */
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchNewsException exception) {
        var data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
