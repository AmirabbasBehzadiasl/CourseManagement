package com.amir.CourseManagement.Service;

import com.amir.CourseManagement.Model.Student;
import com.amir.CourseManagement.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.isEmpty() ? Optional.empty() : Optional.of(students);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }
}
