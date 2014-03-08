package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * @author Jaime Reynoso & Alexander Guzman
 *
 */
public class Album {

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
	
	/**Delete a photo from an album through their name
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
	public ArrayList<Photo> getPhotos()
	{
		return pics;
	}

}
