package pl.edu.pjatk.tau.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjatk.tau.domain.Task;
import pl.edu.pjatk.tau.domain.Worker;


import java.util.LinkedList;
import java.util.List;

@Component
@Transactional
public class  SectionManagerImpl  implements SectionManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addWorker(Worker worker) {
        worker.setId(null);
        sessionFactory.getCurrentSession().save(worker);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void editWorker(Worker worker) {
        sessionFactory.getCurrentSession().update(worker);
    }

    @Override
    public void deleteWorker(Worker worker) {
        sessionFactory.getCurrentSession().delete(worker);
    }

    @Override
    public Worker findWorkerById(Long id) {
        return (Worker) sessionFactory.getCurrentSession().getNamedQuery("Worker.byId").setLong("id", id).uniqueResult();
    }

    @Override
    public Worker findWorkerByLogin(String login) {
        return (Worker) sessionFactory.getCurrentSession().getNamedQuery("Worker.byLogin").setString("login", login).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Worker> getAllWorkers() {
        return sessionFactory.getCurrentSession().getNamedQuery("Worker.getAll").list();
    }

    @Override
    public Long addNewTask(Task item) {
        item.setId(null);
        return (Long) sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public void deleteTask(Task item) {
        sessionFactory.getCurrentSession().delete(item);

    }

    @Override
    public void editTask(Task item) {
        sessionFactory.getCurrentSession().update(item);

    }

    @Override
    public Task findTaskById(int id) {
        return (Task) sessionFactory.getCurrentSession().getNamedQuery("Task.byId").setInteger("id", id).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> getAllTasks() {
        return sessionFactory.getCurrentSession().getNamedQuery("Task.findAll").list();
    }

    @Override
    public void tradeWithWorker(Long idWorker1, Long idWorker2, Long idTask) {

        Worker worker1 = sessionFactory.getCurrentSession().get(Worker.class, idWorker1);
        Worker worker2 = sessionFactory.getCurrentSession().get(Worker.class, idWorker2);
        Task item = sessionFactory.getCurrentSession().get(Task.class, idTask);

        boolean have = false;
        for (Task items : worker1.getTasks()) {
            if (items.getId().compareTo(item.getId()) == 0) {
                have = true;
                break;
            }
        }

        if (have) {
            if (worker2.getTasks() == null) {
                worker2.setTasks(new LinkedList<Task>());}
                worker1.getTasks().remove(0);
                worker2.getTasks().add(0,item);
            } else System.out.println("NIE MA TAKIEGO NA LYSCIE");
        }

    }

