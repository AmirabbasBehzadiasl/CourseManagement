package com.amir.CourseManagement.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    private Integer personnelNumber;

    @Column(nullable=false,length = 48)
    private String firstName;

    @Column(nullable=false,length = 48)
    private String lastName;

    @Column(unique = true,nullable=false)
    private String nationalCode;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
