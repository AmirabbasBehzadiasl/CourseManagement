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
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false , length = 48)
    @NotNull(message = "name is required")
    @Size(min = 5, max = 48)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String name;

    @JoinColumn(nullable = false,name = "head_id")
    @NotNull(message = "you should specify head of faculty")
    @OneToOne
    private Professor head;

    @OneToMany(mappedBy = "faculty")
    private List<Professor> professors;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    @Column(nullable = false)
    @OneToMany(mappedBy = "faculty")
    @NotNull(message = "courses is required")
    @JsonManagedReference("faculty")
    private List<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "name is required") @Size(min = 5, max = 48) @Pattern(regexp = "^[a-zA-Z0-9]+$") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "name is required") @Size(min = 5, max = 48) @Pattern(regexp = "^[a-zA-Z0-9]+$") String name) {
        this.name = name;
    }

    public @NotNull(message = "you should specify head of faculty") Professor getHead() {
        return head;
    }

    public void setHead(@NotNull(message = "you should specify head of faculty") Professor head) {
        this.head = head;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public @NotNull(message = "courses is required") List<Course> getCourses() {
        return courses;
    }

    public void setCourses(@NotNull(message = "courses is required") List<Course> courses) {
        this.courses = courses;
    }
}
