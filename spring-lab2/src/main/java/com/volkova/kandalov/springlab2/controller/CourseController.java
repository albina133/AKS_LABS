package com.volkova.kandalov.springlab2.controller;

import com.volkova.kandalov.springlab2.entity.Course;
import com.volkova.kandalov.springlab2.entity.Student;
import com.volkova.kandalov.springlab2.service.CourseService;
import com.volkova.kandalov.springlab2.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;

    public CourseController(CourseService courseService,
                            StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    // курсы
    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("course", new Course());
        return "courses"; // шаблон courses.html
    }

    // добавление
    @PostMapping
    public String createCourse(@RequestParam String title,
                               @RequestParam String description,
                               @RequestParam(required = false) Long studentId) {

        Student student = null;
        if (studentId != null) {
            Optional<Student> studentOpt = studentService.findById(studentId);
            student = studentOpt.orElse(null);
        }

        Course course = new Course(title, description, student);
        courseService.save(course);

        return "redirect:/courses";
    }

    // делет
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }
}
