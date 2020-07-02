import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FileLineIterator provides a useful wrapper around Java's provided
 * BufferedReader and provides practice with implementing an Iterator.
 * Your solution should not read the entire file into memory at once, instead
 * reading a line whenever the next() method is called. See
 * Java's documentation for BufferedReader to learn how to construct one given
 * a path to a file. Then, think about how you can use BufferedReader's
 * methods within this class to implement our desired functionality.
 * 
 * Note: Any IOExceptions thrown by readers should be caught and handled properly.
 */
public class FileLineIterator implements Iterator<String> {
	private int line;
    private BufferedReader in;
    private boolean hasnext;
    private String buf;
    /**
     * Creates a FileLineIterator for the file located at filePath.
     * Fill out the constructor so that a user can instantiate a FileLineIterator.
     * Feel free to create and instantiate any variables that your implementation requires here.
     * See recitation and lecture notes for guidance.
     *
     * If an IOException is thrown by the BufferedReader or FileReader, then set next to null.
     *
     * @param filePath - the path to the CSV file to be turned to an Iterator
     * @throws IllegalArgumentException if filePath is null or if the file doesn't exist
     */
    public FileLineIterator(String filePath) {
        // todo: Complete this constructor.
    	if (filePath == null) {
    		throw new IllegalArgumentException();
    	}
    	line = 0;
    	try {
			in = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

     /**
      * Returns true if there are lines left to read in the file, and
     * false otherwise.
     * 
     * If there are no more lines left, this method should close the
     * BuffereReader.
      *
      * @return a boolean indicating whether the FileLineIterator
      * can produce another line from the file
      */
    @Override
    public boolean hasNext() {
    	try {
			buf = in.readLine();
    		//System.out.println(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}
    	if (buf == null) {
    		hasnext = false; 
    		return false;
    	}
    	hasnext = true;
    	return true;	
    	 
    }

    /**
      * Returns the next line from the file, or throws a NoSuchElementException
      * if there are no more strings left to return (i.e. hasNext() is false).
      * 
      * This method also advances the iterator in preparation for another
      * invocation.  If an IOException is thrown during this process, the
      * subsequent call throw a NoSuchElementException
      *
      * @return the next line in the file
      * @throws NoSuchElementException if there is no more data in the file
      */
    @Override
    public String next() {
    	if (! hasnext) {
    		try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		throw new NoSuchElementException();
    	}
    	line++;
    	return buf;

    }
}
