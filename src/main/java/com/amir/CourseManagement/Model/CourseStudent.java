package com.amir.CourseManagement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
//import lombok.*;

@Entity
//@Getter
//@Setter
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class CourseStudent {
    @EmbeddedId
    private CourseStudentId id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    @JsonBackReference("course-student")
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    @JsonBackReference("student-course")
    private Student student;

    private int score;

    public CourseStudentId getId() {
        return id;
    }

    public void setId(CourseStudentId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
