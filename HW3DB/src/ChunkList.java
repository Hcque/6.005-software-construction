import java.util.AbstractCollection;
import java.util.Iterator;

public class ChunkList<E> extends AbstractCollection<E> {
	private Chunk head;
	private Chunk tail;
	private int length;
	//private int numOfChunks;
	
	public boolean add(E e) {
		if (tail == null) {
			Chunk ch = new Chunk();
			ch.T[0] = e;
			ch.logicLen++;
			//System.out.println("head: " + head);
			//System.out.println("tail: " + tail);
			if (head == null) {
				head = tail = ch; // not sure 
			}
			else tail = ch; 
		} else if (tail.ARRAY_SIZE - tail.logicLen >= 1){ // not full
			tail.T[tail.logicLen++] = e;
		} else { // tail is full
			Chunk last = tail;
			tail = new Chunk();
			tail.T[0] = e;
			tail.logicLen++;
			last.next = tail; // pointer redirect
		}
		length++;
		return true;
	}
	
	public int size() {
		return length;
	}
	
	public Iterator<E> iterator() {
		return new ChunkIterator();
	}
	
	// inner class of chunk
	private class Chunk {
		private E[] T;
		private static final int ARRAY_SIZE = 8;
		private int logicLen;
		private Chunk next;
		
		public Chunk() {
			T = (E[]) new Object[ARRAY_SIZE];
			logicLen = 0;
			next = null;
		}
	}
	
	// inner class of iterator
	private class ChunkIterator implements Iterator<E> {
		private int index = 0;

		public boolean hasNext() {
			return index < length;
		}

		public E next() {
			int chunkNum = (index / Chunk.ARRAY_SIZE) + 1; // from 1,2...to n
			// find that chunk
			Chunk thisChunk = head;
			while (chunkNum > 1) {
				thisChunk = thisChunk.next;
				chunkNum--;
			}
			return thisChunk.T[index++ % Chunk.ARRAY_SIZE];
		}
		public void remove() {
			
		}
	}
	
	

}
