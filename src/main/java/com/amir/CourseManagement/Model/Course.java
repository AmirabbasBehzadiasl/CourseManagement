package com.amir.CourseManagement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
//@ToString
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

    @OneToMany(mappedBy = "course" )
    @JsonManagedReference("course-student")
    private List<CourseStudent> students;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonBackReference("professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonBackReference("faculty")
    private Faculty faculty;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", students=" + students +
                ", professor=" + professor +
                ", faculty=" + faculty +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Course name is required") @Size(min = 2, max = 64, message = "Course name must be between 2 and 64") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Course name is required") @Size(min = 2, max = 64, message = "Course name must be between 2 and 64") String name) {
        this.name = name;
    }

    public @NotNull(message = "Course credit is required") @Min(value = 1, message = "Credits must be at least 1") @Max(value = 10, message = "Credits must be at most 10") Integer getCredits() {
        return credits;
    }

    public void setCredits(@NotNull(message = "Course credit is required") @Min(value = 1, message = "Credits must be at least 1") @Max(value = 10, message = "Credits must be at most 10") Integer credits) {
        this.credits = credits;
    }

    public List<CourseStudent> getStudents() {
        return students;
    }

    public void setStudents(List<CourseStudent> students) {
        this.students = students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
