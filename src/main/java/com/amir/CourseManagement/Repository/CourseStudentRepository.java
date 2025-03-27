package com.amir.CourseManagement.Repository;

import com.amir.CourseManagement.Model.CourseStudent;
import org.springframework.data.repository.CrudRepository;

public interface CourseStudentRepository extends CrudRepository<CourseStudent, Integer> {
}
