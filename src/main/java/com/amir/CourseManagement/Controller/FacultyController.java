package com.amir.CourseManagement.Controller;

import com.amir.CourseManagement.Model.Faculty;
import com.amir.CourseManagement.Service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    FacultyService facultyService;
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/getAllFaculties")
    public ResponseEntity<?> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAllFaculties();
        if (faculties.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(faculties);
    }

    @GetMapping("/getFacultyById/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable int id) {
        Optional<Faculty> faculty = facultyService.getFacultyById(id);
        if (faculty.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        return ResponseEntity.ok(faculty);
    }
    @PostMapping("/addFaculty")
    public ResponseEntity<?> addFaculty(@RequestBody Faculty faculty) {
        facultyService.addFaculty(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).body("faculty added");
    }
    @PutMapping ("/updateFaculty/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable int id ,@RequestBody Faculty faculty) {
        Faculty faculty1 =  facultyService.updateFaculty(id,faculty);
        return ResponseEntity.ok(faculty1);
    }

    @DeleteMapping("/deleteFacultyById/{id}")
    public ResponseEntity<?> deleteFacultyById(@PathVariable int id) {
        facultyService.deleteFacultyById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("faculty deleted");
    }
}
