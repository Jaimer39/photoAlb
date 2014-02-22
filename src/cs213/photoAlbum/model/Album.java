package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * @author Jaime Reynoso & Alexander Guzman
 * 
 */
public class Album {

	String AlbumName;
	ArrayList<Photo> pics;
	Album tail;

	/**
	 * The constructor for the album
	 * 
	 * @param name
	 */
	public Album(String name) {
		this.AlbumName = name;
		pics = new ArrayList<Photo>();
	}

	/**
	 * Delete a photo from an album through their name
	 * 
	 * @param name
	 */
	public void deletePhoto(Photo name) {
		pics.remove(name);
	}

	/**
	 * Add a photo
	 * 
	 * @param name
	 */
	public void addPhoto(Photo name) {
		pics.add(name);
	}

}
