package kipin.spring.AstonHybernate.persist.Repository;


import kipin.spring.AstonHybernate.persist.Entity.Positions;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kipin.spring.AstonHybernate.persist.Entity.Workers;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class WorkersRepository {
@Autowired
    private final SessionFactory sessionFactory;
    PositionsRepository posRep;

    @Autowired
    public void setPosRep(PositionsRepository posRep) {
        this.posRep = posRep;
    }

    public void saveWorker(Workers a) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(a);
            session.getTransaction().commit();
        }
    }

    public List<Workers> findWorkersById(List<Integer> list) throws RuntimeException {
        try (Session session = sessionFactory.openSession()) {
            List<Workers> prj = session.createQuery("from workers p where p.id in :param", Workers.class).
                    setParameter("param", list).getResultList();
            return prj;
        }
    }

    public void deleteWorker(int id) {

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Workers user = session.find(Workers.class, id);
            session.remove(user);
            if (user != null) {
                session.remove(user);
            } else throw new RuntimeException("Работника удалить нельзя, так его нету в базе данных");
            session.getTransaction().commit();
        }
    }

    public void UpdateWorker(int id, String name, String surname, String position) {

        try (Session session = sessionFactory.openSession()) {

            Workers user = session.find(Workers.class, id);
            if(user==null) {throw new RuntimeException("пользователя с таким ID не найдено в БД");}
            session.getTransaction().begin();
            user.setName(name);
            user.setSurname(surname);
            Optional<Positions> pos = posRep.findPositionByName(position);
            if(!pos.isPresent()) {throw new RuntimeException("Такой должности в БД не найдено");}
            pos.get();
            user.setPosition(pos.get());
            session.getTransaction().commit();
        }
    }

    public List<Workers> findAll() {
        List<Workers> workerList;
        try (Session session = sessionFactory.openSession()) {
            workerList = session.createQuery("from workers", Workers.class)
                    .getResultList();
            return workerList;
        }
    }

    public Workers findWorkerById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Workers user = session.find(Workers.class, id);
            return user;
        }
    }
}
