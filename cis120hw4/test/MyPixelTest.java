import static org.junit.Assert.*;
import org.junit.Test;


/** 
 *  Use this file to test your implementation of Pixel.
 * 
 *  We will manually grade this file to give you feedback
 *  about the completeness of your test cases.
 */

public class MyPixelTest {


    /*
     * Remember, UNIT tests should ideally have one point of failure. Below we
     * give you an example of a unit test for the Pixel constructor that takes
     * in three ints as arguments. We use the getRed(), getGreen(), and
     * getBlue() methods to check that the values were set correctly. This one
     * test does not comprehensively test all of Pixel so you must add your own.
     *
     * You might want to look into assertEquals, assertTrue, assertFalse, and
     * assertArrayEquals at the following:
     * http://junit.sourceforge.net/javadoc/org/junit/Assert.html
     *
     * Note, if you want to add global variables so that you can reuse Pixels
     * in multiple tests, feel free to do so.
     */

    @Test
    public void testConstructInBounds() {
        Pixel p = new Pixel(40, 50, 60);
        assertEquals(40, p.getRed());
        assertEquals(50, p.getGreen());
        assertEquals(60, p.getBlue());
    }
    @Test
    public void testConstructFromArray() {
    	Pixel p = new Pixel(new int[] {40,50,60});
    	assertEquals(40, p.getRed());
    	assertEquals(50, p.getGreen());
    	assertEquals(60, p.getBlue());
    }
    @Test
    public void testConstructFromArrayMissed() {
    	Pixel p = new Pixel(new int[] {40,50});
    	assertEquals(40, p.getRed());
    	assertEquals(50, p.getGreen());
    	assertEquals(0, p.getBlue());
    }
    @Test
    public void testEquals() {
    	Pixel p = new Pixel(new int[] {40,50,60});
    	Pixel p2 = new Pixel(new int[] {40,50,60});
    	assertTrue(p.equals(p2));
    }
    @Test
    public void testDistance() {
    	Pixel p = new Pixel(new int[] {10,20,60});
    	Pixel p2 = new Pixel(new int[] {0,50,60});
    	assertEquals(40, p.distance(p2));
    }

    /* ADD YOUR OWN TESTS BELOW */

}
