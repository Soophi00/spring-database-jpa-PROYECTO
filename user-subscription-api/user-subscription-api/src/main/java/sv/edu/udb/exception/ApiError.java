package sv.edu.udb.exception;

import java.time.OffsetDateTime;

public class ApiError {
    private int status;
    private String error;
    private String message;
    private String field;
    private OffsetDateTime timestamp;

    // getters/setters
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getField() { return field; }
    public void setField(String field) { this.field = field; }
    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
}
