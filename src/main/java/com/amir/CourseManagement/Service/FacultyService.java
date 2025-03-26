package com.amir.CourseManagement.Service;

import com.amir.CourseManagement.Model.Faculty;
import com.amir.CourseManagement.Exceptions.NotFoundException;
import com.amir.CourseManagement.Repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getAllFaculties() {
        System.out.println("Inside getAllFaculties");
        return facultyRepository.findAll();
    }

    public Optional<Faculty> getFacultyById(int id) {
        return facultyRepository.findById(id);
    }


    public void addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(int id, Faculty faculty) {
        Faculty faculty1 = facultyRepository.findById(id).orElseThrow(() -> new NotFoundException("faculty with id " +id+" doesn't exist"));
        faculty1.setName(faculty.getName());
        faculty1.setHead(faculty.getHead());
        faculty1.setStudents(faculty.getStudents());
        faculty1.setProfessors(faculty.getProfessors());
        faculty1.setCourses(faculty.getCourses());

        return facultyRepository.save(faculty1);
    }

    public void deleteFacultyById(int id) {
        facultyRepository.findById(id).orElseThrow(() -> new NotFoundException("faculty with id " + id + " doesn't exist"));
        facultyRepository.deleteById(id);
    }
}
