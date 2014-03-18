package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Jaime Reynoso & Alexander Guzman
 *
 */
public class Album implements Serializable{

	public String AlbumName;
	ArrayList<Photo> pics;
	Album right, left;
	
	
	/**The constructor for the album
	 * @param name
	 */
	public Album(String name)
	{
		this.AlbumName = name;
		pics = new ArrayList<Photo>();
	}
	
	/**Returns a photo that is in the album
	 * @param name
	 */
	public Photo getPhoto(String name) throws Exception{
		for(Photo a: pics){
			if(a.fileName == name){
				return a;
			}
		}
		throw new Exception();
		
	}
	/**This disassociates a picture from an album
	 * 
	 */
	public void deletePics(){
		for(int i = 0; i < pics.size(); i++){
			pics.get(i).deleteAlbum(this.AlbumName);
		}
	}
	public boolean equals(Object o)
	{
		if(o == null) return false;
		if(!(o instanceof Album)) return false;
		Album tmp = (Album) o;
		return(tmp.AlbumName.equals(AlbumName));
	}
	/**Delete a photo from an album
	 * @param name
	 */
	public void deletePhoto(Photo name){
		pics.remove(name);
	}
	/** Add a photo
	 * @param name
	 */
	public void addPhoto(Photo name){
		name.addAlbum(this.AlbumName);
		pics.add(name);
	}
	/**Returns photos from album
	 * @return list of photos
	 */
	public ArrayList<Photo> getPhotos()
	{
		return pics;
	}

	/**Get name of album
	 * @return AlbumName
	 */
	public String getAlbumName() {
		return AlbumName;
	}

	/**Changes the Albums name
	 * @param albumName
	 */
	public void setAlbumName(String albumName) {
		AlbumName = albumName;
	}
}
