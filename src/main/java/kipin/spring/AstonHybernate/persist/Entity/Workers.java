package kipin.spring.AstonHybernate.persist.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "workers")
@Table(name = "workers")
public class Workers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;
    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    public Workers(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public Workers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pos_id")
    Positions position;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "workers_projects",
            joinColumns = @JoinColumn(name = "workers_id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id"))
    private List<Projects> projects;



}
