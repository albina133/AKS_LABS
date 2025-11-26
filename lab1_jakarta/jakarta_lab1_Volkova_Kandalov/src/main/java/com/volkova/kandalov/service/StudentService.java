package com.volkova.kandalov.service;

import com.volkova.kandalov.entity.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class StudentService {

    @PersistenceContext(unitName = "jakarta_lab1PU")
    private EntityManager em;

    public List<Student> findAll() {
        TypedQuery<Student> query =
                em.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void create(Student student) {
        em.persist(student);
    }

    public Student update(Student student) {
        return em.merge(student);
    }

    public void delete(Long id) {
        Student s = em.find(Student.class, id);
        if (s != null) {
            em.remove(s);
        }
    }
}