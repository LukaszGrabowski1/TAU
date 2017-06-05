package pl.edu.pjatk.tau.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Task.findAll", query = "Select i from Task i"),
        @NamedQuery(name = "Task.byId", query = "Select i from Task  i where i.id = :id")
})
public class Task {

    private Long id;
    private String name;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
