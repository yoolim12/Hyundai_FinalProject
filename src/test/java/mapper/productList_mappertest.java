package mapper;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.hyundai.project.productDAO.ProductListDAO;


@SpringBootTest
@ContextConfiguration(classes = ProductListDAO.class)
public class productList_mappertest {
	
	@Autowired
	private ProductListDAO mapper;
	
	@Test
	public void testGetList() throws SQLException {
		mapper.getListAll();
	}//end void
	
}//end class

