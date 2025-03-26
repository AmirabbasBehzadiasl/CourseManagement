package com.amir.CourseManagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false , length = 64)
    @NotNull(message = "Course name is required")
    @Size(min = 2, max = 64)
    private String name;

    @Column(nullable=false)
    @NotNull(message = "Course credit is required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits must be at most 10")
    private Integer credits;

    @OneToMany(mappedBy = "course")
    private List<CourseStudent> students;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
