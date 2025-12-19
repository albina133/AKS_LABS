package com.volkova.kandalov.springlab2.api.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.volkova.kandalov.springlab2.entity.Student;

import java.util.List;

@JacksonXmlRootElement(localName = "students")
public class StudentsDto {

    @JacksonXmlProperty(localName = "student")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Student> students;

    public StudentsDto() {}

    public StudentsDto(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
