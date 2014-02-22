package cs213.photoAlbum.model;

/**
 * 
 * @author Jaime Reynoso & Alexander Guzman
 * 
 * 
 */
public class User {

	String user_ID, fullName;
	Album list_myAlbum[];
	String alphabeticalOrder = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * A User needs an ID and they need to have their full name on file
	 * 
	 * @param userID
	 * @param full_Name
	 */
	public User(String userID, String full_Name) {
		this.user_ID = userID;
		this.fullName = full_Name;
		list_myAlbum = new Album[26];
	}

	/**
	 * Add album
	 * 
	 * @param name
	 *            - this is the name of the Album
	 * 
	 */
	public void addAlbum(String name) {
		/*
		 * Album boss = new Album(name); int index =
		 * name.indexOf(name.charAt(0)); //some soft of intelligent delete
		 * method with the linked list
		 */
	}

	/**
	 * This deletes an album based on their name
	 * 
	 * @param name
	 */
	public void deleteAlbum(String name) {
		// this is going to use some sort of algorithm to delete a link in
		// a linked list
	}

}
