package com.amir.CourseManagement.Controller;


import com.amir.CourseManagement.Model.Course;
import com.amir.CourseManagement.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
    }
    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course) {
        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("course added");
    }
    @PutMapping ("/updateCourse/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id ,@Valid @RequestBody Course course) {
        courseService.updateCourse(id,course);
        return ResponseEntity.status(HttpStatus.OK).body("course updated");
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable int id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("course deleted");
    }
}
