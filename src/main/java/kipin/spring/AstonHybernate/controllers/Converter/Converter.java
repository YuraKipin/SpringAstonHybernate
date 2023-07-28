package kipin.spring.AstonHybernate.controllers.Converter;

import kipin.spring.AstonHybernate.controllers.DTOs.AddWorkersToProject;
import kipin.spring.AstonHybernate.controllers.DTOs.PositionsDTO;
import kipin.spring.AstonHybernate.controllers.DTOs.ProjectsDTO;
import kipin.spring.AstonHybernate.controllers.DTOs.WorkersDTO;
import kipin.spring.AstonHybernate.persist.Entity.Positions;
import kipin.spring.AstonHybernate.persist.Entity.Projects;
import kipin.spring.AstonHybernate.persist.Entity.Workers;
import kipin.spring.AstonHybernate.service.PositionsService;
import kipin.spring.AstonHybernate.service.ProjectsService;
import kipin.spring.AstonHybernate.service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {
    WorkersService workersService;
    ProjectsService projectsService;
    PositionsService positionsService;

    public WorkersService getWorkersService() {
        return workersService;
    }

    @Autowired
    public void setWorkersService(WorkersService workersService) {
        this.workersService = workersService;
    }

    public ProjectsService getProjectsService() {
        return projectsService;
    }

    @Autowired
    public void setProjectsService(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    public PositionsService getPositionsService() {
        return positionsService;
    }

    @Autowired
    public void setPositionsService(PositionsService positionsService) {
        this.positionsService = positionsService;
    }

    //WORKERS
    //*****************************************************************************//
    public List<WorkersDTO> findWorkersByProjectid(@PathVariable int id) {
        Projects prj = projectsService.findProjectById(id);
        List<Workers> wrk = prj.getWorkers();
        return transformWorkerToDTO(wrk);
    }

    public List<WorkersDTO> findAllWorkers() {
        List<Workers> workers = workersService.findAll();
        List<WorkersDTO> workersDTO = new ArrayList<>(workers.size());
        return transformWorkerToDTO(workers);
    }

    public List<WorkersDTO> transformWorkerToDTO(List<Workers> workers) {
        List<WorkersDTO> workersDTO = new ArrayList<>(workers.size());
        for (int i = 0; i < workers.size(); i++) {
            workersDTO.add(new WorkersDTO());
            workersDTO.get(i).setId(workers.get(i).getId());
            workersDTO.get(i).setName(workers.get(i).getName());
            workersDTO.get(i).setSurname(workers.get(i).getSurname());
            workersDTO.get(i).setPosition(workers.get(i).getPosition().getPositionName());
            workersDTO.get(i).setProjects(workers.get(i).getProjects().
                    stream().map(x -> x.getProjectName()).
                    collect(Collectors.toList()));
        }
        return workersDTO;
    }

    public WorkersDTO findWorkerById(int id) {
        Workers worker = workersService.findWorkerById(id);
        WorkersDTO worker2 = new WorkersDTO();
        worker2.setName(worker.getName());
        worker2.setSurname(worker.getSurname());
        worker2.setPosition(worker.getPosition().getPositionName());
        worker2.setProjects(worker.getProjects().stream().map(x -> x.getProjectName()).collect(Collectors.toList()));
        return worker2;
    }

    public void saveWorker(WorkersDTO workersDTO) {
        workersService.saveWorker(workersDTO.getName(), workersDTO.getSurname(), workersDTO.getPosition());
    }

    public void updateWorker(WorkersDTO workersDTO) {
        workersService.updateWorker(workersDTO.getId(), workersDTO.getName(), workersDTO.getSurname(), workersDTO.getPosition());
    }

    //PROJECTS
    //*******************************************************************************
    public void updateProject(ProjectsDTO projectsDTO) {
        projectsService.updateProject(projectsDTO.getId(), projectsDTO.getProjectName());
    }

    public void saveProject(ProjectsDTO projectsDTO) {
        projectsService.saveProjects(projectsDTO.getProjectName());
    }

    public List<ProjectsDTO> findAllProjects() {
        List<Projects> projects = projectsService.findAll();
        List<ProjectsDTO> projectsDTO = new ArrayList<>(projects.size());

        for (int i = 0; i < projects.size(); i++) {
            ProjectsDTO prj = new ProjectsDTO();
            prj.setId(projects.get(i).getId());
            prj.setProjectName(projects.get(i).getProjectName());
            projectsDTO.add(prj);

        }
        return projectsDTO;
    }
    public void addWorkersToProject(AddWorkersToProject addWorkersToProject) {
        projectsService.addWorkersToProject(addWorkersToProject.getName(),addWorkersToProject.getList());
    }

    public void updateWorkersOnProjects(AddWorkersToProject addWorkersToProject) {
        projectsService.updateWorkersOnProjects(addWorkersToProject.getName(),addWorkersToProject.getList());
    }

    //***********************************************************
    //ДОЛЖНОСТИ
    public List<PositionsDTO> findAllPositions() {
        List<Positions> positions = positionsService.findAllPositions();
        List<PositionsDTO> projectsDTO = new ArrayList<>(positions.size());

        for (int i = 0; i < positions.size(); i++) {
            PositionsDTO prj = new PositionsDTO();
            prj.setId(positions.get(i).getId());
            prj.setPositionName(positions.get(i).getPositionName());
            projectsDTO.add(prj);

        }
        return projectsDTO;
    }

    public List<WorkersDTO> findWorkersByPositionsId(@PathVariable int id) {
        Positions pos = positionsService.findPositionsById(id);
        int posId = pos.getId();
        List<Workers> workers = workersService.findAll().stream().filter(x -> x.getPosition().getId() == posId).collect(Collectors.toList());
        return transformWorkerToDTO(workers);
    }

    public void savePosition(PositionsDTO positionsDTO) {
        positionsService.savePositions(positionsDTO.getPositionName());
    }
    public void updatePosition(PositionsDTO positionsDTO) {
        positionsService.updatePosition(positionsDTO.getId(), positionsDTO.getPositionName());
    }

}
