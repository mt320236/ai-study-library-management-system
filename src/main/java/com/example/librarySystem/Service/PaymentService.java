package com.example.librarySystem.Service;



import com.example.librarySystem.Entity.Payment;
import com.example.librarySystem.Repository.PaymentRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> seePayment() {
        return paymentRepository.findAll();
    }

    public  Payment seePaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    public Payment updatePaymentById(Long id, Payment payment) {
        Payment existingPayment=paymentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Paymment not found"));
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentDate(payment.getPaymentDate());

        return paymentRepository.save(existingPayment);
    }

    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }
}
