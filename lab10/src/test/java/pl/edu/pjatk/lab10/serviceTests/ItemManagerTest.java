package pl.edu.pjatk.lab10.serviceTests;

import java.net.URL;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.edu.pjatk.lab10.domain.Item;
import pl.edu.pjatk.lab10.service.ItemManager;
import pl.edu.pjatk.lab10.service.ItemManagerImpl;

@RunWith(JUnit4.class)
public class ItemManagerTest extends DBTestCase {
	ItemManager itemManager;

    public ItemManagerTest() throws Exception {
        super("itemManagerImpl test");
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.TRUNCATE_TABLE;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return this.getDataSet("dataset-pm-add.xml");
    }


    protected IDataSet getDataSet(String datasetName) throws Exception {
        URL url = getClass().getClassLoader().getResource(datasetName);
        FlatXmlDataSet ret = new FlatXmlDataSetBuilder().build(url.openStream());
        return ret;
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        itemManager = new ItemManagerImpl(this.getConnection().getConnection());
    }
	
	@Test
	public void addingTest() throws Exception {
		Item item = new Item();
		item.setName("Okno");
		

		assertEquals(1, itemManager.addItem(item));


        // Data verification

        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("Item");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-pm-add-check.xml");
        ITable expectedTable = expectedDataSet.getTable("Item");

        Assertion.assertEquals(expectedTable, filteredTable);
    }
	
	@Test
	public void removeItemTest() throws Exception {
		Item itemToRemove;
		int count;
		List<Item> items = itemManager.getAllItems();
		count = items.size();
		itemToRemove = items.get(0);

		assertEquals(1,itemManager.removeItem(itemToRemove));
		assertEquals(null, itemManager.getItem(itemToRemove.getId()).getName());
		assertEquals(count-1, itemManager.getAllItems().size());
	}
	
	@Test
	public void updateItemTest() throws Exception{
		long id;
		Item itemFromDB;
		Item itemToUpdate = new Item();
		itemFromDB = itemManager.getAllItems().get(0);
		id = itemFromDB.getId();
		itemToUpdate.setId(id);
		itemToUpdate.setName("Kalkulator");
		
		assertEquals(1, itemManager.updateItem(itemToUpdate));
		itemFromDB = itemManager.getItem(itemFromDB.getId());
		
		assertEquals("Kalkulator", itemFromDB.getName());
		assertEquals(id, itemFromDB.getId());
		assertNotSame("Lampa", itemFromDB.getName());
	}

}
