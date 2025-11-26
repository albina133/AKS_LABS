package com.volkova.kandalov.controller;

import com.volkova.kandalov.entity.Course;
import com.volkova.kandalov.entity.Student;
import com.volkova.kandalov.service.CourseService;
import com.volkova.kandalov.service.StudentService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    @EJB
    private CourseService courseService;

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        List<Course> courses = courseService.findAll();
        req.setAttribute("courses", courses);

        req.getRequestDispatcher("courses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String studentIdStr = req.getParameter("studentId");

        Student student = null;
        if (studentIdStr != null && !studentIdStr.isBlank()) {
            try {
                Long studentId = Long.valueOf(studentIdStr);
                student = studentService.findById(studentId);
            } catch (NumberFormatException e) {
                // просто игнорируем, если id не число
            }
        }

        courseService.create(title, description, student);

        resp.sendRedirect("courses");
    }
}