
/*
 * This file has been adapted by the CIS 120 course staff from code by
 * Richard Wicentowski and Tia Newhall (2005).
 *
 * You need to complete this file FIRST before moving on to the
 * rest of the project.
 */

/**
 * A point of color.
 * <p>
 * Pixels are represented as three integral color components (red, green, and
 * blue) in the inclusive range [0, 255]. Lower values mean less color; higher
 * mean more. For example, {@code new Pixel(255,255,255)} represents white,
 * {@code new Pixel(0,0,0)} represents black, and {@code new Pixel(0,255,0)}
 * represents green.
 * <p>
 * This data structure is immutable. Once a {@code Pixel} is created, it cannot
 * be modified.
 */
public class Pixel implements Comparable<Pixel> {

    /**
     * The {@code Pixel} representing the RGB color black.
     */
    public static final Pixel BLACK = new Pixel(0,0,0);

    /**
     * The {@code Pixel} representing the RGB color blue.
     */
    public static final Pixel BLUE = new Pixel(0,0,255);

    /**
     * The {@code Pixel} representing the RGB color red.
     */
    public static final Pixel RED = new Pixel(255,0,0);

    /**
     * The {@code Pixel} representing the RGB color green.
     */
    public static final Pixel GREEN = new Pixel(0,255,0);

    /**
     * The {@code Pixel} representing the RGB color white.
     */
    public static final Pixel WHITE = new Pixel(255,255,255);
    
    private int r;
    private int g;
    private int b;
    private int special = 0;
    
    // helper for blur
    public Pixel(int spec) {
    	special = spec;
    	r = 0;
    	g = 0;
    	b = 0;
	}
    public int getSpecial() {
    	return special;
    }
    /**
     * Create a new pixel with the provided color intensities.
     * <p>
     * If the supplied arguments are not between 0 and 255, they are clipped:
     * <ul>
     *   <li>Negative components are set to 0.</li>
     *   <li>Components greater than 255 are set to 255.</li>
     * </ul>
     *
     * @param r the red component of the pixel
     * @param g the green component of the pixel
     * @param b the blue component of the pixel
     */
    Pixel(int r, int g, int b) {
        // TODO: Implement this
    	if (r < 0) {r = 0;}
    	if (g < 0) {g = 0;}
    	if (b < 0) {b = 0;}
    	if (r > 255) {r = 255;}
    	if (g > 255) {g = 255;}
    	if (b > 255) {b = 255;}
    	this.r = r;
    	this.g = g;
    	this.b = b;
    }
    

    /**
     * Create a new pixel with the provided color components, specified as an
     * array. The index {@code c[0]} corresponds to {@code Pixel}'s red
     * component; {@code c[1]} its green component, and {@code c[2]} its blue
     * component.
     * <p>
     * If {@code c} is null, or has fewer than 3 entries, then the missing
     * components are set to 0. If {@code c} has more than 3 entries, the extra
     * entries are ignored.
     * <p>
     * This constructor must not throw any exceptions.
     *
     * @param c the array of components
     */
    Pixel(int[] c) {
        // TODO: Implement this
    	try {
    		this.r = c[0];
    	} catch (ArrayIndexOutOfBoundsException e) {
    		this.r = 0;
    	}
    	try {
    		this.g = c[1];
    	} catch (ArrayIndexOutOfBoundsException e) {
    		this.r = 0;
    	}
    	try {
    		this.b = c[2];
    	} catch (ArrayIndexOutOfBoundsException e) {
    		this.b = 0;
    	}
    }

    /**
     * Accessor for the red component of the pixel.
     *
     * @return the int value of the red component
     */
    public int getRed() {
        return this.r; // TODO: Implement this
    }

    /**
     * Accessor for the green component of the pixel.
     *
     * @return the int value of the green component
     */
    public int getGreen() {
        return this.g; // TODO: Implement this
    }

    /**
     * Accessor for the blue component of the pixel.
     *
     * @return the int value of the blue component
     */
    public int getBlue() {
        return this.b; // TODO: Implement this
    }

    /**
     * Accessor for the pixel's components as an array of 3 integers, where
     * index 0 is red, index 1 is green, and index 2 is blue.
     *
     * Note that this method should not break encapsulation.
     *
     * @return an int array representing the pixel's components
     */
    public int[] getComponents() {
    	int[] result = new int[3];
    	result[0] = getRed();
    	result[1] = getGreen();
    	result[2] = getBlue();
        return result; // TODO: Implement this
    }

    /**
     * Determines the level of similarity between this pixel and another by
     * summing the absolute values of the differences between corresponding
     * components of the two pixels. Distance to a null pixel is defined as -1.
     *
     * Hint: use {@code Math.abs}
     *
     * @param px the other pixel with which to compare
     * @return the sum of the differences in each of the color components
     */
    public int distance(Pixel px) {
    	if (px == null) return -1;
    	int result = Math.abs(px.getRed() - this.getRed()) + 
    	Math.abs(px.getGreen() - this.getGreen()) + 
    	Math.abs(px.getBlue() - this.getBlue());
        return result; // TODO: Implement this
    }

    /**
     * Returns a string representation of this pixel. The string should
     * comma separate the rgb values and surround them with parentheses.
     * <p>
     * For example, {@code RED.toString()} is {@code "(255, 0, 0)"}
     * <p>
     * Note: This function will allow you to print pixels in a readable format.
     * This can be very helpful while debugging, and we highly encourage you to
     * use print statements to aid your debugging throughout this assignment.
     *
     * @return a string representation of this pixel
     */
    public String toString() {
    	int r = this.getRed();
    	int g = this.getGreen();
    	int b = this.getBlue();
        return "(" + r + "," + g + "," + b + ")"; // TODO: Implement this
    }

    /**
     * Compares the RGB values of the current Pixel with another to check if
     * they are the same (and thus whether the two Pixels equal each other)
     *
     * @param px The pixel being compared with this
     * @return whether the two pixels contain the same components
     */
    public boolean equals(Pixel px) {
        return px.getRed() == this.getRed() &&  // TODO: Implement this
        		px.getGreen() == this.getGreen() && 
        		px.getBlue() == this.getBlue(); // TODO: Implement this
    }


    /* ---------------- Don't modify below this line ------------------*/


    /**
     * Checks whether this pixel has the same components as the given Object.
     * If the other object is not a Pixel, then the method returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other instanceof Pixel) {
            Pixel px = (Pixel) other;
            return this.equals(px);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 0;
        int[] components = getComponents();

        for (int k = 0; k < components.length; k++) {
            h += k * 255 + components[k];
        }
        return h;
    }

    @Override
    public int compareTo(Pixel o) {
        int rc = getRed() - o.getRed();
        int gc = getGreen() - o.getGreen();
        int bc = getBlue() - o.getBlue();

        if (rc != 0) {
            return rc;
        } else if (gc != 0) {
            return gc;
        } else {
            return bc;
        }
    }
}
