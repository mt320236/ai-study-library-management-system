package com.example.librarySystem.Controller;


import com.example.librarySystem.Entity.Payment;
import com.example.librarySystem.Entity.Seat;
import com.example.librarySystem.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(payment));

    }
    @GetMapping
    public ResponseEntity<List<Payment>> seePayment(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.seePayment());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Payment> seePaymentById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.seePaymentById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePaymentById(@PathVariable Long id, @RequestBody Payment payment){
        Payment payment1=paymentService.updatePaymentById(id,payment);
        return ResponseEntity.status(HttpStatus.OK).body(payment1);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentById(@PathVariable Long id){
        paymentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Payment deleted");

    }
}
