package kipin.spring.AstonHybernate.controllers.PositionsController;
import kipin.spring.AstonHybernate.controllers.Converter.Converter;
import kipin.spring.AstonHybernate.controllers.DTOs.PositionsDTO;
import kipin.spring.AstonHybernate.controllers.DTOs.ProjectsDTO;
import kipin.spring.AstonHybernate.controllers.DTOs.WorkersDTO;
import kipin.spring.AstonHybernate.service.PositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/positions")
@RestController
public class PositionsController {

    private PositionsService positionsService;
    private Converter converter;

    public Converter getConverter() {
        return converter;
    }

    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @GetMapping
    public List<PositionsDTO> getAllPositions() {
        return converter.findAllPositions();
    }

    @GetMapping("/{id}")
    public List<WorkersDTO> findWorkersByPositionId(@PathVariable int id) {
        return converter.findWorkersByPositionsId(id);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteProjectById(@PathVariable int id) {
        positionsService.deletePosition(id);
    }

    @PostMapping
    public void savePositions(@RequestBody PositionsDTO positionsDTO) {
        converter.savePosition(positionsDTO);
    }

    @PostMapping("/update")
    public void UpdateProject(@RequestBody PositionsDTO positionsDTO) {
        converter.updatePosition(positionsDTO);
    }

}