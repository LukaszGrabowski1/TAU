package pl.edu.pjatk.tau.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
@NamedQuery(name = "Worker.getAll", query = "Select p from Worker p"),
@NamedQuery(name = "Worker.byId", query = "Select p from Worker p where p.id = :id"),
@NamedQuery(name="Worker.byLogin", query = "Select p from Worker p where p.login = :login")
})
public class Worker {

    private Long id;
    private String login;
    private String password;
    private List<Task> tasks;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

	@Override
	public String toString() {
		return "Worker [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
    

}
