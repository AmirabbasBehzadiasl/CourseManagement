package com.amir.CourseManagement.Controller;

import com.amir.CourseManagement.Model.Student;
import com.amir.CourseManagement.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }
    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }
    @PutMapping ("/updateStudent/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id ,@Valid @RequestBody Student student) {
        studentService.updateStudent(id,student);
        return ResponseEntity.status(HttpStatus.OK).body("student updated");
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("student deleted");
    }
}
