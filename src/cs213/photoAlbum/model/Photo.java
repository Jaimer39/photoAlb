package cs213.photoAlbum.model;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Jaime Reynoso & Alexander Guzman
 * 
 */
public class Photo {

	String fileName, Caption;
	Calendar cal = Calendar.getInstance();
	Date time;
	String amountOfPeople[];
	String Location;
	int numFriends = 0;

	/**
	 * Every photo needs a name and a caption
	 * 
	 * @param fileName
	 * @param Caption
	 */
	public Photo(String fileName, String Caption) {
		this.fileName = fileName;
		this.Caption = Caption;
		time = cal.getTime();
		amountOfPeople = new String[5];
	}

	/**
	 * Tags a friend in a photo
	 * 
	 * @param name
	 */
	public void addPerson(String name) {
		if (numFriends < 0) {
			amountOfPeople[numFriends] = name;
		} else
			System.out.println("Sorry, but you don't have any more friends");
	}

	/**
	 * lets you delete a tagged person
	 * 
	 * @param name
	 * @return
	 */
	public boolean deletePerson(String name) {
		boolean response = false;
		for (int i = 0; i < numFriends; i++) {
			if (name.equalsIgnoreCase(amountOfPeople[i])) {
				response = true;
				numFriends--;
			}
		}
		return response;
	}

	/**
	 * Change the location of where you were
	 * 
	 * @param location
	 */
	public void changeLocation(String location) {
		this.Location = location;
	}

	/**
	 * this lets you change the name of the photo
	 * 
	 * @param name
	 */
	public void changeName(String name) {
		this.fileName = name;
	}

	/**
	 * This lets you change the caption
	 * 
	 * @param caption
	 */
	public void changeCaption(String caption) {
		this.Caption = caption;
	}
}
