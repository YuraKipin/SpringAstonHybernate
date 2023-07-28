package kipin.spring.AstonHybernate.service;

import kipin.spring.AstonHybernate.persist.Entity.Projects;
import kipin.spring.AstonHybernate.persist.Entity.Workers;
import kipin.spring.AstonHybernate.persist.Repository.ProjectsRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {
    ProjectsRepository projectsRep;

    public ProjectsRepository getProjectsRep() {
        return projectsRep;
    }

    WorkersService workersService;

    @Autowired
    public void setWorkersService(WorkersService workersService) {
        this.workersService = workersService;
    }

    @Autowired
    public void setProjectsRep(ProjectsRepository projectsRep) {
        this.projectsRep = projectsRep;
    }

    public void saveProjects(String prj) {
        Projects a = new Projects();
        a.setProjectName(prj);
        projectsRep.saveProjects(a);
    }
    public List<Projects> findAll() { return projectsRep.findAllProjects(); }

    public void addWorkersToProject(String name, List<Integer> a) {

        Optional<Projects> prjName = projectsRep.findProjectByName(name);
        if(!prjName.isPresent()) {throw new RuntimeException("Проекта с таким именем не найдено!");}
        List<Workers> workers = workersService.findWorkersById(a);
        Projects prj = prjName.get();
        projectsRep.addWorkersToProject(prj, workers);
    }

    public void updateProject(int id, String name) {
        projectsRep.UpdateProject(id,name);
    }

    public Projects findProjectById(int id) {
        Projects prj= projectsRep.findProjectById(id);
        if (prj != null) {
            return prj;
        } else throw new RuntimeException("Запрашиваемого работника не существует в БД");
    }

    public void updateWorkersOnProjects(String name, List<Integer> a) {
        Optional<Projects> prjName = projectsRep.findProjectByName(name);
        List<Workers> workers = workersService.findWorkersById(a);
        Projects prj = prjName.get();
        projectsRep.updateWorkersOnProjects(prj, workers);
    }
    public void deleteProject(int id) {
        projectsRep.deleteProject(id);
    }

}



