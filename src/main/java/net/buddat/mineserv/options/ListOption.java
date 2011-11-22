package net.buddat.mineserv.options;

import java.util.ArrayList;
import java.util.List;

public class ListOption extends Option {
	
	private List<Object> value = new ArrayList<Object>();
	
	public ListOption(String key, List<Object> value) {
		this(key, value, null);
	}
	
	public ListOption(String key, List<Object> value, String comment) {
		setKey(key);
		setValue(value);
		setComment(comment);
	}

	@Deprecated
	@Override
	public void setValue(Object newValue) {
		
	}
	
	public void addValue(Object newValue) {
		value.add(newValue);
	}
	
	public void setValue(int id, Object newValue) {
		value.set(id, newValue);
	}

	@Deprecated
	@Override
	public Object getValue() {
		return null;
	}
	
	public Object getValue(int id) {
		return value.get(id);
	}
	
	public List<Object> getAllValues() {
		return value;
	}

	@Override
	public String toString() {
		String values = "";
		for (Object o : getAllValues()) {
			values += (String) o;
			values += Options.LIST_DELIMITER;
		}
		
		values = values.substring(0, values.length() - 1);
		
		return (getComment() == null ? "" : Options.COMMENT_CHAR + getComment() + "\r\n") + getKey() + Options.DELIMITER + values;
	}

}
