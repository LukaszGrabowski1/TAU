package pl.edu.pjatk.lab7;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import pl.edu.pjatk.lab7.domain.Item;
import pl.edu.pjatk.lab7.service.ItemManager;


public class ItemManagerTest {

    ItemManager itemManager = new ItemManager();

    public ItemManagerTest() throws SQLException{
    	
    }

    @After
    public void cleanup() throws SQLException{
    	itemManager.resetDB();
    }

    @Test
    public void connectionCheck (){ 
    	assertNotNull(itemManager.getConnection());
    }

    @Test
    public void addNewItemTest() throws SQLException{
    	Item item = new Item();
    	item.setDate("18.10.2018");
    	item.setName("Klej do tapet");
    	item.setAvailable(6);
    	
    	itemManager.removeAll();
    	
    	assertEquals(true, itemManager.addItem(item));
    }
    
    @Test
    public void deleteItemTest() throws SQLException {
    	Item item = new Item(41234, "Tapeta", 3);
    	itemManager.addItem(item);
    	assertEquals(true, itemManager.deleteItem(item));
    }

    @Test
    public void editItemTest() throws SQLException {
    	Item item = new Item();
    	item.setId(1135);
    	item.setName("Gniazdko");
    	item.setAvailable(3);
    	item.setDate("02.03.2012");
    	
    	itemManager.addItem(item);
    	Item itemFromDB = itemManager.getItem(item);
    	
    	assertEquals(item.getId(), itemFromDB.getId());
    	assertEquals(item.getName(), itemFromDB.getName());
    	assertEquals(item.getDate(), itemFromDB.getDate());
    	assertEquals(item.getAvailable(), itemFromDB.getAvailable());
    	
    	item.setName("Włącznik");
    	Assert.assertTrue(itemManager.updateItem(item));
    	
//    	Optional
//    	
//    	itemFromDB = itemManager.getItem(item);
//    	assertEquals(item.getId(), itemFromDB.getId());
//    	assertEquals(item.getName(), itemFromDB.getName());
//    	assertEquals(item.getDate(), itemFromDB.getDate());
//    	assertEquals(item.getAvailable(), itemFromDB.getAvailable());
    }


}
