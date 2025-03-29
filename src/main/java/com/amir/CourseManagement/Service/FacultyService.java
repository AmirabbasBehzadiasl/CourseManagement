package com.amir.CourseManagement.Service;

import com.amir.CourseManagement.Exceptions.AlreadyExistException;
import com.amir.CourseManagement.Model.Course;
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
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(int id) {
        return facultyRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Faculty with id " + id + " not found"));
    }

    public Faculty addFaculty(Faculty faculty) {
        facultyRepository.findFacultiesByName(faculty.getName())
                .ifPresentOrElse( existingFaculty -> {throw new AlreadyExistException("this Faculty already exists");},
                        () -> facultyRepository.save(faculty));

        return facultyRepository.save(faculty);

    }

    public void updateFaculty(int id, Faculty faculty) {
        facultyRepository.findById(id)
                .ifPresentOrElse(existingFaculty -> {
                    existingFaculty.setName(faculty.getName());
                    existingFaculty.setHead(faculty.getHead());
                    existingFaculty.setCourses(faculty.getCourses());
                    existingFaculty.setProfessors(faculty.getProfessors());
                    existingFaculty.setStudents(faculty.getStudents());
                    facultyRepository.save(existingFaculty);
                }, () -> {throw new NotFoundException("Faculty with id " + id + " not found");});
    }

    public void deleteFacultyById(int id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Faculty with id " + id + " not found"));
        facultyRepository.delete(faculty);
    }
}
