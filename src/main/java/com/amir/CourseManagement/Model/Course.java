package com.amir.CourseManagement.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Integer id;

    @Column(unique=true, nullable=false , length = 64)
    @NotNull(message = "Course name is required")
    @Size(min = 2, max = 64 , message = "Course name must be between 2 and 64")
    private String name;

    @Column(nullable=false)
    @NotNull(message = "Course credit is required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits must be at most 10")
    private Integer credits;

    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference("course-student")
    private List<CourseStudent> students;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
