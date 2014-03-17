package cs213.photoAlbum.model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * @author Jaime Reynoso & Alexander Guzman
 *
 */
public class Photo implements Serializable{

	String fileName, Caption;
	Calendar cal = Calendar.getInstance();
	Date time;
	ArrayList<tag> tags; 
	int numFriends =0;
	File f;
	private static final long serialVersionUID = 7526472295622776147L;

	/**
	 * Every photo needs a name and a caption
	 * @param fileName
	 * @param Caption
	 */
	public Photo(String fileName, String Caption){
		f = new File("../../"+fileName);
		this.fileName = fileName;
		this.Caption = Caption;
		time = cal.getTime();
		tags = new ArrayList<tag>();
		try{
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.fileName+"\n"+this.Caption);
		bw.close();
		}
		catch(Exception e){
			
		}
	}
	
	
	/**
	 * Get filename
	 * @return filename
	 */
	public String getFileName() {
		return fileName;
	}


	/**This changes the filename on a photo
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	/**This returns the caption from photo
	 * @return Caption
	 */
	public String getCaption() {
		return Caption;
	}


	/**This changes the Caption on a photo
	 * @param caption
	 */
	public void setCaption(String caption) {
		Caption = caption;
	}


	/**This returns the date on which the pic was saved
	 * @return Calendar instance
	 */
	public Calendar getCal() {
		return cal;
	}


	/**This returns all the tags(Albums, friends, location, etc.)
	 * @return tags
	 */
	public ArrayList<tag> getTags() {
		return tags;
	}


	/**Returns the num of people in a photo
	 * @return numFriends
	 */
	public int getNumFriends() {
		return numFriends;
	}


	/**Returns the file that was saved for a photo
	 * @return File
	 */
	public File getF() {
		return f;
	}


	/**Can change the file that the photo uses to get saved
	 * @param f
	 */
	public void setF(File f) {
		this.f = f;
	}


	/**Returns the time at which the photo was saved
	 * @return time
	 */
	public Date getTime() {
		return time;
	}


	/**Add Album, lets you add an album to a photo as a tag
	 * @param name
	 */
	public void addAlbum(String name) {
		tag newAlbum = new tag("Album", name);
		tags.add(newAlbum);
	}
	
	/**Delete Album, lets you delete an album from a photo
	 * @param name
	 */
	public void deleteAlbum(String name){
		tag oldAlbum = new tag("Album", name);
		tags.remove(oldAlbum);
	}
	
	/**Lets you add a friend to the tags
	 * @param name
	 */
	public void addPerson(String name){
		tag newTemp = new tag("Person", name);
		numFriends++;
		tags.add(newTemp);
	}
	
	
	/** lets you delete a tagged person
	 * @param name
	 */
	public void deletePerson(String name){
		tag delTag = new tag("Person", name);
		tags.remove(delTag);
	}
	
	/** Lets you delete a tag completely
	 * @param tagName
	 */
	public void deleteTag(String tagName){
		for(tag a: tags){
			if(a.getValue() == tagName){
				tags.remove(a);
			}
		}
	}
	/**Lets you add a location but only if there isn't one already
	 * @param location
	 * @return true or False
	 */
	public boolean addLocation(String locationValue)
	{
		tag loc = new tag("Location", locationValue);
		for(int i = 0; i < tags.size(); i++)
		{
			if(tags.get(i).sameType("Location"))
			{
				return false;
			}
		}
		tags.add(loc);
		return true;
	}
	
	/** This lets you change the location to a new one, but only if you have one
	 * @param Nlocation 
	 */
	public void changeLocation(String Nlocation){
		for(int i = 0; i < tags.size(); i++)
		{
			if(tags.get(i).sameType("Location"))
			{
				tags.get(i).changeValue(Nlocation);
				break;
			}
		}
	}
	
	/* This just tells me whether two pics are the same
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o)
	{
		if(o == null) return false;
		if(!(o instanceof Photo)) return false;
		Photo oldPic = (Photo) o;
		return (oldPic.fileName.equals(fileName));
	}
	/**This method is used to print out a list of all the tags
	 * Type: Void
	 */
	public void listTags()
	{
		for(int i = 0; i < tags.size(); i++)
		{
			System.out.println(tags.get(i).toString());
		}
	}
	public void addTag(String type, String value){
		tags.add(new tag(type, value));
	}

	/**Returns ArrayList of Albums the Photo is in
	 * @return Albums
	 */
	public ArrayList<tag> getAlbums(){
		ArrayList<tag> albums = new ArrayList<tag>();
		for(tag a: tags){
			if(a.sameType("Album")){
				albums.add(a);
			}
		}
		return albums;
		
	}
	public void removePic() {
		f.delete();
	}

	
}
