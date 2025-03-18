package com.amir.CourseManagement.Model;

import jakarta.persistence.*;
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
    private String name;

    @Column(nullable=false)
    private int credits;

    @OneToMany(mappedBy = "course")
    private List<CourseStudent> students;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
