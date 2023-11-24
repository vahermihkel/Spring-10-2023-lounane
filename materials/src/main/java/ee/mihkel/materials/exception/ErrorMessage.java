package ee.mihkel.materials.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private ErrorContent error;
}

@Data
class ErrorContent {
    private String message;
    private int code;
    private Date timestamp;
}
