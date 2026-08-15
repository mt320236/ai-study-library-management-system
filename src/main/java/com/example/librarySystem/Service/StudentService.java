package com.example.librarySystem.Service;

import com.example.librarySystem.Entity.Payment;
import com.example.librarySystem.Entity.Seat;
import com.example.librarySystem.Entity.Student;
import com.example.librarySystem.Repository.SeatRepository;
import com.example.librarySystem.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private SeatRepository seatRepository;

    public StudentService(StudentRepository studentRepository, SeatRepository seatRepository) {
        this.studentRepository = studentRepository;
        this.seatRepository = seatRepository;
    }

    public Student createStudent(Student student) {
        studentRepository.save(student);

        return student;
    }
    public List<Student> findStudent(){
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);

    }

    public Student updateStudent(Long id,Student student) {
        Student existingStudent=studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Paymment not found"));
        existingStudent.setEmail(student.getEmail());

        existingStudent.setName(student.getName());
        existingStudent.setPhone_no(student.getPhone_no());

        return studentRepository.save(existingStudent);


    }
    public void assignSeat(Long studentId,Long seatId){
        Student student=studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Seat seat=seatRepository.findById(seatId).orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        if(!seat.isAvailable()){
            throw new IllegalArgumentException("Seat Already Occupied");
        }
        student.setSeat(seat);
        seat.setAvailable(false);
        studentRepository.save(student);
        seatRepository.save(seat);
    }
}
