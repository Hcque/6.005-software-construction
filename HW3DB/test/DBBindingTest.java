import static org.junit.Assert.*;

import org.junit.Test;

public class DBBindingTest {

	@Test
	public void testDBBinding() {
		DBBinding dbb = new DBBinding("name : mia");
		System.out.println(dbb);
		assertEquals("name", dbb.getKey());
		assertEquals("mia", dbb.getValue());
		
	}
	public void testSetKeyValue() {
		DBBinding dbb = new DBBinding("name : mia");
		assertEquals("name", dbb.getKey());
		assertEquals("mia", dbb.getValue());
	}
	public void testEquals() {
		DBBinding dbb1 = new DBBinding("name : mia");
		DBBinding dbb2 = new DBBinding("name : mia");
		DBBinding dbb3 = new DBBinding("good : mia");
		Object dbb4 = new DBBinding("name : mia");
		assertTrue(dbb1.equals(dbb4));
		assertTrue(dbb1.equals(dbb2));
		assertFalse(dbb1.equals(dbb3));
	}

}
