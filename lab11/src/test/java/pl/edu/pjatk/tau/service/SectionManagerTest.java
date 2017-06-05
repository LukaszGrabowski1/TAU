package pl.edu.pjatk.tau.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjatk.tau.domain.Task;
import pl.edu.pjatk.tau.domain.Worker;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
@Rollback
@Commit
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class SectionManagerTest {

    @Autowired
    SectionManager sectionManager;



    @Test
    @DatabaseSetup("/dbSetup.xml")
    @ExpectedDatabase(value = "/dataset-deleteWorker.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteWorkerTest() {
        assertEquals(2, sectionManager.getAllTasks().size());
        Worker workerToDelete = sectionManager.findWorkerByLogin("kowal");
        assertEquals("kowal", workerToDelete.getLogin());
        sectionManager.deleteWorker(workerToDelete);
        assertEquals(1, sectionManager.getAllWorkers() .size());
        
    }


    @Test
    @DatabaseSetup("/dbSetup.xml")
    public void selectWorkerTest(){
        assertEquals(2,sectionManager.getAllWorkers().size());
        Worker selectedWorker = sectionManager.findWorkerByLogin("kowal");
        Long id = selectedWorker.getId();
        assertEquals("kowal", selectedWorker.getLogin());
        assertEquals("qherbi23yh14ih3", selectedWorker.getPassword());
        selectedWorker = null;
        selectedWorker = sectionManager.findWorkerById(id);
        assertEquals("kowal", selectedWorker.getLogin());
        assertEquals("qherbi23yh14ih3", selectedWorker.getPassword());
        

    }

    @Test
    @DatabaseSetup("/dbSetup.xml")
    @ExpectedDatabase(value = "/dataset-editWorker.xml",
        assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void editWorkerTest(){
        assertEquals(2,sectionManager.getAllWorkers().size());

        Worker workerToEdit = new Worker();
        workerToEdit.setLogin("grat12");
        workerToEdit.setPassword("9fjsH43FJ");
        sectionManager.addWorker(workerToEdit);
        
        System.out.println("EDIT " + workerToEdit);
        
        Worker workerFromDB = sectionManager.findWorkerByLogin("grat12");
        assertEquals("grat12",workerFromDB.getLogin());

        workerFromDB.setLogin("Edited");
        workerFromDB.setPassword("3uy4ip2o3u4y");
        sectionManager.editWorker(workerToEdit);
        assertEquals("Edited",sectionManager.findWorkerByLogin("Edited").getLogin());

    }

    @Test
    @DatabaseSetup("/dbSetup.xml")
    @ExpectedDatabase(value = "/dataset-addWorker.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addWorkerTest() {
        assertEquals(2, sectionManager.getAllWorkers().size());
        Worker workerToAdd = new Worker();
        workerToAdd.setLogin("maras");
        workerToAdd.setPassword("123");
        sectionManager.addWorker(workerToAdd);
        
        assertEquals(3, sectionManager.getAllWorkers().size());
        assertEquals("maras",sectionManager.findWorkerByLogin("maras").getLogin());
    }
    
    @Test
    @DatabaseSetup("/dbSetup.xml")
    public void tradeItem(){
        assertEquals(2, sectionManager.getAllTasks().size());
        Worker worker1 = sectionManager.findWorkerById(3L);
        Worker worker2 = sectionManager.findWorkerById(Long.valueOf(4));
        Task task = sectionManager.findTaskById(1);

        Long p1 = worker1.getId();
        Long p2 = worker2.getId();
        Long i  = task.getId();

        sectionManager.tradeWithWorker(p1,p2,i);
        assertEquals(1,worker2.getTasks().size());
        assertEquals(0,worker1.getTasks().size());
    }


}
