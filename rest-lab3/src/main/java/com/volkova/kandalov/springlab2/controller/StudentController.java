package com.volkova.kandalov.springlab2.controller;

import com.volkova.kandalov.springlab2.entity.Student;
import com.volkova.kandalov.springlab2.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // список студентов
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("student", new Student()); // для формы добавления
        return "students"; // шаблон students.html
    }

    // добавление студента
    @PostMapping
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    // форма эдита
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Optional<Student> studentOpt = studentService.findById(id);
        if (studentOpt.isPresent()) {
            model.addAttribute("student", studentOpt.get());
            return "student-edit"; // шаблон student-edit.html
        } else {
            return "redirect:/students";
        }
    }

    // сохранение изменений
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    // удаление
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}
