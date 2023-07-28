package kipin.spring.AstonHybernate.controllers.ProjectsController;



import kipin.spring.AstonHybernate.controllers.Converter.Converter;
import kipin.spring.AstonHybernate.controllers.DTOs.AddWorkersToProject;
import kipin.spring.AstonHybernate.controllers.DTOs.ProjectsDTO;
import kipin.spring.AstonHybernate.controllers.DTOs.WorkersDTO;
import kipin.spring.AstonHybernate.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/projects")
@RestController
public class ProjectsController {
    private ProjectsService projectsService;
    private Converter converter;

    public Converter getConverter() {
        return converter;
    }

    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public ProjectsService getProjectsService() {
        return projectsService;
    }

    @Autowired
    public void setProjectsService(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public List<ProjectsDTO> getAllProjects() {
        return converter.findAllProjects();
    }

    @GetMapping("/{id}")
    public List<WorkersDTO> findWorkersByProjectId(@PathVariable int id) {
        return converter.findWorkersByProjectid(id);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteProjectById(@PathVariable int id) {
        projectsService.deleteProject(id);
    }

    @PostMapping
    public void saveProject(@RequestBody ProjectsDTO projectsDTO) {
        converter.saveProject(projectsDTO);
    }

    @PostMapping("/update")
    public void UpdateProject(@RequestBody ProjectsDTO workersDTO) {
        converter.updateProject(workersDTO);
    }

    @PostMapping("/addWorkersToProject")
    public void addWorkersToProject(@RequestBody AddWorkersToProject addWorkersToProject) {
        converter.addWorkersToProject(addWorkersToProject);
    }
    @PostMapping("/updateWorkersToProject")
    public void updateWorkersToProject(@RequestBody AddWorkersToProject addWorkersToProject) {
        converter.updateWorkersOnProjects(addWorkersToProject);
    }

}