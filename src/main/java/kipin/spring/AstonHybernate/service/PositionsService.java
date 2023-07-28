package kipin.spring.AstonHybernate.service;


import kipin.spring.AstonHybernate.persist.Entity.Positions;
import kipin.spring.AstonHybernate.persist.Entity.Projects;
import kipin.spring.AstonHybernate.persist.Repository.PositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionsService {

    PositionsRepository positionRep;

    public PositionsRepository getPositionRep() {
        return positionRep;
    }

    @Autowired
    public void setPositionRep(PositionsRepository positionRep) {
        this.positionRep = positionRep;
    }

    public void savePositions(String pos) {
        Positions a = new Positions();
        a.setPositionName(pos);
        positionRep.savePosition(a);
    }

    public void deletePosition(int id) {
        positionRep.deletePosition(id);
    }

    public Optional<Positions> findPositionByName(String name) {
        return positionRep.findPositionByName(name);
    }

    public List<Positions> findAllPositions() {
        return positionRep.findAllPositions();

    }
    public Positions findPositionsById(int id) {
        Positions pos= positionRep.findPositionsById(id);
        if (pos != null) {
            return pos;
        } else throw new RuntimeException("Запрашиваемой позиции не существует в БД");
    }
    public void updatePosition(int id, String name) {
        positionRep.updatePosition(id,name);
    }

}
