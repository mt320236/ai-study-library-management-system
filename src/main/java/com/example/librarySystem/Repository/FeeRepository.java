package com.example.librarySystem.Repository;

import com.example.librarySystem.Entity.Fee;
import com.example.librarySystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee,Long> {
    List<Fee> findByDueDateBeforeAndStatus(LocalDate date, String status);
    List<Fee> findByStudent(Student student);
}
