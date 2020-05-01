package com.ioverlap.dojo.carservice.exception;

public class ErrorMessage {

    private String message;

    private String exception;

    private String path;

    public ErrorMessage(Exception ex, String path) {
        message = ex.getMessage();
        exception = ex.getClass().getSimpleName();
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", exception='" + exception + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
