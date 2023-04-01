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
	
	public void csvToTable() throws ClassNotFoundException, SQLException, IOException {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = conn.createStatement(); 
       
       // Read the CSV file using a BufferedReader
       BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
       
       // Get the first line of the CSV file (i.e. the column headers)
       String headerLine = br.readLine();
       
       // Split the header line into an array of column names
       String[] columns = headerLine.split(",");
       
       // Generate the SQL statement to create the table
       StringBuilder sb = new StringBuilder();
       sb.append("CREATE TABLE " + tableName + " (");
       for (String column : columns) {
           sb.append(column + " VARCHAR(255), ");
       }
       sb.setLength(sb.length() - 2);
       sb.append(")");
       String createTableSql = sb.toString();
       
       // Execute the SQL statement to create the table
       boolean r=stmt.execute(createTableSql);
       
       // Close the BufferedReader
       br.close();
       if(r)
       System.out.println("Table " + tableName + " created successfully.");
       else
    	   System.out.println("Table " + tableName + " created successfully.");
    	
    }
	public void insertDataCsvToTable() throws SQLException, IOException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
	    BufferedReader br = new BufferedReader(new FileReader(csvFilePath));

	   String line;
	   String[] columns;
	   String[] columnsName;
	   String[] values;
	   line=br.readLine();
	   columnsName = line.split(",");
	   // Read the CSV file line by line
	   while ((line = br.readLine()) != null) {
           columns = line.split(",");
           values = new String[columnsName.length];
           for (int i = 0; i < columnsName.length; i++) {
               values[i] = columns[i].trim();
           }

           // Build the SQL INSERT statement
           StringBuilder sql = new StringBuilder();
           sql.append("INSERT INTO " + tableName + " (");
           for (int i = 0; i < columnsName.length; i++) {
               sql.append(columnsName[i]);
               if (i < columnsName.length - 1) {
                   sql.append(", ");
               }
           }
           sql.append(") VALUES (");
           for (int i = 0; i < values.length; i++) {
               sql.append("?");
               if (i < values.length - 1) {
                   sql.append(", ");
               }
           }
           sql.append(")");

           // Prepare the statement and set the values
           PreparedStatement ps = conn.prepareStatement(sql.toString());
           for (int i = 0; i < values.length; i++) {
               ps.setString(i + 1, values[i]);
           }

           // Execute the statement
           ps.executeUpdate();
       }

       System.out.println("Data inserted successfully!");

	}

}
