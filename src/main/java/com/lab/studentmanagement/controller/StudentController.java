package com.lab.studentmanagement.controller;

import com.lab.studentmanagement.model.Student;
import com.lab.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CONTROLLER LAYER
 * Handles all HTTP requests from the View.
 * Routes requests to the Service layer and returns responses.
 *
 * Two roles:
 *  1. MVC Controller  → Serves the HTML page (Thymeleaf)
 *  2. REST Controller → Handles API calls from the frontend
 */
@Controller
public class StudentController {

    // Dependency Injection - Spring injects the service automatically
    @Autowired
    private StudentService studentService;

    // ================================================================
    // MVC Endpoint: Serves the main HTML page
    // ================================================================

    /**
     * GET /
     * Renders the home page with the registration form and student list.
     * Passes all students from the DB to the Thymeleaf template.
     */
    @GetMapping("/")
    public String homePage(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("student", new Student()); // Empty object for form binding
        return "index"; // maps to src/main/resources/templates/index.html
    }

    // ================================================================
    // REST API Endpoints
    // ================================================================

    /**
     * POST /api/students
     * Creates a new student record from the submitted form data.
     * Validates required fields and prevents duplicate email.
     *
     * @param student JSON body with name, email, course
     * @return 201 Created with saved student, or 400 Bad Request on error
     */
    @PostMapping("/api/students")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();

        // Validate required fields
        if (student.getName() == null || student.getName().isBlank()) {
            response.put("error", "Name is required.");
            return ResponseEntity.badRequest().body(response);
        }
        if (student.getEmail() == null || student.getEmail().isBlank()) {
            response.put("error", "Email is required.");
            return ResponseEntity.badRequest().body(response);
        }
        if (student.getCourse() == null || student.getCourse().isBlank()) {
            response.put("error", "Course is required.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            Student saved = studentService.addStudent(student);
            response.put("message", "Student registered successfully!");
            response.put("student", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            // Duplicate email caught here
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * GET /api/students
     * Returns a list of all registered students in JSON format.
     *
     * @return 200 OK with list of all students
     */
    @GetMapping("/api/students")
    @ResponseBody
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students/{id}
     * Returns a single student by their ID.
     *
     * @param id the student's unique ID
     * @return 200 OK with the student, or 404 Not Found
     */
    @GetMapping("/api/students/{id}")
    @ResponseBody
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            return ResponseEntity.ok(student);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    /**
     * GET /api/students/course/{course}
     * Returns all students enrolled in the specified course.
     *
     * @param course the course name
     * @return 200 OK with list of students in that course
     */
    @GetMapping("/api/students/course/{course}")
    @ResponseBody
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable String course) {
        List<Student> students = studentService.getStudentsByCourse(course);
        return ResponseEntity.ok(students);
    }

    /**
     * DELETE /api/students/{id}
     * Deletes a student by their ID.
     *
     * @param id the student's unique ID
     * @return 200 OK on success, or 404 Not Found
     */
    @DeleteMapping("/api/students/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            studentService.deleteStudent(id);
            response.put("message", "Student deleted successfully!");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
