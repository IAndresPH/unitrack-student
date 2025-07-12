package edu.corhuila.unitrack.application.shared.exception;

public class PercentageExceededException extends RuntimeException{
    public PercentageExceededException(String message) {
        super(message);
    }
}