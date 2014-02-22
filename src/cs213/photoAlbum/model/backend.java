package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * @author Jaime Reynoso and Alexander Guzman
 * 
 */
public class backend {

	User thedude;
	ArrayList<String> temp;

	/**
	 * Contructor used to use the methods
	 */
	public backend() {
	}

	/**
	 * This returns the user that you're searching for
	 * 
	 * @param name
	 * @return
	 */
	public User readAUser(String name) {
		// this is going to use an algorithm to look for
		// someone with their names
		return thedude;
	}

	/**
	 * This creates a user
	 * 
	 * @param info
	 */
	public void writeAUser(User info) {
		// This is going to write a user into the data
	}

	/**
	 * Delete a user by name
	 * 
	 * @param name
	 */
	public void deleteUser(String name) {
		// this is going to use the name to search the database and
		// then delete
	}

	/**
	 * This returns an array list of all the users
	 * 
	 * @return users
	 */
	public ArrayList<String> getUsers() {
		return temp;
	}

}
