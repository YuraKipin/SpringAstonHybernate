package kipin.spring.AstonHybernate.service;


import kipin.spring.AstonHybernate.persist.Entity.Positions;



import kipin.spring.AstonHybernate.persist.Repository.WorkersRepository;
import kipin.spring.AstonHybernate.persist.Entity.Workers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkersService {
    String name;
    String surname;
    String position;
    WorkersRepository workRep;
    PositionsService positionService;

    public PositionsService getPositionService() {
        return positionService;
    }

    @Autowired
    public void setPositionsService(PositionsService positionService) {
        this.positionService = positionService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public WorkersRepository getWorkRep() {
        return workRep;
    }

    @Autowired
    public void setWorkRep(WorkersRepository workRep) {
        this.workRep = workRep;
    }

    public void saveWorker(String name, String surname, String pos) {
        Workers a = new Workers();
        a.setName(name);
        a.setSurname(surname);
        Optional<Positions> position = positionService.findPositionByName(pos);
        if(!position.isPresent()) {throw new RuntimeException("Такой позиции не существует");}
        a.setPosition(position.get());
        workRep.saveWorker(a);
    }

    public List<Workers> findWorkersById(List<Integer> list) {
        List<Workers> work = workRep.findWorkersById(list);
        if (work.stream().count() == 0) {
            throw new RuntimeException("РАБОТНИКОВ С ТАКИМИ АЙДИШНИКАМИ НЕ ОБНАРУЖЕНО");
        } else {
            return workRep.findWorkersById(list);
        }
    }

    public void deleteWorker(int id) {
        workRep.deleteWorker(id);
    }

    public void updateWorker(int id, String name, String surname, String position) {
        workRep.UpdateWorker(id, name, surname, position);
    }

    public List<Workers> findAll() {
       return workRep.findAll();
    }
    public Workers findWorkerById(int id) {
    Workers worker= workRep.findWorkerById(id);
        if (worker != null) {
            return worker;
        } else throw new RuntimeException("Запрашиваемого работника не существует в БД");
    }
}
