package cs213.photoAlbum.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.backendModel;

/**
 * @author Jaime Reynoso and Alexander Guzman
 *
 */
public class control {

	private backendModel backend;
	User Person;
	String[] people;
	//login for a user needs to be there
	/**
	 * Creates a contructor object needing a user
	 * @param person
	 */
	public control(){
		backend = new backendModel();
	}
	
	public ArrayList<User> getUsers() {
		return backend.getUsers();
	}
	public User getUser(String userID) throws Exception {
		return backend.getUser(userID.toLowerCase());
	}
	public void addUser(String id, String name) throws Exception {	
		User user = new User(id,name);
		backend.writeAUser(user);
	}
	public void deleteUser(String id) throws Exception{
		backend.deleteUser(id);
	}
	public void createAlbum(User person, String nAlbumName){
		person.addAlbum(nAlbumName);
	}
	public void deleteAlbum(User user, String album) throws Exception{
		Album[] n = user.getAlbums();
		Album p = new Album("zzz");
		int success = 0;
		for(int i = 0; i < n.length; i++){
			if(n[i].AlbumName.equals(album)){
				p = n[i];
				success = 1;
			}
		}
		Exception Exception = null;
		if(success == 0) throw Exception;
		else{
			p.deletePics();
		}
		ArrayList<Photo> pics = p.getPhotos();
		user.delAlbum(album);
		for(Photo a:pics){
			a.removePics();
		}
		
	}
	public void addPhoto(User user, String fileName, String caption, String albumName) throws Exception{
		Album tmp = new Album("zzz");
		try{
			tmp = user.getAlbum(albumName);
		} catch(Exception e){
			throw e;
		}
		tmp.addPhoto(new Photo(fileName, caption));
		
	}
	public void movePhoto(User user, String title, String oldAlb, String newAlb) throws Exception{
		try{
		Album temp1 = user.getAlbum(oldAlb);
		Album temp2 = user.getAlbum(newAlb);
		Photo pic = temp1.getPhoto(title);
		temp1.deletePhoto(pic);
		temp2.addPhoto(pic);} catch(Exception e){
			throw e;
		}
	}
	public void removePhoto(User user, String title, String AlbumName)throws Exception{
		try{
		Album album = user.getAlbum(AlbumName);
		album.deletePhoto(album.getPhoto(title));
		} catch(Exception e){
			throw e;
		}
	}
	
	public void addTag(User user, String fileName, String tagType, String tagValue, String AlbumName) throws Exception{
		try{
		Photo pic = user.getAlbum(AlbumName).getPhoto(fileName);
		pic.addTag(tagType, tagValue);}
		catch(Exception e){
			throw e;
		}
	}
	public void deleteTag(User user, String fileName, String tagType, String tagValue, String alb) throws Exception{
		try{
			Photo pic = user.getAlbum(alb).getPhoto(fileName);
			pic.deleteTag(tagValue);
		} catch(Exception e){
			throw e;
		}
	}
	//create an album for this user
	/**
	 * returns true if createAlbum was possible
	 * @param name
	 * @return successful
	 */
	public boolean createAlbum(String name){
		//this creates an album with the specific name
		return true;
	}
	//delete an album
	/**
	 * Returns true is delete album was viable
	 * @param name
	 * @return successful
	 */
	public boolean deleteAlbum(String name){
		return true;
		//this deletes an album for the user
	}
	//list albums
	/** Returns the name of all the albums
	 * @return albumName array
	 */
	public String[] getAlbums(){
		return people;
	}
	//list photos in an album (retrieve all photos)
	/**Returns a string of all the photo names in the album you last used
	 * @return photoArray
	 */
	public String[] getPhoto(){
		return people;
	}
	/**
	 * Returns all the photos in an album
	 * @param AlbumName
	 * @return photo
	 */
	public String[] getPhoto(String AlbumName){
		return people;
	}
	//move an photo from one album to the other
	/**
	 * returns true if the photo was deleted
	 * @param PhotoName
	 * @param AlbumName
	 * @return successful
	 */
	public boolean mvPhoto(String PhotoName, String AlbumName){
		return true;
	}
	//delete a tag
	/** gets the information from a tag from a photo
	 * @param tag
	 * @param photo
	 */
	public void findTag(String tag, Photo p){
		//goes through the tags and looks for a specific tag name
	}
	//list photo info
	/**
	 * Returns all the tagged people in a given photo
	 * @param albumName
	 * @param photo
	 * @return taggedPeople
	 */
	public String[] getPhotoInfo(String albumName, String photo){
		return people;
	}
	//get photos with chronological order
	/**Returns the photos in an album
	 * @param Album
	 * @return
	 */
	public String[] getPhotos(String Album){
		return people;
	}
	//search for photos through a certain tag
	/** returns all the photos with a gien tag and the given tag value
	 * @param tag
	 * @param Value
	 * @return people
	 */
	public String[] getTagPhoto(String tag, String Value){
		return people;
	}
	public void saveSession() throws IOException, FileNotFoundException {
		backend.saveSession();
	}

}
