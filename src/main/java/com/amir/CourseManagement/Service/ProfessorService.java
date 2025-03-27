package com.amir.CourseManagement.Service;

import com.amir.CourseManagement.Exceptions.AlreadyExistException;
import com.amir.CourseManagement.Exceptions.NotFoundException;
import com.amir.CourseManagement.Model.Course;
import com.amir.CourseManagement.Model.Professor;
import com.amir.CourseManagement.Repository.CourseRepository;
import com.amir.CourseManagement.Repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    ProfessorRepository professorRepository;
    CourseRepository courseRepository;
    public ProfessorService(ProfessorRepository professorRepository, CourseRepository courseRepository) {
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
    }

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Professor getProfessorById(int id) {
        return professorRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Professor with id " + id + " not found"));
    }

    public Professor addProfessor(Professor professor) {
        professorRepository.findProfessorByPersonnelNumber(professor.getId())
                .ifPresentOrElse( existingProfessor -> {throw new AlreadyExistException("this Professor already exists");},
                        () -> professorRepository.save(professor));
        if (professor.getCourses() != null) {
            for (Course course : professor.getCourses()) {
                Course course1 = courseRepository.findById(course.getId())
                        .orElseThrow(() -> new NotFoundException("Course with id " + course.getId() + " not found"));
                course1.setProfessor(professor);
            }
        }
        return professorRepository.save(professor);

    }

    public void updateProfessor(int id, Professor professor) {
        professorRepository.findById(id)
                .ifPresentOrElse(existingProfessor -> {
                    existingProfessor.setFirstName(professor.getFirstName());
                    existingProfessor.setLastName(professor.getLastName());
                    existingProfessor.setCourses(professor.getCourses());
                    existingProfessor.setFaculty(professor.getFaculty());
                    existingProfessor.setPersonnelNumber(professor.getPersonnelNumber());
                    existingProfessor.setNationalCode(professor.getNationalCode());
                    professorRepository.save(existingProfessor);
                }, () -> {throw new NotFoundException("Professor with id " + id + " not found");});
        for (Course course : professor.getCourses()) {
            Course course1 = courseRepository.findById(course.getId())
                    .orElseThrow(() -> new NotFoundException("Course with id " + course.getId() + " not found"));
            course1.setProfessor(professor);
        }
    }

    public void deleteProfessorById(int id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with id " + id + " not found"));
        for (Course course : professor.getCourses()) {
            Course course1 = courseRepository.findById(course.getId())
                    .orElseThrow(() -> new NotFoundException("Course with id " + course.getId() + " not found"));
            courseRepository.delete(course1);
        }
        professorRepository.delete(professor);
    }
}
