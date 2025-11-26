package com.volkova.kandalov.service;

import com.volkova.kandalov.entity.Course;
import com.volkova.kandalov.entity.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class CourseService {

    @PersistenceContext(unitName = "jakarta_lab1PU")
    private EntityManager em;

    public List<Course> findAll() {
        TypedQuery<Course> query =
                em.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void create(String title, String description, Student student) {
        Course course = new Course(title, description, student);
        em.persist(course);
    }

    public void create(Course course) {
        em.persist(course);
    }

    public Course update(Course course) {
        return em.merge(course);
    }

    public void delete(Long id) {
        Course c = em.find(Course.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}