
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class ChunkListTest {

	@Test
	public void testSize() {
		ChunkList<String> ch = new ChunkList<String>();
		assertEquals(0, ch.size());
	}


	@Test
	public void testAddOne() {
		ChunkList<String> ch = new ChunkList<String>();
		ch.add("str1");
		assertEquals(1, ch.size());
		Iterator<String> itr = ch.iterator();

		//System.out.println(itr.next());
		assertTrue(itr.next().equals("str1"));
	}

	@Test
	public void testAddE() {
		ChunkList<String> ch = new ChunkList<String>();
		ch.add("str1");
		assertEquals(1, ch.size());
		Iterator<String> itr = ch.iterator();
		ch.add("str2");
		ch.add("str3");
		ch.add("");
		String[] expected = {"str1", "str2", "str3", ""};
		int i = 0;
		while (itr.hasNext()) {
			//System.out.println(itr.next());
			//System.out.println(expected[i]);
			assertTrue(itr.next().equals(expected[i]));
			i++;
		}
	}

	@Test
	public void testIterator() {
		ChunkList<String> ch = new ChunkList<String>();
		Iterator<String> itr = ch.iterator();
		assertFalse(itr.hasNext());
		ch.add("str1");
		assertTrue(itr.hasNext());
		
		
	}


	@Test
	public void testRemoveOne() {

		ChunkList<String> ch = new ChunkList<String>();
		Iterator<String> itr = ch.iterator();
		ch.add("str1");
		ch.add("str2");
		itr.remove();
		assertEquals(1, ch.size());
		assertTrue(itr.next().equals("str1"));
		itr.remove();
		assertTrue(itr.next() == null);
		
	}

}
