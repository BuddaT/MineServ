package net.buddat.mineserv.options;

/**
 * Wrapper class for String options.
 * 
 * @author Budda
 */
public class StringOption extends Option {

	/** The String value of the option. */
	private String value;
	
	/**
	 * Instantiates a new string option.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public StringOption(String key, String value) {
		this(key, value, null);
	}
	
	/**
	 * Instantiates a new string option.
	 *
	 * @param key the key
	 * @param value the value
	 * @param comment the comment to be stored in the config file
	 */
	public StringOption(String key, String value, String comment) {
		setKey(key);
		setValue(value);
		setComment(comment);
	}
	
	/* (non-Javadoc)
	 * @see net.buddat.mineserv.options.Option#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object newValue) {
		this.value = (String) newValue;
	}
	
	/* (non-Javadoc)
	 * @see net.buddat.mineserv.options.Option#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see net.buddat.mineserv.options.Option#toString()
	 */
	@Override
	public String toString() {
		return (getComment() == null ? "" : Options.COMMENT_CHAR + getComment() + "\r\n") + getKey() + Options.DELIMITER + getValue();
	}

}
