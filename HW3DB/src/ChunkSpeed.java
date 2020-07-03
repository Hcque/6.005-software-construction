// ChunkSpeed.java
/*
 This is a little test class that runs some timing tests on
 ArrayList, LinkedList, and ChunkList.
 This is not really OOP -- it's just static functions.
*/
import java.util.*;

public class ChunkSpeed {
	
	// number of elements
	public static final int MAX = 200000;
	
	public static String FOO = "Foo";
	
	/**
	 Times a fixed series of add/next/remove calls
	 on the given collection. Returns the number
	 of milliseconds the operations took.
	 */
	public static long test(Collection<String> coll) {
		long start = System.currentTimeMillis();
	
		// Build the thing up
		for (int i=0; i<MAX; i++) {
			coll.add(FOO);
		}
		
		// Iterate halfway through
		Iterator<String>it = coll.iterator();
		for (int i=0; i<MAX/2; i++) {
			it.next();
		}
		
		// Delete the next tenth
		for (int i=0; i<MAX/10; i++) {
			it.next();
			it.remove();
		}
		
		// Iterate over the whole thing (read-only) 5 times
		int count = 0;
		for (int i = 0; i<5; i++) {
			for (String s: coll) {
				if (s != null) count++;
			}
		}
		
		long delta = System.currentTimeMillis() - start;
		return(delta);
		
	}
	
	/**
	  Runs the time test on the given collection and prints the result,
	  including the class name (cute use of getClass()).
	*/
	public static void printTest(Collection<String> c) {
		long time = test(c);
		System.out.println(c.getClass().toString() + " " + time);
	}
	
	
	public static void main(String[] args) {
		for (int i=0; i<5; i++) {
			printTest(new ArrayList<String>());
			printTest(new LinkedList<String>());
			printTest(new ChunkList<String>());
		}
	}

}
