package sk.stuba.fei.uim.vsa.service;

import sk.stuba.fei.uim.vsa.domain.Lecture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LectureService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public Lecture createLecture(Lecture lecture) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(lecture);
            em.getTransaction().commit();
        } catch (Exception e ) {
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            lecture = null;
        }
        em.close();
        return lecture;
    }
}
