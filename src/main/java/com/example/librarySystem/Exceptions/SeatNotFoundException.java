package com.example.librarySystem.Exceptions;

public class SeatNotFoundException extends RuntimeException{
    public SeatNotFoundException(String message) {
        super(message);
    }
}
