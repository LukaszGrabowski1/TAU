package pl.edu.pjatk.tau.service;
import pl.edu.pjatk.tau.domain.Task;
import pl.edu.pjatk.tau.domain.Worker;

import java.util.List;


public interface SectionManager {

    void addWorker(Worker Worker);
    void editWorker(Worker Worker);
    void deleteWorker(Worker Worker);
    Worker findWorkerById(Long id);
    List<Worker> getAllWorkers();
    Worker findWorkerByLogin(String login);


    Long addNewTask(Task item);
    void deleteTask(Task item);
    void editTask(Task item);
    Task findTaskById(int id);
    List<Task> getAllTasks();

    void tradeWithWorker(Long idWorker1, Long idWorker2, Long idTask);
}
