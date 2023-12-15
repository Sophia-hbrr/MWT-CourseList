package com.example.springbootcrud.repository;

import com.example.springbootcrud.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    public List<Course> findByFull(boolean full);
}
