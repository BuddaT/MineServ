package net.buddat.mineserv.options;

/**
 * Abstract class for option wrappers to extend.
 * 
 * @author Budda
 */
public abstract class Option {
	
	/** The key the option is stored under. */
	private String key;
	
	/** The comment that is stored in the config file. */
	private String comment;

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key key to set to
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param newValue the new value
	 */
	public abstract void setValue(Object newValue);
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public abstract Object getValue();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
}
