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
	
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getCaption() {
		return Caption;
	}


	public void setCaption(String caption) {
		Caption = caption;
	}


	public Calendar getCal() {
		return cal;
	}


	public void setCal(Calendar cal) {
		this.cal = cal;
	}


	public ArrayList<tag> getTags() {
		return tags;
	}


	public void setTags(ArrayList<tag> tags) {
		this.tags = tags;
	}


	public int getNumFriends() {
		return numFriends;
	}


	public void setNumFriends(int numFriends) {
		this.numFriends = numFriends;
	}


	public File getF() {
		return f;
	}


	public void setF(File f) {
		this.f = f;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	/**Tags a friend in a photo
	 * @param name
	 */
	public void addAlbum(String name) {
		tag newAlbum = new tag("Album", name);
		tags.add(newAlbum);
	}
	public void deleteAlbum(String name){
		tag oldAlbum = new tag("Album", name);
		tags.remove(oldAlbum);
	}
	public void addPerson(String name){
		tag newTemp = new tag("Person", name);
		tags.add(newTemp);
	}
	
	
	/** lets you delete a tagged person
	 * @param name
	 * @return
	 */
	public boolean deletePerson(String name){
		boolean response = false;
		tag delTag = new tag("Person", name);
		tags.remove(delTag);
		return response;
	}
	public void deleteTag(String tagName){
		for(tag a: tags){
			if(a.getValue() == tagName){
				tags.remove(a);
			}
		}
	}
	/**Change the location of where you were
	 * @param location
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
	/** this lets you change the name of the photo
	 * @param name
	 */
	
	public boolean equals(Object o)
	{
		if(o == null) return false;
		if(!(o instanceof Photo)) return false;
		Photo oldPic = (Photo) o;
		return (oldPic.fileName.equals(fileName));
	}
	public void listTags()
	{
		for(int i = 0; i < tags.size(); i++)
		{
			System.out.println(tags.get(i).toString());
		}
	}
	public void changeName(String name){
		this.fileName = name;
	}
	/** This lets you change the caption
	 * @param caption
	 */
	public void changeCaption(String caption){
		this.Caption = caption;
	}
	public void addTag(String type, String value){
		tags.add(new tag(type, value));
	}

	public void removePics() {
		f.delete();
	}


	public ArrayList<Album> getAlbums() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
