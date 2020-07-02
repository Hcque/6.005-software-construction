import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class DBTableTest {

	@Test
	public void testDBTable() throws IOException {
		DBTable dbt = new DBTable();
		dbt.addData("movies.txt");
		assertEquals(0, dbt.countSelected());
	}

	@Test
	public void testChangeSelected() throws IOException {
		DBTable dbt = new DBTable();
		dbt.addData("movies.txt");	
		
	}

}
