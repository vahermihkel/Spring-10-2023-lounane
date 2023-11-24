package ee.mihkel.materials.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(SisuNotFoundException e) {
        ErrorMessage message = new ErrorMessage();
        ErrorContent errorContent = new ErrorContent();
        errorContent.setCode(404);
        errorContent.setTimestamp(new Date());
        errorContent.setMessage("Sisu not found");
        message.setError(errorContent);

        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException e) {
        ErrorMessage message = new ErrorMessage();
        ErrorContent errorContent = new ErrorContent();
        errorContent.setCode(400);
        errorContent.setTimestamp(new Date());
        errorContent.setMessage(e.getMessage());
        message.setError(errorContent);

        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        ErrorMessage message = new ErrorMessage();
        ErrorContent errorContent = new ErrorContent();
        errorContent.setCode(400);
        errorContent.setTimestamp(new Date());
        errorContent.setMessage(e.getMessage());
        message.setError(errorContent);

        return ResponseEntity.status(400).body(message);
    }
}
