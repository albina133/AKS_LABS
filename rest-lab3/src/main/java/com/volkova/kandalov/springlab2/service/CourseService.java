package com.volkova.kandalov.springlab2.service;

import com.volkova.kandalov.springlab2.entity.Course;
import com.volkova.kandalov.springlab2.entity.Student;
import com.volkova.kandalov.springlab2.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // все курсы
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    // найти курс
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    // создать или обновить курс
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    // удалить курс
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    // помощник для создания курса с привязкой к студенту
    public Course createCourse(String title, String description, Student student) {
        Course course = new Course(title, description, student);
        return courseRepository.save(course);
    }
}
