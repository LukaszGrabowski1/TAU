package pl.edu.pjatk.lab8;

import org.junit.After;
import org.junit.Test;

import pl.edu.pjatk.lab8.Domain.Item;
import pl.edu.pjatk.lab8.Service.ItemManagerImpl;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.junit.Assert.*;

public class ItemManagerImplTest {

    ItemManagerImpl itemManager = new ItemManagerImpl();

    public ItemManagerImplTest() throws SQLException {}

    @Test
    public void connectionCheck() {
        assertNotNull(itemManager.getConnection());
    }

    @Test
    public void createItemTest() throws SQLException {
        Item item = new Item(12,"Gniazdko","15.12.2016",5);
        assertEquals(1,itemManager.addItem(item));
        assertNotNull(itemManager.selectItem(item.getId()));
    }

    @Test
    public void deleteItemTest() throws SQLException {
        Item item = new Item(10,"Krzesło","14.05.2018",15);
        itemManager.addItem(item);
        int beforeDelete = itemManager.countRows();
        assertEquals(1,itemManager.deleteItem(item.getId()));
        int afterDelete = itemManager.countRows();
        assertNotSame(beforeDelete,afterDelete);
        assertNotSame(item.getId(),itemManager.selectItem(item.getId()).getId());
    }

    @Test
    public void editItemTest() throws SQLException {
        Item itemToEdit = new Item(73,"Laptop Samsung","14.03.2013",3);
        Item itemEdited = new Item(73,"Monitor ECO","17.06.2015",7);
        itemManager.addItem(itemToEdit);
        assertEquals(1,itemManager.updateItem(itemEdited));
        assertNotSame(itemToEdit.getAvailable(),itemManager.selectItem(itemEdited.getId()).getAvailable());
    }

    @Test
    public void selectItemTest() throws SQLException {
        Item item = new Item(123,"Kable USB","2017-12-12",32);
        itemManager.addItem(item);
        assertEquals(item.getId(),itemManager.selectItem(item.getId()).getId());
    }

    @After
    public void clearup() throws SQLException{
        itemManager.clear();
    }

}