package com.amir.CourseManagement.Service;

import com.amir.CourseManagement.Exceptions.AlreadyExistException;
import com.amir.CourseManagement.Exceptions.NotFoundException;
import com.amir.CourseManagement.Model.Course;
import com.amir.CourseManagement.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
           return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Course with id " + id + " not found"));
    }

    public void addCourse(Course course) {
        courseRepository.findCourseByName(course.getName())
            .ifPresentOrElse( existingCourse -> {throw new AlreadyExistException("this course already exists");},
                    () -> courseRepository.save(course));
    }

    public void updateCourse(int id, Course course) {
        courseRepository.findById(id)
                .ifPresentOrElse(existingCourse -> {
                    existingCourse.setName(course.getName());
                    existingCourse.setCredits(course.getCredits());
                    existingCourse.setStudents(course.getStudents());
                    existingCourse.setFaculty(course.getFaculty());
                    existingCourse.setProfessor(course.getProfessor());
                    courseRepository.save(existingCourse);
                }, () -> {throw new NotFoundException("Course with id " + id + " not found");});
    }

    public void deleteCourseById(int id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with id " + id + " not found"));
        courseRepository.delete(course);
    }
}

