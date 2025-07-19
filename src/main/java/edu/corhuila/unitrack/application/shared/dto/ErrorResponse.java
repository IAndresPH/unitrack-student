package edu.corhuila.unitrack.application.shared.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private Map<String, String> validations;

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String path, Map<String, String> validations) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.validations = validations;
    }
}