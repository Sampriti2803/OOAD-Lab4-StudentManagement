package com.lab.studentmanagement.service;

import com.lab.studentmanagement.model.Student;
import com.lab.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * SERVICE LAYER
 * Contains all business logic for student operations.
 * Acts as the bridge between Controller and Repository.
 * Keeps the Controller clean by moving logic here.
 */
@Service
public class StudentService {

    // Dependency Injection - Spring injects the repository automatically
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Add a new student to the database.
     * Business Rule: Prevents duplicate email registration.
     *
     * @param student the student object to save
     * @return the saved student with auto-generated ID
     * @throws IllegalArgumentException if email already exists
     */
    public Student addStudent(Student student) {
        // Business Logic: Check for duplicate email
        Optional<Student> existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            throw new IllegalArgumentException(
                "A student with email '" + student.getEmail() + "' is already registered."
            );
        }
        return studentRepository.save(student);
    }

    /**
     * Retrieve all students from the database.
     *
     * @return list of all students
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieve a single student by their ID.
     *
     * @param id the student's unique ID
     * @return the student if found
     * @throws IllegalArgumentException if student not found
     */
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
    }

    /**
     * Retrieve all students enrolled in a specific course.
     *
     * @param course the course name
     * @return list of students in that course
     */
    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    /**
     * Delete a student by their ID.
     *
     * @param id the student's unique ID
     * @throws IllegalArgumentException if student not found
     */
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
