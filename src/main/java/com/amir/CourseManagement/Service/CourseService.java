package com.amir.CourseManagement.Service;

import com.amir.CourseManagement.Exceptions.AlreadyExistException;
import com.amir.CourseManagement.Exceptions.NotFoundException;
import com.amir.CourseManagement.Model.Course;
import com.amir.CourseManagement.Model.CourseStudent;
import com.amir.CourseManagement.Model.CourseStudentId;
import com.amir.CourseManagement.Model.Student;
import com.amir.CourseManagement.Repository.CourseRepository;
import com.amir.CourseManagement.Repository.CourseStudentRepository;
import com.amir.CourseManagement.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    StudentRepository studentRepository;
    CourseRepository courseRepository;
    CourseStudentRepository courseStudentRepository;
    public CourseService(CourseRepository courseRepository, CourseStudentRepository courseStudentRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
        this.studentRepository = studentRepository;
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
                    () -> {
                courseRepository.save(course);
                if (course.getStudents() != null) {
                for (CourseStudent courseStudent : course.getStudents()) {
                Student student = studentRepository.findById(courseStudent.getStudent().getId())
                        .orElseThrow(() -> new NotFoundException("Student with id " + courseStudent.getId().getStudentId() + " not found"));
                courseStudent.setId(new CourseStudentId(course.getId(), student.getId()));
                courseStudent.setCourse(course);
                courseStudent.setStudent(student);
                courseStudent.setScore(courseStudent.getScore());

                courseStudentRepository.save(courseStudent);
                }
            }
        });
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

