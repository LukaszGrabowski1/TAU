package pl.edu.pjatk.lab10.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pl.edu.pjatk.lab10.domain.Item;

public interface ItemManager {
	public Connection getConnection();
	public int addItem(Item item);
	public int removeItem(Item item);
	public int updateItem(Item item);
	public Item getItem(long id);
	public List<Item> getAllItems();
}
