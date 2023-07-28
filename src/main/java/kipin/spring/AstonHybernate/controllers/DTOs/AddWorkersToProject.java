package kipin.spring.AstonHybernate.controllers.DTOs;

import java.util.List;

public class AddWorkersToProject {
    String name;
   List<Integer> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
