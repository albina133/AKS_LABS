package com.volkova.kandalov.springlab2.repository;

import com.volkova.kandalov.springlab2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
