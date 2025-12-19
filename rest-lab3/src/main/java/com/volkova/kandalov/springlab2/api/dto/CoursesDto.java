package com.volkova.kandalov.springlab2.api.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.volkova.kandalov.springlab2.entity.Course;

import java.util.List;

@JacksonXmlRootElement(localName = "courses")
public class CoursesDto {

    @JacksonXmlProperty(localName = "course")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Course> courses;

    public CoursesDto() {}

    public CoursesDto(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}