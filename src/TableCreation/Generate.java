package TableCreation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Generate {
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/Visualization";
    String username = "root";
    String password = "bade";
    String csvFilePath = "D:\\download\\Data.csv";
    String tableName = "VisualDataTable";
	

}
