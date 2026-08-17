package com.example.librarySystem.Exceptions;

public class SeatAlreadyOccupiedException extends RuntimeException{
    public SeatAlreadyOccupiedException(String message) {
        super(message);
    }
}
