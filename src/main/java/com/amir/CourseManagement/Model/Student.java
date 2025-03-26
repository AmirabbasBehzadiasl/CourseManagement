package com.amir.CourseManagement.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    private Long studentCode;

    @Column(nullable=false , length = 48)
    private String firstName;

    @Column(nullable=false , length = 48 )
    private String lastName;

    @Column(unique = true , nullable=false)
    private String nationalCode;

    @Column(length = 128)
    private String address;

    @OneToMany(mappedBy = "student")
    private List<CourseStudent> courses;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
