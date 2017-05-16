package pl.edu.pjatk.lab8.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pl.edu.pjatk.lab8.Domain.Item;

public interface ItemManager {

    Connection getConnection();
    int addItem(Item item) throws SQLException;
    int deleteItem(int id) throws SQLException;
    int updateItem(Item item) throws SQLException;
    Item selectItem(int id) throws SQLException;
    List<Item> getAllItems() throws  SQLException;
    int countRows() throws SQLException;
}
