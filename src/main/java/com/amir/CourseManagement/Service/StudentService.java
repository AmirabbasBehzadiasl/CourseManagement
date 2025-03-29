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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepository;
    private  CourseRepository courseRepository;
    private  CourseStudentRepository courseStudentRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, CourseStudentRepository courseStudentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Student with id " + id + " not found"));
    }

    public void addStudent(Student student) {
        studentRepository.findStudentByStudentCode(student.getStudentCode())
                .ifPresentOrElse(existingStudent -> {
                    throw new AlreadyExistException("This student already exists");
                }, () -> {
                    studentRepository.save(student);

                        for (CourseStudent courseStudent : student.getCourses()) {
                                Course course = courseRepository.findById(courseStudent.getCourse().getId())
                                        .orElseThrow(() -> new NotFoundException("Course not found"));
                            courseStudent.setCourse(course);
                            courseStudent.setStudent(student);
                            courseStudent.setId(new CourseStudentId(course.getId(), student.getId()));

                            courseStudentRepository.save(courseStudent);
                        }
                });
    }



    public void updateStudent(int id, Student student) {
        Student lastStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));

        BeanUtils.copyProperties(lastStudent, student , "id","courses");

        List<CourseStudent> lastCourses = lastStudent.getCourses();

        for (CourseStudent newCourseStudent : student.getCourses()) {
            Course course = courseRepository.findById(newCourseStudent.getCourse().getId())
                    .orElseThrow(() -> new NotFoundException("Course not found"));

            CourseStudent existingCourseStudent = lastCourses.stream()
                    .filter(cs -> cs.getCourse().getId().equals(course.getId()))
                    .findFirst()
                    .orElse(null);

            if (existingCourseStudent != null) {
                existingCourseStudent.setScore(newCourseStudent.getScore());
            } else {
                CourseStudent courseStudent = new CourseStudent();
                courseStudent.setId(new CourseStudentId(course.getId(), lastStudent.getId()));
                courseStudent.setCourse(course);
                courseStudent.setStudent(lastStudent);
                courseStudent.setScore(newCourseStudent.getScore());

                lastCourses.add(courseStudent);
            }
        }

        studentRepository.save(student);
    }


    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
        studentRepository.delete(student);
    }
}
