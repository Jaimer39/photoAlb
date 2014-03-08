package cs213.photoAlbum.model;

public class tag {

	String type;
	private String value;
	
	public tag(String type, String value)
	{
		this.type = type;
		this.value = value;
	}
	public boolean sameType(String type)
	{
		return (type == this.type);
	}
	public void changeValue(String newValue)
	{
		this.value = newValue;
	}
	public String getValue()
	{
		return this.value;
	}
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
