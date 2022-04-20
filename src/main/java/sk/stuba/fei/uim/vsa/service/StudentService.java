package sk.stuba.fei.uim.vsa.service;

import sk.stuba.fei.uim.vsa.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public Student findStudentById(final Long id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    public Student saveStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        }
        em.close();
        return student;
    }
}
