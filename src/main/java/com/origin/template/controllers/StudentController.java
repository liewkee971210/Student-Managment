package com.origin.template.controllers;

import com.origin.template.model.Student;
import com.origin.template.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, HttpServletRequest request) {
        logRequest(request, null);
        List<Student> students = studentService.getAllStudents(page, size);
        logResponse(students);
        return ResponseEntity.ok(students);
    }
    
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student, HttpServletRequest request) {
        logRequest(request, student);
        Student createdStudent = studentService.saveStudent(student);
        logResponse(createdStudent);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails, HttpServletRequest request) {
        logRequest(request, studentDetails);
        int updatedStudent = studentService.updateStudent(id, studentDetails);
        logResponse(updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id, HttpServletRequest request) {
        logRequest(request, id);
        studentService.deleteStudent(id);
        logResponse(null);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable Long id, HttpServletRequest request) {
        logRequest(request, id);
        Optional<Student> student = studentService.getStudent(id);
        logResponse(student);
        return ResponseEntity.ok(student);
    }

    private void logRequest(HttpServletRequest request, Object body) {
        LOGGER.info("REQUEST " + request.getMethod() + " " + request.getRequestURI() + " BODY: " + body);
    }

    private void logResponse(Object body) {
        LOGGER.info("RESPONSE BODY: " + body);
    }

}
