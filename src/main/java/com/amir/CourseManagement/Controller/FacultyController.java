package com.amir.CourseManagement.Controller;

import com.amir.CourseManagement.Model.Faculty;
import com.amir.CourseManagement.Service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there are no faculties");
        }

        return ResponseEntity.status(HttpStatus.OK).body(faculties);
    }

    @GetMapping("/getFacultyById/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.getFacultyById(id));
    }
    @PostMapping("/addFaculty")
    public ResponseEntity<?> addFaculty(@Valid @RequestBody Faculty faculty) {
        facultyService.addFaculty(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).body("faculty added successfully");
    }
    @PutMapping ("/updateFaculty/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable int id ,@Valid @RequestBody Faculty faculty) {
        facultyService.updateFaculty(id,faculty);
        return ResponseEntity.status(HttpStatus.OK).body("faculty updated");
    }

    @DeleteMapping("/deleteFaculty/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable int id) {
        facultyService.deleteFacultyById(id);
        return ResponseEntity.status(HttpStatus.OK).body("faculty deleted");
    }
}
