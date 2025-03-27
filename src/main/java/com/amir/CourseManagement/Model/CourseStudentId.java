package com.amir.CourseManagement.Model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CourseStudentId implements Serializable {
    private Integer courseId;
    private Integer studentId;
}
