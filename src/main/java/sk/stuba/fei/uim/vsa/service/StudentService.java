package sk.stuba.fei.uim.vsa.service;

import sk.stuba.fei.uim.vsa.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public Student getStudent(final Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Student.class, id);
    }
}
