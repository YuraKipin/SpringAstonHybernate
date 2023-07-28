package kipin.spring.AstonHybernate.persist.Entity;

import jakarta.persistence.*;

import java.util.List;


@Entity(name="positions")
@Table(name="positions")
public class Positions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "name",unique = true)
    String positionName;

    public int getId() {
        return id;
    }

    public Positions(String positionName) {
        this.positionName = positionName;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public Positions() {
    };

    @OneToMany(mappedBy="position",orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Workers> workersID;
}
