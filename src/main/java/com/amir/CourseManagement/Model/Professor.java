package com.amir.CourseManagement.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
//import lombok.*;

import java.util.List;

@Entity
//@Getter
//@Setter
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Professor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    @NotNull(message = "personnelNumber is required")
    private Integer personnelNumber;

    @Column(nullable=false,length = 48)
    @NotNull(message = "first name is required")
    @Size(min = 2, max = 48 , message = "firstName must be between 2 and 48 character")
    private String firstName;

    @NotNull(message = "last name is required")
    @Size(min = 2, max = 48 , message = "lastname must be between 2 and 48 character")
    @Column(nullable=false,length = 48)
    private String lastName;

    @Column(unique = true,nullable=false)
    @NotNull(message = "nationalCode is required")
    @Pattern(regexp = "^\\d{10}$", message = "The national code must be 10 digits")
    private String nationalCode;

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference("professor")
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "personnelNumber is required") Integer getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(@NotNull(message = "personnelNumber is required") Integer personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public @NotNull(message = "first name is required") @Size(min = 2, max = 48, message = "firstName must be between 2 and 48 character") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "first name is required") @Size(min = 2, max = 48, message = "firstName must be between 2 and 48 character") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "last name is required") @Size(min = 2, max = 48, message = "lastname must be between 2 and 48 character") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "last name is required") @Size(min = 2, max = 48, message = "lastname must be between 2 and 48 character") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "nationalCode is required") @Pattern(regexp = "^\\d{10}$", message = "The national code must be 10 digits") String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(@NotNull(message = "nationalCode is required") @Pattern(regexp = "^\\d{10}$", message = "The national code must be 10 digits") String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
