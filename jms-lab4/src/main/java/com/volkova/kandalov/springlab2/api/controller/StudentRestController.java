package com.volkova.kandalov.springlab2.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.volkova.kandalov.springlab2.api.dto.StudentsDto;
import com.volkova.kandalov.springlab2.entity.Student;
import com.volkova.kandalov.springlab2.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;
    private final XmlMapper xmlMapper;

    public StudentRestController(StudentService studentService,
                                 @Qualifier("xmlMapper") XmlMapper xmlMapper) {
        this.studentService = studentService;
        this.xmlMapper = xmlMapper;
    }

    // get
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentsDto getAllJson() {
        return new StudentsDto(studentService.findAll());
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public StudentsDto getAllXml() {
        return new StudentsDto(studentService.findAll());
    }

    // get id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getByIdJson(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Student> getByIdXml(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // post
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Student> create(@RequestBody Student student) {
        student.setId(null);
        Student saved = studentService.save(student);
        return ResponseEntity.ok(saved);
    }

    // put id
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        if (studentService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        student.setId(id);
        Student saved = studentService.save(student);
        return ResponseEntity.ok(saved);
    }

    // delete id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (studentService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // get xsl
    @GetMapping(value = "/xsl", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> studentsXmlWithXsl() throws JsonProcessingException {

        String xml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <?xml-stylesheet type="text/xsl" href="/xsl/students.xsl"?>
                """ + xmlMapper.writeValueAsString(new StudentsDto(studentService.findAll()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/xml; charset=UTF-8")
                .body(xml);
    }
}
