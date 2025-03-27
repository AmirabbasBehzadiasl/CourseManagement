package com.amir.CourseManagement.Model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private Integer id;

    @Column(unique=true, nullable=false)
    @NotNull(message = "nationalCode is required")
    @Size(min = 8, max = 10, message = "studentCode must be between 8 and 10 characters")
    @Pattern(regexp = "^\\d+$", message = "studentCode must contain only digits")
    private String studentCode;

    @NotNull(message = "Student firstname is required")
    @Size(min = 3 , max = 48 , message = "first name must be between 3 and 48 character")
    @Column(nullable=false , length = 48)
    private String firstName;

    @NotNull(message = "Student lastname is required")
    @Size(min = 3 , max = 48 , message = "lastname must be between 3 and 48 character")
    @Column(nullable=false , length = 48 )
    private String lastName;

    @NotNull(message = "nationalCode is required")
    @Pattern(regexp = "^\\d{10}$", message = "The national code must be 10 digits")
    @Column(unique = true , nullable=false)
    private String nationalCode;

    @Column(length = 128)
    private String address;

    @OneToMany(mappedBy = "student" , cascade = CascadeType.ALL , orphanRemoval = true)
    @NotNull(message = "courses is required")
    @JsonManagedReference("student-course")
    private List<CourseStudent> courses;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
