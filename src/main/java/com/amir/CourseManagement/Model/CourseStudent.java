package com.amir.CourseManagement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
