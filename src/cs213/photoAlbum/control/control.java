package cs213.photoAlbum.control;

import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

/**
 * @author Jaime Reynoso and Alexander Guzman
 * 
 */
public class control {

	User Person;
	String[] people;

	// login for a user needs to be there
	/**
	 * Creates a contructor object needing a user
	 * 
	 * @param person
	 */
	public control(User person) {
		this.Person = person;
	}

	// create an album for this user
	/**
	 * returns true if createAlbum was possible
	 * 
	 * @param name
	 * @return successful
	 */
	public boolean createAlbum(String name) {
		// this creates an album with the specific name
		return true;
	}

	// delete an album
	/**
	 * Returns true is delete album was viable
	 * 
	 * @param name
	 * @return successful
	 */
	public boolean deleteAlbum(String name) {
		return true;
		// this deletes an album for the user
	}

	// list albums
	/**
	 * Returns the name of all the albums
	 * 
	 * @return albumName array
	 */
	public String[] getAlbums() {
		return people;
	}

	// list photos in an album (retrieve all photos)
	/**
	 * Returns a string of all the photo names in the album you last used
	 * 
	 * @return photoArray
	 */
	public String[] getPhoto() {
		return people;
	}

	/**
	 * Returns all the photos in an album
	 * 
	 * @param AlbumName
	 * @return photo
	 */
	public String[] getPhoto(String AlbumName) {
		return people;
	}

	// move an photo from one album to the other
	/**
	 * returns true if the photo was deleted
	 * 
	 * @param PhotoName
	 * @param AlbumName
	 * @return successful
	 */
	public boolean mvPhoto(String PhotoName, String AlbumName) {
		return true;
	}

	// delete a tag
	/**
	 * gets the information from a tag from a photo
	 * 
	 * @param tag
	 * @param photo
	 */
	public void findTag(String tag, Photo p) {
		// goes through the tags and looks for a specific tag name
	}

	// list photo info
	/**
	 * Returns all the tagged people in a given photo
	 * 
	 * @param albumName
	 * @param photo
	 * @return taggedPeople
	 */
	public String[] getPhotoInfo(String albumName, String photo) {
		return people;
	}

	// get photos with chronological order
	/**
	 * Returns the photos in an album
	 * 
	 * @param Album
	 * @return
	 */
	public String[] getPhotos(String Album) {
		return people;
	}

	// search for photos through a certain tag
	/**
	 * returns all the photos with a gien tag and the given tag value
	 * 
	 * @param tag
	 * @param Value
	 * @return people
	 */
	public String[] getTagPhoto(String tag, String Value) {
		return people;
	}

}
