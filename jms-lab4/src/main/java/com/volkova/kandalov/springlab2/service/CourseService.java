package com.volkova.kandalov.springlab2.service;

import com.volkova.kandalov.springlab2.entity.Course;
import com.volkova.kandalov.springlab2.entity.Student;
import com.volkova.kandalov.springlab2.jms.ChangeEvent;
import com.volkova.kandalov.springlab2.jms.ChangeEventProducer;
import com.volkova.kandalov.springlab2.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ChangeEventProducer changeEventProducer;

    public CourseService(CourseRepository courseRepository,
                         ChangeEventProducer changeEventProducer) {
        this.courseRepository = courseRepository;
        this.changeEventProducer = changeEventProducer;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        boolean isCreate = (course.getId() == null);

        Course saved = courseRepository.save(course);

        Long studentId = (saved.getStudent() != null ? saved.getStudent().getId() : null);

        String payload = "{"
                + "\"id\":" + saved.getId() + ","
                + "\"title\":\"" + esc(saved.getTitle()) + "\","
                + "\"description\":\"" + esc(saved.getDescription()) + "\","
                + "\"studentId\":" + (studentId == null ? "null" : studentId)
                + "}";

        changeEventProducer.send(new ChangeEvent(
                isCreate ? "INSERT" : "UPDATE",
                "Course",
                saved.getId(),
                payload
        ));

        return saved;
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);

        changeEventProducer.send(new ChangeEvent(
                "DELETE",
                "Course",
                id,
                "{\"id\":" + id + "}"
        ));
    }

    public Course createCourse(String title, String description, Student student) {
        Course course = new Course(title, description, student);
        Course saved = courseRepository.save(course);

        Long studentId = (student != null ? student.getId() : null);

        String payload = "{"
                + "\"id\":" + saved.getId() + ","
                + "\"title\":\"" + esc(saved.getTitle()) + "\","
                + "\"description\":\"" + esc(saved.getDescription()) + "\","
                + "\"studentId\":" + (studentId == null ? "null" : studentId)
                + "}";

        changeEventProducer.send(new ChangeEvent(
                "INSERT",
                "Course",
                saved.getId(),
                payload
        ));

        return saved;
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
