package kipin.spring.AstonHybernate.persist.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import kipin.spring.AstonHybernate.persist.Entity.Positions;
import kipin.spring.AstonHybernate.persist.Entity.Projects;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Repository
public class PositionsRepository {
    @Autowired
    private final SessionFactory sessionFactory;

    public void savePosition(Positions a) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(a);
            session.getTransaction().commit();
        }
    }

    public Optional<Positions> findPositionByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Optional<Positions> pos = session.createQuery("from positions u where u.positionName = :pos", Positions.class)
                    .setParameter("pos", name)
                    .getResultStream().findFirst();
            if (pos.isPresent()) {
                return pos;
            } else {
                throw new RuntimeException("ВВЕДЕННАЯ ПОЗИЦИЯ НЕ СУЩЕСТУВУЕТ В БАЗЕ ДАННЫХ");
            }
        }
    }
    public List<Positions> findAllPositions() {
        try (Session session = sessionFactory.openSession()) {
            List<Positions> pos = session.createQuery("from positions", Positions.class).getResultList();
            if (pos.size()>0) {
                return pos;
            } else {
                throw new RuntimeException("НИКАКИХ ПОЗИЦИЙ НЕ СУЩЕСТУВУЕТ В БД");
            }
        }
    }
    public Positions findPositionsById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Positions pos = session.find(Positions.class, id);
            return pos;
        }
    }

    public void updatePosition(int id, String name) {
        try (Session session = sessionFactory.openSession()) {
            Positions user = session.find(Positions.class, id);
            session.getTransaction().begin();
            user.setPositionName(name);
            session.getTransaction().commit();
        }
    }

    public void deletePosition(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Positions pos = session.find(Positions.class, id);
            if (pos != null) {
                session.remove(pos);
            } else throw new RuntimeException("Позицию удалить нельзя, так его нету в базе данных");
            session.getTransaction().commit();
        }
    }
}
