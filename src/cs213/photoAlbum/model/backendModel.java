package cs213.photoAlbum.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Jaime Reynoso and Alexander Guzman
 *
 */
public class backendModel implements backend {

	private User LoggedUser;
	ArrayList<User> users;
	private String modelFileName = ".." + File.separator + "data" + File.separator + "photo-album.dat";
	
	/**Default constructor for a backendModel
	 * 
	 */
	public backendModel(){
		
		FileInputStream src = null;
		ObjectInputStream src2 = null;
		try {
			src = new FileInputStream(modelFileName);
			src2 = new ObjectInputStream(src);
			users = (ArrayList<User>)src2.readObject();
			src2.close();
		} catch (Exception e) {
			users = new ArrayList<User>();
		}
	}

	@Override
	public User readAUser(String name) {
		for(User a: users){
			if(a.getFullName().equalsIgnoreCase(name)){
				return a;
			}
		}
		return null;
	}

	@Override
	public void writeAUser(User info) throws Exception {
		for(int i = 0; i < users.size(); i++){
			if(users.get(i) == info){
				Exception Exception = null;
				throw Exception ;
			}
		}
		users.add(info);
		
	}

	public User getUser(String UserID) throws Exception{
		User newP;
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).user_ID == UserID){
				return users.get(i);
			}
		}
		Exception Exception = null;
		throw Exception;
	}
	@Override
	public void deleteUser(String name) throws Exception{
		Exception Exception = null;
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).fullName == name){
				users.remove(i);
				return;
			}
		}
		throw Exception;
	}
	public void saveSession() throws IOException, FileNotFoundException{
	FileOutputStream des = null;
	ObjectOutputStream out = null;
	des = new FileOutputStream(modelFileName);
	out = new ObjectOutputStream(des);
	out.writeObject(users);
	out.close();
	
	}

	@Override
	public ArrayList<User> getUsers() {
		return this.users;
	}

}
