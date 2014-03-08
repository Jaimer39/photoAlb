package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * @author Jaime Reynoso and Alexander Guzman
 *
 */
public interface backend {

	
	/** This returns the user that you're searching for
	 * @param name
	 * @return
	 */
	public User readAUser(String name);
	/** This creates a user
	 * @param info
	 * @throws Exception 
	 */
	public void writeAUser(User info) throws Exception;
	/**Delete a user by name
	 * @param name
	 */
	public void deleteUser(String name) throws Exception;
	/**
	 * This returns an array list of all the users
	 * @return users
	 */
	public ArrayList<User> getUsers();
	
}
