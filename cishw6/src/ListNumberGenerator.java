import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/**
 * Produces deterministic numbers by iterating repeatedly over a
 * List<Integer> or Integer[] argued in the constructors.
 */
public class ListNumberGenerator implements NumberGenerator {
    
    final List<Integer> list;
    int index = 0;
    int smallestUpperBound = Integer.MAX_VALUE;
    
    public ListNumberGenerator(List<Integer> list) {
        if (list == null || list.isEmpty()) { 
            throw new IllegalArgumentException("list must be non-null and non-empty");
        }
        this.list = new LinkedList<Integer>();
        for (int i : list) {
            if (i < smallestUpperBound) { 
                smallestUpperBound = i;
            }
        }
        this.list.addAll(list);
    }
    
    public ListNumberGenerator(Integer[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array must be non-null");
        }
        this.list = new LinkedList<Integer>();
        for (int i : arr) {
            if (i < smallestUpperBound) { 
                smallestUpperBound = i;
            }
        }
        this.list.addAll(Arrays.asList(arr)); 
    }
    
    public int next() {
        int next = list.get(index++);
        if (index == list.size()) {
            index = 0;
        }
        return next;
    }
    
    public int next(int bound) {
        if (bound <= smallestUpperBound) {
            throw new NoSuchElementException("The list contains no elements "
                            + "less than or equal to the argued bound");
        }

        int next;
        while ((next = next()) >= bound) { 
            continue; 
        }
        return next;
    }
    
}
