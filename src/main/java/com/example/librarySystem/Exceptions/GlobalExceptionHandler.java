package com.example.librarySystem.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ StudentNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleExceptions(StudentNotFoundException exception){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("message",exception.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error","NOT FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleSeatNotFoundExcpetion(SeatNotFoundException seatNotFoundException){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("message",seatNotFoundException.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error","NOT FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);


    }

    @ExceptionHandler(SeatAlreadyOccupiedException.class)
    public ResponseEntity<Map<String,Object>> seatAlreadyOccupiedExcpetionHandler(SeatAlreadyOccupiedException seatAlreadyOccupiedException){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("message",seatAlreadyOccupiedException.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error","BAD REQUEST");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);


    }
    @ExceptionHandler(FeeNotFoundException.class)
    public ResponseEntity<Map<String,Object>> feeNotFoundExcpetionHandler(FeeNotFoundException feeNotFoundException){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("message",feeNotFoundException.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error","NOT FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);


    }
    @ExceptionHandler(PaymentNotFoundExcpetion.class)
    public ResponseEntity<Map<String,Object>> paymentNotFoundExcpetionHandler(PaymentNotFoundExcpetion paymentNotFoundExcpetion){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("message",paymentNotFoundExcpetion.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error","NOT FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);


    }

}
