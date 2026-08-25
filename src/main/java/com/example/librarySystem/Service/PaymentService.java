package com.example.librarySystem.Service;



import com.example.librarySystem.Entity.Fee;
import com.example.librarySystem.Entity.Payment;
import com.example.librarySystem.Exceptions.FeeNotFoundException;
import com.example.librarySystem.Exceptions.PaymentNotFoundExcpetion;
import com.example.librarySystem.Repository.FeeRepository;
import com.example.librarySystem.Repository.PaymentRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final FeeRepository feeRepository;

    public PaymentService(PaymentRepository paymentRepository, FeeRepository feeRepository) {
        this.paymentRepository = paymentRepository;
        this.feeRepository = feeRepository;
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> seePayment() {
        return paymentRepository.findAll();
    }

    public  Payment seePaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundExcpetion("Payment with Id "+id+" not found"));
    }

    public Payment updatePaymentById(Long id, Payment payment) {
        Payment existingPayment=paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundExcpetion("Paymment not found"));
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentDateTime(payment.getPaymentDateTime());
        existingPayment.setFee(payment.getFee());
        existingPayment.setTransactionMode(payment.getTransactionMode());



        return paymentRepository.save(existingPayment);
    }

    public void deleteById(Long id) {

        paymentRepository.deleteById(id);
    }
    public Payment markFeeaAsPaid(Long feeId,double amount,String transactionMode){
        Fee fee=feeRepository.findById(feeId).orElseThrow(() -> new FeeNotFoundException("Fee with Id "+feeId+" is not found"));
        if(fee.getStatus().equals("PAID")) throw new IllegalArgumentException("Fee already paid");
        fee.setStatus("PAID");
        feeRepository.save(fee);
        Payment payment=new Payment();

        payment.setFee(fee);
        payment.setAmount(amount);
        payment.setTransactionMode(transactionMode);
        payment.setStudent(fee.getStudent());  // fee se student lo
        payment.setPaymentDateTime(LocalDateTime.now());
        paymentRepository.save(payment);
        return payment;

    }
}

