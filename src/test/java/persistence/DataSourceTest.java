package persistence;

import lombok.extern.log4j.Log4j;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DataSourceTest {	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//end static
	
	@Test
	public void TestConnection() {
		try {
			Connection con = 
					DriverManager.getConnection(
							"jdbc:oracle:thin:@123.142.252.25:40024:1521/xepdb1"
							,"product","Hyundai1234");
			System.out.println(con);
		} catch (SQLException e) {
			System.out.println(e.getMessage());			
		}//end try			
	}//end test
}//end class

