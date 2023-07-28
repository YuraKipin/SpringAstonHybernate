package kipin.spring.AstonHybernate.controllers.DTOs;

import java.util.List;
public class WorkersDTO {
    int id;
    String name;
    String surname;
    String position;
    List<String> projects;
    public List<String> getProjects() {
        return projects;
    }
    public void setProjects(List<String> projects) {
        this.projects = projects;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
