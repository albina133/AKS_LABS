package com.volkova.kandalov.springlab2.service;

import com.volkova.kandalov.springlab2.entity.Student;
import com.volkova.kandalov.springlab2.jms.ChangeEvent;
import com.volkova.kandalov.springlab2.jms.ChangeEventProducer;
import com.volkova.kandalov.springlab2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ChangeEventProducer changeEventProducer;

    public StudentService(StudentRepository studentRepository,
                          ChangeEventProducer changeEventProducer) {
        this.studentRepository = studentRepository;
        this.changeEventProducer = changeEventProducer;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        boolean isCreate = (student.getId() == null);

        Student saved = studentRepository.save(student);

        String payload = "{"
                + "\"id\":" + saved.getId() + ","
                + "\"fullName\":\"" + esc(saved.getFullName()) + "\","
                + "\"email\":\"" + esc(saved.getEmail()) + "\","
                + "\"age\":" + saved.getAge()
                + "}";

        changeEventProducer.send(new ChangeEvent(
                isCreate ? "INSERT" : "UPDATE",
                "Student",
                saved.getId(),
                payload
        ));

        return saved;
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);

        changeEventProducer.send(new ChangeEvent(
                "DELETE",
                "Student",
                id,
                "{\"id\":" + id + "}"
        ));
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
