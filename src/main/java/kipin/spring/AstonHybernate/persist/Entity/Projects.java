package kipin.spring.AstonHybernate.persist.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="projects")
@Table(name="projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "projectName",unique = true)
    String projectName;

    public Projects() {
    }

    public Projects(String projectName) {
        this.projectName = projectName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Workers> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Workers> workers) {
        this.workers = workers;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "workers_projects",
            joinColumns = @JoinColumn(name = "projects_id"),
            inverseJoinColumns = @JoinColumn(name = "workers_id"))
    private List<Workers> workers;

}
