package com.amir.CourseManagement.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false , length = 48)
    @Size(min = 5, max = 48)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String name;

    @JoinColumn(nullable = false,name = "head_id")
    @OneToOne
    private Professor head;

    @OneToMany(mappedBy = "faculty")
    private List<Professor> professors;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    @Column(nullable = false)
    @OneToMany(mappedBy = "faculty")
    private List<Course> courses;


    public @Size(min = 5, max = 48) @Pattern(regexp = "^[a-zA-Z0-9]+$") String getName() {
        return name;
    }

    public void setName(@Size(min = 5, max = 48) @Pattern(regexp = "^[a-zA-Z0-9]+$") String name) {
        this.name = name;
    }
}
