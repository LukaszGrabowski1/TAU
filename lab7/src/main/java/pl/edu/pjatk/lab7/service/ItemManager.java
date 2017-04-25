package pl.edu.pjatk.lab7.service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pjatk.lab7.domain.Item;

public class ItemManager {
	
    private Connection connection;

    private String url = "jdbc:mysql://localhost:8889/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String USER = "root";
    String PASS = "root";
    
    private String createTableItems = "CREATE TABLE " +
            "Items( `id` INT NOT NULL AUTO_INCREMENT ," +
            " `name` VARCHAR(350) NOT NULL , " +
            "`dateOfUpdate` VARCHAR(10) NOT NULL , " +
            "`numberAvailable` INT NOT NULL , PRIMARY KEY (`id`))";

    private PreparedStatement addItem;
    private PreparedStatement deleteItem;
    private PreparedStatement editItem;
    private PreparedStatement getAllItems;
    private PreparedStatement selectItem;
    private Statement statement;
    
  
    
    public ItemManager() throws SQLException{
    	
        connection = DriverManager.getConnection(url, USER, PASS);
        statement = connection.createStatement();

        ResultSet rs = connection.getMetaData().getTables(null, null, "%", null);

        boolean tableExist = false;

        while (rs.next()){
            if("Items".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
                tableExist = true;
                break;
            }
        }

        if(!tableExist)
        statement.executeUpdate(createTableItems);

        addItem = connection.prepareStatement("INSERT INTO Items (id,name,dateOfUpdate,numberAvailable) VALUES (?,?,?,?)");
        editItem = connection.prepareStatement("UPDATE Items SET `name`= ?,`dateOfUpdate`= ?,`numberAvailable`=? WHERE `id` = ?");
        deleteItem = connection.prepareStatement("DELETE FROM `Items` WHERE `id` = ?");
        getAllItems = connection.prepareStatement("SELECT * FROM Items");
        selectItem = connection.prepareStatement("SELECT * FROM `Items` WHERE `id` = ?");
    }

    public Connection getConnection(){
    	return connection;
    	}

    public boolean deleteItem(Item Item) throws SQLException{
    	int result = 0;
    	try{
            deleteItem.setInt(1,Item.getId());
            result = deleteItem.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    	
    	if(result >= 1)
        	return true;
        else
        	return false;
    }

    public void removeAll() throws SQLException{
    	try{
        connection.prepareStatement("DELETE FROM Items").executeUpdate();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    }
    
    public void resetDB() {
    	try {
			removeAll();
			statement.executeUpdate("ALTER TABLE Items AUTO_INCREMENT = 1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public boolean addItem(Item Item) throws SQLException{
    	int rs = 0;
        try{
            addItem.setInt(1,Item.getId());
            addItem.setString(2,Item.getName());
            addItem.setString(3,Item.getDate());
            addItem.setInt(4,Item.getAvailable());
            rs = addItem.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        
        if(rs >=1)
        	return true;
        else
        	return false;
    }

   public boolean updateItem(Item Item) throws SQLException{
	   int result = 0;
       try{
           editItem.setString(1,Item.getName());
           editItem.setString(2,Item.getDate());
           editItem.setInt(3,Item.getAvailable());
           editItem.setInt(4,Item.getId());
           result = editItem.executeUpdate();
       }catch (SQLException e){
           e.printStackTrace();
       }
       
       if(result >= 1)
       	return true;
       else
       	return false;
   }

   
   /** Get all data from database based on Item id */
   public Item getItem(Item Item){
	   ResultSet rs = null;
        try{
            selectItem.setInt(1,Item.getId());
            rs = selectItem.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }

        Item item = new Item();
        try {
        	rs.first();
			item.setId(rs.getInt("id"));
	        item.setName(rs.getString("name"));
	        item.setDate(rs.getString("dateOfUpdate"));
	        item.setAvailable(rs.getInt("numberAvailable"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return item;
   }
   
   public Item getItem(int id){
	   Item item = new Item();
	   item.setId(id);
	   return getItem(item);
	}
   
   
   public List<Item> getAllItems(){
        List<Item> Items = new ArrayList<Item>();
        try{
            ResultSet result = getAllItems.executeQuery();
            while (result.next()){
                Item Item = new Item();
                Item.setId(result.getInt("id"));
                Item.setName(result.getString("name"));
                Item.setDate(result.getString("dateOfUpdate"));
                Item.setAvailable(Integer.parseInt(result.getString("numberAvailable")));

                Items.add(Item);
            }
        }catch (NumberFormatException | SQLException e){
            e.printStackTrace();
        }
        return Items;
    }
}
