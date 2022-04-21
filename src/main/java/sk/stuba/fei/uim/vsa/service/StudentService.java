package sk.stuba.fei.uim.vsa.service;

import sk.stuba.fei.uim.vsa.domain.Student;
import sk.stuba.fei.uim.vsa.exceptions.AlreadyExistsException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createNamedQuery(Student.FIND_ALL, Student.class);
        return query.getResultList();
    }

    public Student findStudentById(final Long id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    public Student createStudent(Student student) throws AlreadyExistsException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new AlreadyExistsException();
        }
        em.close();
        return student;
    }

    public Student deleteStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(student);
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
