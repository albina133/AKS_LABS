package com.volkova.kandalov.springlab2.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.volkova.kandalov.springlab2.api.dto.CoursesDto;
import com.volkova.kandalov.springlab2.entity.Course;
import com.volkova.kandalov.springlab2.entity.Student;
import com.volkova.kandalov.springlab2.service.CourseService;
import com.volkova.kandalov.springlab2.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final XmlMapper xmlMapper;

    public CourseRestController(CourseService courseService,
                                StudentService studentService,
                                @Qualifier("xmlMapper") XmlMapper xmlMapper) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.xmlMapper = xmlMapper;
    }

    // get
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CoursesDto getAllJson() {
        return new CoursesDto(courseService.findAll());
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CoursesDto getAllXml() {
        return new CoursesDto(courseService.findAll());
    }

    // get id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getByIdJson(@PathVariable Long id) {
        return courseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Course> getByIdXml(@PathVariable Long id) {
        return courseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // post
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Course> create(@RequestBody Course course) {

        // если пришёл student{id}, подтянем студента из БД
        if (course.getStudent() != null && course.getStudent().getId() != null) {
            Student st = studentService.findById(course.getStudent().getId()).orElse(null);
            course.setStudent(st);
        }

        course.setId(null);
        Course saved = courseService.save(course);
        return ResponseEntity.ok(saved);
    }

    // put id
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {

        if (courseService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (course.getStudent() != null && course.getStudent().getId() != null) {
            Student st = studentService.findById(course.getStudent().getId()).orElse(null);
            course.setStudent(st);
        }

        course.setId(id);
        Course saved = courseService.save(course);
        return ResponseEntity.ok(saved);
    }

    // delete id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (courseService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // get xsl
    @GetMapping(value = "/xsl", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> coursesXmlWithXsl() throws JsonProcessingException {

        String xml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <?xml-stylesheet type="text/xsl" href="/xsl/courses.xsl"?>
                """ + xmlMapper.writeValueAsString(new CoursesDto(courseService.findAll()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/xml; charset=UTF-8")
                .body(xml);
    }
}
