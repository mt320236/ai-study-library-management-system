package com.example.librarySystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private LocalDate dueDate;
    private String status;
    @JsonIgnore
    @OneToOne(mappedBy = "fee")
    private Payment payment;
    @JsonIgnoreProperties({"fees","payments","seat"})
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Fee(Long id, double amount, LocalDate dueDate, String status ,Student student) {
        this.id = id;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.student=student;
    }

    public Fee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
