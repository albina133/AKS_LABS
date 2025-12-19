package com.volkova.kandalov.springlab2.repository;

import com.volkova.kandalov.springlab2.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}