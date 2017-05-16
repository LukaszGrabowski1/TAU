package pl.edu.pjatk.lab8.Service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.sqlite.SQLite;

import pl.edu.pjatk.lab8.Domain.Item;


@Component
public class ItemManagerImpl implements ItemManager {

    private Connection connection;

//    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    private String url = "jdbc:sqlite:/Users/lukasz/workspace/TAU/lab8/src/database/database.sqlite";
    
    private String url = "jdbc:hsqldb:hsql://localhost/workdb";
    private String createTableNotes = "CREATE TABLE " +
            "Items( id INT NOT NULL ,"
            + "name VARCHAR(350) NOT NULL , " 
            + "dateOfUpdate VARCHAR(20) NOT NULL ,"
            + " numberAvailable INT NOT NULL ,"
            + " PRIMARY KEY (id))";
    
    

    private PreparedStatement addItemStmt;
    private PreparedStatement deleteItemStmt;
    private PreparedStatement editItemStmt;
    private PreparedStatement getAllItemsStmt;
    private PreparedStatement selectItemStmt;
    private Statement statement;

    public ItemManagerImpl() throws SQLException{
    	DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
        connection = DriverManager.getConnection (url, "SA", "");
        statement = connection.createStatement();

        ResultSet rs = connection.getMetaData().getTables(null, null, "%",
                null);

        boolean tableExist = false;

        while (rs.next()){
            if("Items".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
                tableExist = true;
                break;
            }
        }

        if(!tableExist)
            statement.executeUpdate(createTableNotes);

        addItemStmt = connection.prepareStatement("INSERT INTO Items (id,name, dateOfUpdate,numberAvailable) VALUES (?,?,?,?)");
        editItemStmt = connection.prepareStatement("UPDATE Items SET name= ?,dateOfUpdate= ?,numberAvailable=? WHERE id = ?");
        deleteItemStmt = connection.prepareStatement("DELETE FROM Items WHERE id = ?");
        getAllItemsStmt = connection.prepareStatement("SELECT * FROM Items");
        selectItemStmt = connection.prepareStatement("SELECT * FROM Items WHERE id = ?");
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public int addItem(Item item) throws SQLException {
        int count = 0;
        try{
            addItemStmt.setInt(1,item.getId());
            addItemStmt.setString(2,item.getName());
            addItemStmt.setString(3,item.getDate());
            addItemStmt.setInt(4,item.getAvailable());

            count = addItemStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteItem(int id) throws SQLException {
        int count = 0;
        try{
            deleteItemStmt.setInt(1,id);
            count =  deleteItemStmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int updateItem(Item item) throws SQLException {
        int count = 0;
        try{
            editItemStmt.setString(1,item.getName());
            editItemStmt.setString(2,item.getDate());
            editItemStmt.setInt(3,item.getAvailable());
            editItemStmt.setInt(4,item.getId());
            count = editItemStmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Item selectItem(int id) throws SQLException{
        int count = 0;
        Item n = new Item();
        try{
            selectItemStmt.setInt(1,id);
            ResultSet rs = selectItemStmt.executeQuery();
            while(rs.next()){
                n.setId(rs.getInt("id"));
                n.setName(rs.getString("name"));
                n.setDate(rs.getString("dateOfUpdate"));
                n.setAvailable(rs.getInt("numberAvailable"));
                count = 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Item> getAllItems() throws SQLException{
        List<Item> items = new ArrayList<Item>();

        try{
            ResultSet rs = getAllItemsStmt.executeQuery();
            while (rs.next()){
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDate(rs.getString("dateOfUpdate"));
                item.setAvailable(rs.getInt("numberAvailable"));

                items.add(item);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public int countRows() throws SQLException {
        int count = 0;
        try{
            ResultSet rs = statement.executeQuery("SELECT count(*) as count FROM Items");
            while(rs.next()){
                count = rs.getInt("count");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public void clear() throws SQLException{
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Items");
           if(stmt.executeUpdate() == 1){
           }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
