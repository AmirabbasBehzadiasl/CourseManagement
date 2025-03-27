package com.amir.CourseManagement.Model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@Embeddable
public class CourseStudentId implements Serializable {
    private Integer courseId;
    private Integer studentId;

    public CourseStudentId() {
    }

    public CourseStudentId(Integer id, Integer id1) {
        courseId = id;
        studentId = id1;
    }

}
