package sk.stuba.fei.uim.vsa.service;

import sk.stuba.fei.uim.vsa.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class StudentService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createNamedQuery(Student.FIND_ALL, Student.class);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }

    public Student findStudentById(final Long id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    public Student findStudentByEmail(final String email) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createNamedQuery(Student.FIND_BY_EMAIL, Student.class);
        query.setParameter("email", email);
        Optional<Student> student = query.getResultStream().findFirst();
        em.close();
        return student.orElse(null);
    }

    public Student createStudent(Student student) {
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
