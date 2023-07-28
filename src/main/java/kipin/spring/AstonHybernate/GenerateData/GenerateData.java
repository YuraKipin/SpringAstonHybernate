package kipin.spring.AstonHybernate.GenerateData;

import kipin.spring.AstonHybernate.controllers.Converter.Converter;
import kipin.spring.AstonHybernate.service.PositionsService;
import kipin.spring.AstonHybernate.service.ProjectsService;
import kipin.spring.AstonHybernate.service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/dataGen")
@RestController
public class GenerateData {
    private WorkersService workersService;
    private PositionsService positionsService;
    private ProjectsService projectsService;

    public PositionsService getPositionsService() {
        return positionsService;
    }

    @Autowired
    public void setPositionsService(PositionsService positionsService) {
        this.positionsService = positionsService;
    }

    public ProjectsService getProjectsService() {
        return projectsService;
    }

    @Autowired
    public void setProjectsService(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    private Converter converter;

    public Converter getConverter() {
        return converter;
    }

    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }


    public WorkersService getWorkersService() {
        return workersService;
    }

    @Autowired
    public void setWorkersService(WorkersService workersService) {
        this.workersService = workersService;
    }

    @GetMapping
    public void getAllWorkers() {
        positionsService.savePositions("мл разработчик");
        positionsService.savePositions("ст разработчик");
        positionsService.savePositions("Собутыл");
        positionsService.savePositions("Мясник");

        projectsService.saveProjects("пить чай в пятницу");
        projectsService.saveProjects("Разработка");
        projectsService.saveProjects("Сантехническое обслуживание");

        workersService.saveWorker("Petya", "Erm", "Собутыл");
        workersService.saveWorker("Eugen", "Kasp", "мл разработчик");
        workersService.saveWorker("Yura", "kipin", "ст разработчик");
        workersService.saveWorker("Дмитрий", "Уткин", "Мясник");

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1));

        projectsService.addWorkersToProject("Сантехническое обслуживание", list);
        projectsService.addWorkersToProject("Разработка", list2);
    }
}
