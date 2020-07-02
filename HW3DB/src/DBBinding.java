
public class DBBinding {
	private String key;
	private String value;
	
	public DBBinding(String s) {
		String[] sArray = s.split(":", -1);
		key = sArray[0].trim();
		value = sArray[1].trim();
	}
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public void setKey(String k) {
		key = k;
	}
	public void setValue(String v) {
		value = v;
	}
	
	@Override
	public boolean equals(Object that) {
		if (!(that instanceof DBBinding)) return false;
		DBBinding thatDBb = (DBBinding) that;
		return thatDBb.getKey().equals(this.getKey()) && 
				thatDBb.getValue().toLowerCase().equals(this.getValue().toLowerCase());
	}
	
	
	@Override
	public String toString() {
		return key + ":" + value;
	}
}
