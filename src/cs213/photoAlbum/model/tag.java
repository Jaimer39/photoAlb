package cs213.photoAlbum.model;

/**
 * @author Jaime Reynoso And Alexander Guzman
 *
 */
public class tag {

	String type;
	private String value;
	
	/**Constructor
	 * @param type
	 * @param value
	 */
	public tag(String type, String value)
	{
		this.type = type;
		this.value = value;
	}
	/**This method returns true if two tags are the same type
	 * @param type
	 * @return boolean
	 */
	public boolean sameType(String type)
	{
		return (type == this.type);
	}
	/**This Changes the value of a particular tag
	 * @param newValue
	 */
	public void changeValue(String newValue)
	{
		this.value = newValue;
	}
	/**This method returns the value of a particular tag
	 * @return value
	 */
	public String getValue()
	{
		return this.value;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o)
	{
		if( o == null) return false;
		if(!(o instanceof tag)) return false;
		tag tmp = (tag) o;
		return (tmp.type.equalsIgnoreCase(this.type) && tmp.getValue().equalsIgnoreCase(this.value));
	}
	public String toString()
	{
		return "("+type+", "+value+")";
	}

}
