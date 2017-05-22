package pj.edu.pjatk.lab10.helper;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;


public class ExportDatabase {
    public static void main(String[] args) throws Exception {
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        FlatXmlDataSet.write(connection.createDataSet(),
                new FileOutputStream("src/test/resources/databaseDump.xml"));
        FlatDtdDataSet.write(connection.createDataSet(),
                new FileOutputStream("src/test/resources/databaseDump.dtd"));
    }

}