import java.util.ArrayList;

public class DBrecord {
	private List<DBBinding> records = new ArrayList<>();
	private boolean selectState = false;
	
	public DBrecord(String line) {
		// TODO Auto-generated constructor stub
		String[] sArray = line.split(",", -1);
		for (int i = 0; i < sArray.length; i++) {
			records.add(new DBBinding(sArray[i]));
		}
	}

	public void addBinding(DBBinding b) {
		records.add(b);
	}
	
	public boolean findBinding(DBBinding targetDbb) {
		for (DBBinding dbb : records) {
			if (targetDbb.equals(dbb)) return true;
		}
		return false;
	}

	public boolean getState() {
		return selectState;
	}
	public void select() {
		selectState = true;
	}
	public void unSelect() {
		selectState = false;
	}
 	
	@Override
	public String toString() {
		String result = selectState ? "*" : "";
		for (DBBinding dbb: records) {
			result += dbb + ",";
		}
		return result.substring(0, result.length()-1);
	}
	
	public boolean smallerThan(DBrecord compareDBr) {
		for (DBBinding dbb : records) {
			if (!(compareDBr.findBinding(dbb))) return false;
		}
		return true;
	}

	public boolean overlap(DBrecord compareDBr) {
		for (DBBinding dbb : records) {
			if (compareDBr.findBinding(dbb)) return true;
		}
		return false;
	}
}
