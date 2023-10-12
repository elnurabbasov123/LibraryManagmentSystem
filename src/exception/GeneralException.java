package exception;

import enums.Exception;

import java.time.LocalDateTime;

public class GeneralException extends RuntimeException{
    private int status;
    private String message;
    private LocalDateTime dateTime;

    public GeneralException(Exception exception) {
        super(exception.getMessage());
        this.status = exception.getStatus();
        this.message = exception.getMessage();
        this.dateTime = exception.getExpDate();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
