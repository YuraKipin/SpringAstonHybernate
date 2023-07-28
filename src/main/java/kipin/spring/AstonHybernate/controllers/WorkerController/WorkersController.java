package kipin.spring.AstonHybernate.controllers.WorkerController;

import kipin.spring.AstonHybernate.controllers.Converter.Converter;
import kipin.spring.AstonHybernate.controllers.DTOs.WorkersDTO;
import kipin.spring.AstonHybernate.service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/workers")
@RestController
public class WorkersController {
    private WorkersService workersService;
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
    public List<WorkersDTO> getAllWorkers() {
        return converter.findAllWorkers();
    }

    @GetMapping("/{id}")
    public WorkersDTO findWorkerById(@PathVariable int id) {
        return converter.findWorkerById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE})
    public void deleteWorkerById(@PathVariable int id) {
        workersService.deleteWorker(id);
    }

    @PostMapping("/save")
    public void saveNewWorker(@RequestBody WorkersDTO workersDTO) {
        converter.saveWorker(workersDTO);
    }

    @PostMapping("/update")
    public void UpdateWorker(@RequestBody WorkersDTO workersDTO) {
        converter.updateWorker(workersDTO);
    }

}
