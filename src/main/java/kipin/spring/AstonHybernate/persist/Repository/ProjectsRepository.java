package kipin.spring.AstonHybernate.persist.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import kipin.spring.AstonHybernate.persist.Entity.Projects;
import kipin.spring.AstonHybernate.persist.Entity.Workers;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ProjectsRepository {
    @Autowired
    private final SessionFactory sessionFactory;


    public void saveProjects(Projects a) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(a);
            session.getTransaction().commit();
        }
    }

    public Optional<Projects> findProjectByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Optional<Projects> prj = session.createQuery("from projects p where p.projectName =:param",Projects.class).setParameter("param", name).getResultStream().findFirst();
            if (prj.isPresent()) {
                return prj;
            } else {
                throw new RuntimeException("Введенного проекта не существует");
            }
        }
    }
    public Projects findProjectById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Projects prj = session.find(Projects.class, id);
            return prj;
        }
    }

    public List<Projects> findAllProjects() {
        try (Session session = sessionFactory.openSession()) {
            List<Projects> prj = session.createQuery("from projects",Projects.class).getResultStream().collect(Collectors.toList());
            if (prj.size()>0) {
                return prj;
            } else {
                throw new RuntimeException("Проектов не найдено");
            }
        }
    }

    public void addWorkersToProject(Projects prj, List<Workers> work) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            prj.setWorkers(work);
            session.merge(prj);
            session.getTransaction().commit();
        }
    }
    public void updateWorkersOnProjects(Projects prj, List<Workers> work) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            prj.setWorkers(work);
            session.merge(prj);
            session.getTransaction().commit();
        }
    }
    public void deleteProject(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Projects project = session.find(Projects.class, id);
            if (project != null) {
                session.remove(project);
            } else throw new RuntimeException("Проект удалить нельзя, так его нету в базе данных");
            session.getTransaction().commit();
        }
    }


    public void UpdateProject(int id, String name) {
        try (Session session = sessionFactory.openSession()) {
            Projects prj = session.find(Projects.class, id);
            session.getTransaction().begin();
            prj.setProjectName(name);
            session.getTransaction().commit();
        }
    }
}
