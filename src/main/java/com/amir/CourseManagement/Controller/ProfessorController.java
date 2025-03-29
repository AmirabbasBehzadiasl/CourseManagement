package com.amir.CourseManagement.Controller;

import com.amir.CourseManagement.Model.Professor;
import com.amir.CourseManagement.Service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    @GetMapping("/getAllProfessors")
    public ResponseEntity<?> getAllProfessors() {
        List<Professor> professors = professorService.getAllProfessors();
        if (professors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there are no professors");
        }

        return ResponseEntity.status(HttpStatus.OK).body(professors);
    }

    @GetMapping("/getProfessorById/{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getProfessorById(id));
    }
    @PostMapping("/addProfessor")
    public ResponseEntity<?> addProfessor(@Valid @RequestBody Professor professor) {
        professorService.addProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body("professor added successfully");
    }
    @PutMapping ("/updateProfessor/{id}")
    public ResponseEntity<?> updateProfessor(@PathVariable int id ,@Valid @RequestBody Professor professor) {
        professorService.updateProfessor(id,professor);
        return ResponseEntity.status(HttpStatus.OK).body("Professor updated");
    }

    @DeleteMapping("/deleteProfessor/{id}")
    public ResponseEntity<?> deleteProfessor(@PathVariable int id) {
        professorService.deleteProfessorById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deleted");
    }
}
