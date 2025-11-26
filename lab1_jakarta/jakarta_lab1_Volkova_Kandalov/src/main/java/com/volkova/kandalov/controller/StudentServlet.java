package com.volkova.kandalov.controller;

import com.volkova.kandalov.entity.Student;
import com.volkova.kandalov.service.StudentService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        List<Student> students = studentService.findAll();
        req.setAttribute("students", students);

        req.getRequestDispatcher("students.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String ageStr = req.getParameter("age");

        Integer age = ageStr == null ? null : Integer.valueOf(ageStr);

        studentService.create(new Student(fullName, email, age));

        resp.sendRedirect("students");
    }
}