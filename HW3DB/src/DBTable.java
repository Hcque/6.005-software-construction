import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class DBTable {
	private ArrayList<DBrecord> table = new ArrayList<>();
	private int numOfSelected = 0;
	private int numOfRecords = 0;

	public DBTable() {

	}

	public int getNumOfRecords() { return table.size();	}

	public void addData(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = in.readLine()) != null) {
			table.add(new DBrecord(line));
		}
	}
	public int countSelected() {
		int count = 0;
		for (DBrecord dbr: table) {
			if (dbr.getState()) count++;
		}
		return count;
	}
	
	public void selectAnd(String criterion) {
		DBrecord crtRecord = new DBrecord(criterion);
		for (DBrecord dbr : table) {
			if (crtRecord.smallerThan(dbr)) dbr.select();
		}
	}
	public void selectOr(String criterion) {
		DBrecord crtRecord = new DBrecord(criterion);
		for (DBrecord dbr : table) {
			if (crtRecord.overlap(dbr)) dbr.select();
		}
	}
	public void deleteAll() {
		table.clear();
	}
	public void deleteSelected() {
		for (int i = table.size() - 1; i > -1; i--) {
			if (table.get(i).getState()) table.remove(i);
		}
	}
	public void deleteUnselected() {
		for (int i = table.size() - 1; i > -1; i--) {
			if (!(table.get(i).getState())) table.remove(i);
		}
	}
	public void clearSelection(){
		for (DBrecord dbr : table) {
			dbr.unSelect();
		}
	}
	
	
	@Override public String toString() {
		String result = "";
		for (DBrecord dbr: table) {
			result += dbr + "\n";
		}
		return result;
	}
	
}
