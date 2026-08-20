package com.example.librarySystem.Service;

import com.example.librarySystem.Entity.Fee;
import com.example.librarySystem.Entity.Student;
import com.example.librarySystem.Exceptions.FeeNotFoundException;
import com.example.librarySystem.Exceptions.StudentNotFoundException;
import com.example.librarySystem.Repository.FeeRepository;


import com.example.librarySystem.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeeService {
    private final FeeRepository feeRepository;
    private final StudentRepository studentRepository;


    public FeeService(FeeRepository feeRepository, StudentRepository studentRepository) {
        this.feeRepository = feeRepository;
        this.studentRepository = studentRepository;
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
    public Fee createFeeForStudent(Long studentId){
        Student student=studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with Id "+studentId+" not found"));
        Fee fee=new Fee();
        fee.setStatus("PENDING");
        fee.setDueDate(LocalDate.now().plusDays(30));
        fee.setAmount(400);
        fee.setStudent(student);
        feeRepository.save(fee);
        return fee;
    }
    public Fee markFeeAsPaid(Long feeId){
        Fee fee=feeRepository.findById(feeId).orElseThrow(() -> new FeeNotFoundException("Fee with Id "+feeId+" not found"));
        fee.setStatus("PAID");
        feeRepository.save(fee);
        return fee;

    }
    public List<Fee> getOverDueFees(){
        return feeRepository.findByDueDateBeforeAndStatus(LocalDate.now(),"PENDING");
    }
}
