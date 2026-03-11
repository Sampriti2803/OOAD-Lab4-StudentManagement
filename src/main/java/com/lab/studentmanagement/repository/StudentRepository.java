package com.lab.studentmanagement.repository;

import com.lab.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * REPOSITORY LAYER
 * Extends JpaRepository to get built-in CRUD operations for Student entity.
 * Spring Data JPA auto-implements this interface — no SQL needed.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find a student by email address.
     * Used by the service layer to check for duplicate emails.
     */
    Optional<Student> findByEmail(String email);

    /**
     * Retrieve all students enrolled in a specific course.
     * Spring Data JPA auto-generates the query from the method name.
     */
    List<Student> findByCourse(String course);
}
