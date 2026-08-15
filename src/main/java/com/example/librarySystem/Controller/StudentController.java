package com.example.librarySystem.Controller;

import com.example.librarySystem.Entity.Student;
import com.example.librarySystem.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student student1=studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student1);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findStudent());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student student){
        Student existingStudent=studentService.updateStudent(id,student);
        return ResponseEntity.status(HttpStatus.OK).body(existingStudent);

    }
    @PostMapping("/{studentId}/seat/{seatId}")
    public ResponseEntity<String> assignSeat(@PathVariable Long studentId,@PathVariable Long seatId){
        studentService.assignSeat(studentId,seatId);
        return ResponseEntity.status(HttpStatus.OK).body("SeatAssigned");

    }
}
