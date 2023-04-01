package TableCreation;

import java.io.IOException;
import java.sql.SQLException;

public class MainClass {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Generate generate= new Generate();
		generate.csvToTable();
		generate.insertDataCsvToTable();
	}

}
