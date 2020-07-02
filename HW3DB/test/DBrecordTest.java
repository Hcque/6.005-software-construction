import static org.junit.Assert.*;

import org.junit.Test;

public class DBrecordTest {

	@Test
	public void testDBrecord() {
		DBrecord dbr = new DBrecord("name:mia, stars:hellen, year: 1986");
		assertFalse(dbr.getState());
		System.out.println(dbr);
	}

	@Test
	public void testOverlap() {
		DBrecord dbr1 = new DBrecord("name:mia, stars:hellen, year: 1986");
		DBrecord dbr2 = new DBrecord("name:mia, stars:G");
		assertTrue(dbr1.overlap(dbr2));
		assertTrue(dbr2.overlap(dbr2));
		

	}

	@Test
	public void testSelect() {
		DBrecord dbr = new DBrecord("name:mia, stars:hellen, year: 1986");
		dbr.select();
		assertTrue(dbr.getState());
	}

}
