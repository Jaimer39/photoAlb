package cs213.photoAlbum.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import cs213.photoAlbum.control.control;
import cs213.photoAlbum.model.User;
import cs213.*;

/**
 * Command line
 * @author Alexander Guzman & Jaime Reynoso
 *
 */
public class CmdView {
	
	private static class UserComparator implements Comparator<User> {
		@Override
		public int compare(User user_1, User user_2) {
			return user_1.getUser_ID().compareTo(user_2.getUser_ID());
		}
	}
	
	/**
	 * Get all users
	 * 
	 */
	public static void listUsers() {
		
		control controller = null;
		try {
			controller = new control();
		} catch (Exception e){
			
		}
		
		ArrayList<User> users = controller.getUsers();
		Collections.sort(users, new UserComparator());
		
		for (User user: users) System.out.println(user);
		if (users.size() == 0) System.out.println("List is empty");
	}
	
	/**
	 * Add new user
	 * 
	 * @param args: command line supplied arguments array
	 * @throws Exception 
	 */
	public static void addUser(String[] args) throws Exception {
		if (args.length < 2) {
			System.err.println("Error: adduser requires a user's ID and name");
			return;
		}
		
		control controller = null;
		try {
			controller = new control();
		} catch (Exception e){
			
		}
		
		String id = args[0];
		String userName = args[1];
		try {
			controller.addUser(id, userName);
			controller.saveSession();
			System.out.println("User created");
		} catch (Exception e) {
			User user;
			try {
				user = controller.getUser(id.toLowerCase());
			} catch (Exception ee) {
				System.err.println("Something failed");
				return;
			}
			System.err.println("User duplicate");
		}
	}
	
	/**
	 * Delete a user
	 * 
	 * @param args: command line supplied arguments array
	 * @throws Exception 
	 */
	public static void deleteUser(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("Error: Enter user id");
			return;
		}

		control controller = null;
		try {
			controller = new control();
		} catch (Exception e){
			
		}
		
		String id = args[0];
		try {
			controller.deleteUser(id.toLowerCase());
			controller.saveSession();
			System.out.println("User: "+ id + " deleted");
		} catch (Exception e) {
			System.err.println("User not found");
		} 
	}
	
	/**
	 * User login
	 * 
	 * @param args: command line supplied arguments array
	 * @throws Exception 
	 */
	public static void login(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("Not enough number of arguments");
			return;
		}
		
		String id = args[0];
		try {
			new textView(id);
		} catch (Exception e){
			
		}
	}
	
	/**
	 * Received command line from user
	 * 
	 * @param args: command line supplied arguments
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			String mode = args[0];
			String[] argOptions = Arrays.copyOfRange(args, 1, args.length);
			
			if	(mode.equalsIgnoreCase("listusers"))  
				CmdView.listUsers();
			else if (mode.equalsIgnoreCase("adduser"))   
				CmdView.addUser(argOptions);
			else if (mode.equalsIgnoreCase("deleteuser")) 
				CmdView.deleteUser(argOptions);
			else if (mode.equalsIgnoreCase("login"))      
				CmdView.login(argOptions);
			else System.out.println("Error: Valid modes: listusers, adduser, deleteuser, login");
		} else {
			System.err.println("Error: Not enough number of arguments");
		}
	}
}