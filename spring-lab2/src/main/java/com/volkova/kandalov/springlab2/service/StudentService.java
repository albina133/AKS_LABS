package com.volkova.kandalov.springlab2.service;

import com.volkova.kandalov.springlab2.entity.Student;
import com.volkova.kandalov.springlab2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // зависимости через конструктор
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // получение всех студентов
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // по id найдем
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    // создать / обновить студента
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    // удалить студента
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
