package com.example.librarySystem.Service;

import com.example.librarySystem.Entity.Fee;
import com.example.librarySystem.Repository.FeeRepository;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {
    private final FeeRepository feeRepository;

    public FeeService(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    public Fee createFee(Fee fee) {
       return feeRepository.save(fee);
    }

    public List<Fee> seeFee() {
        return feeRepository.findAll();
    }

    public Fee seeFeeById(Long id) {
        return feeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Fee Details not found"));
    }
    public Fee updateFeeById(Fee fee,Long id){
        Fee existing_fee=feeRepository.findById(id).orElseThrow(() -> new  IllegalArgumentException("Fee not found"));
        existing_fee.setAmount(fee.getAmount());
        existing_fee.setDueDate(fee.getDueDate());
        existing_fee.setStatus(fee.getStatus());
        return feeRepository.save(existing_fee);
    }

    public void deleteById(Long id) {
        feeRepository.deleteById(id);
    }
}
