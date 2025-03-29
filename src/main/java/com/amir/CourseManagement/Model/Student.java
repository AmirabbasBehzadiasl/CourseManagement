package com.amir.CourseManagement.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
//import lombok.*;

import java.util.List;
@Entity
//@Getter
//@Setter
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
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

    @OneToMany(mappedBy = "student")
    @NotNull(message = "courses is required")
//    @JsonManagedReference("student-course")
    private List<CourseStudent> courses;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "nationalCode is required") @Size(min = 8, max = 10, message = "studentCode must be between 8 and 10 characters") @Pattern(regexp = "^\\d+$", message = "studentCode must contain only digits") String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(@NotNull(message = "nationalCode is required") @Size(min = 8, max = 10, message = "studentCode must be between 8 and 10 characters") @Pattern(regexp = "^\\d+$", message = "studentCode must contain only digits") String studentCode) {
        this.studentCode = studentCode;
    }

    public @NotNull(message = "Student firstname is required") @Size(min = 3, max = 48, message = "first name must be between 3 and 48 character") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "Student firstname is required") @Size(min = 3, max = 48, message = "first name must be between 3 and 48 character") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "Student lastname is required") @Size(min = 3, max = 48, message = "lastname must be between 3 and 48 character") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "Student lastname is required") @Size(min = 3, max = 48, message = "lastname must be between 3 and 48 character") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "nationalCode is required") @Pattern(regexp = "^\\d{10}$", message = "The national code must be 10 digits") String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(@NotNull(message = "nationalCode is required") @Pattern(regexp = "^\\d{10}$", message = "The national code must be 10 digits") String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public @NotNull(message = "courses is required") List<CourseStudent> getCourses() {
        return courses;
    }

    public void setCourses(@NotNull(message = "courses is required") List<CourseStudent> courses) {
        this.courses = courses;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
