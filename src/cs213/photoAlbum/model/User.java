package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * 
 * @author Jaime Reynoso & Alexander Guzman
 * 
 *
 */
public class User {
	/**
	 * These are the initial stuff to use 
	 */
	String user_ID, fullName;
	Album head;
	int numAlbums;
	int placement = 0;
	ArrayList<Photo> pics;
	
	public static final String storeDir = "dat";
	public static final String storeFile = ".dat";
	
	/**
	 * A User needs an ID and they need to have their full name on file
	 * @param userID
	 * @param full_Name
	 */
	public User(String userID, String full_Name )
	{
		this.user_ID = userID;
		this.fullName = full_Name;
	}
	

	public String getUser_ID() {
		return user_ID;
	}


	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public Album getHead() {
		return head;
	}


	public void setHead(Album head) {
		this.head = head;
	}


	public int getNumAlbums() {
		return numAlbums;
	}


	public void setNumAlbums(int numAlbums) {
		this.numAlbums = numAlbums;
	}


	public int getPlacement() {
		return placement;
	}


	public void setPlacement(int placement) {
		this.placement = placement;
	}


	public ArrayList<Photo> getPics() {
		return pics;
	}


	public void setPics(ArrayList<Photo> pics) {
		this.pics = pics;
	}
	
	public Photo getPhoto(String name) throws Exception{
		name = name.toLowerCase();
		if(!pics.contains(name)){
			return null;
		}
		else{
			for(int x=0;x< pics.size();x++){
				if(pics.get(x).fileName==name){
					return pics.get(x);
				}
			}
		}
		return null;
	}


	public boolean addAlbum(String name)
	{
		if(head == null){
			head = new Album(name);
			numAlbums++;
			return true;
		}
		else{
			return addAlbum(head, name);
		}
	}
	public Album getAlbum(String name) throws Exception{
		Exception Exception = null;
		if(head == null) throw Exception;
		else{
			return getAlbum(head, name);
		}
	}
	private Album getAlbum(Album head2, String name) throws Exception {
		Exception Exception = null;
		if(head2 == null) throw Exception;
		else{
			if(head2.AlbumName == name) return head2;
			else if(head2.AlbumName.compareTo(name) < 0) return getAlbum(head2.left, name);
			else return getAlbum(head2.right, name);
		}
	}


	private boolean addAlbum( Album top, String name)
	{
		if(top == null)
		{
			top = new Album(name);
			numAlbums++;
			return true;
		}
		if( top.AlbumName.compareTo(name) < 0)  return addAlbum(top.left, name);
		else if(top.AlbumName.compareTo(name) > 0)  return addAlbum(top.right, name);
		else return false;
	}
	public boolean delAlbum( String name)
	{
		if(head == null)
		{
			return false;
		}
		else{
			return delAlbum(head, name);
		}
	}
	private boolean delAlbum( Album top, String name)
	{
		if( top == null) return false;
		if(top.AlbumName.compareTo(name) == 0){
			if(top.left != null) top.AlbumName = findLargestAlbumLeft(top);
			else if(top.right != null) top.AlbumName = findSmallestAlbumRight(top);
			else top = null;
			numAlbums--;
			return true;
		}
		else if(top.AlbumName.compareTo(name) < 0) return delAlbum(top.left, name);
		else return delAlbum(top.right, name);
	}
	private String findLargestAlbumLeft(Album top){
		if(top.right == null){
			String tmp = top.AlbumName;
			top = null;
			return tmp;
		}
		else return findLargestAlbumLeft(top.right);
	}
	private String findSmallestAlbumRight(Album top){
		if(top.left == null){
			String tmp = top.AlbumName;
			top = null;
			return tmp;
		} else return findSmallestAlbumRight(top.left);
	}
	public boolean editAlbum(String oldAlbum, String newAlbum){
		
		boolean returnAns = (delAlbum(oldAlbum) && addAlbum(newAlbum));
		return returnAns;
	}
	public ArrayList<Album> getAlbums(){
		ArrayList<Album> currentAlbums = new ArrayList<Album>();
		if(head == null) return currentAlbums;
		else addAlbumtoArray(head, currentAlbums);
		placement = 0;
		return currentAlbums;
	}
	private void addAlbumtoArray(Album head2, ArrayList<Album> currentAlbums) {
		if(head == null) return;
		else{
			currentAlbums.add(head2);
			placement++;
			if(head2.left!= null) addAlbumtoArray(head2.left, currentAlbums);
			if(head2.right != null) addAlbumtoArray(head2.right, currentAlbums);
		}
	}
}
