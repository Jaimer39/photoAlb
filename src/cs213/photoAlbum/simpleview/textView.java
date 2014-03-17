package cs213.photoAlbum.simpleview;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs213.photoAlbum.control.control;
import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;

/**
 * Textview of the app
 * 
 * @author Alexander Guzman & Jaime Reynoso
 *
 */
public class textView {

	
	private class Compare_Albums implements Comparator<Album> {
		@Override
		public int compare(Album a, Album b) {
			return a.getAlbumName().compareToIgnoreCase(b.getAlbumName());
		}
	}
	
	private class Compare_Dates implements Comparator<Photo> {
		@Override
		public int compare(Photo a, Photo b) {
			return a.getTime().compareTo(b.getTime());
		}
	}

	private final Compare_Albums albumComp           = new Compare_Albums();
	private final Compare_Dates dateComp   = new Compare_Dates();
	
	private final String tokenizer_regex = "(\\S+:\"[^\"]+\",?|[^\"]\\S*|\".*?\")\\s*"; //separates arguments by command:"argument"
	
	private control control;
	private String user_ID;
	private Scanner in;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
	private String[] validCommands = {"createAlbum", "deleteAlbum", "listAlbums",
	                                  "listPhotos", "addPhoto", "movePhoto", "addTag",
	                                  "removePhoto", "deleteTag", "listPhotoInfo",
	                                  "getPhotosByDate", "getPhotosByTag", "logout"};

	/**
	 * Constructor
	 * 
	 * @param user_ID of logged in user
	 * @throws Exception 
	 */
	public textView(String user_ID) throws Exception {
		this.user_ID = user_ID;
		in    = new Scanner(System.in);
		control  = new control();
		
		try {
			control.getUser(user_ID);
		} catch (Exception e) {
			System.err.println("Something failed");
		}
		dateFormat.setLenient(false); 
		interact();
	}
	
	/**
	 * Interact(or?er?) with command line view.
	 * @throws Exception 
	 * 
	 */
	private void interact() throws Exception {
		String command   = null;
		String input     = null;
		String[] options = null;

		
		while (true) {
			System.out.print("> ");
			try {
				input = captureInput();
			} catch (NoSuchElementException e) { 
				System.out.println();
				break;
			}
			if (input.length() == 0) continue;

			ArrayList<String> opts = new ArrayList<String>();
			
			Matcher m = Pattern.compile(tokenizer_regex).matcher(input);

			while (m.find()) {
				String opt = m.group(1);

				int op_length       = opt.length()-1;
				int i_first = opt.indexOf('"');
				int i_last  = opt.lastIndexOf('"');
				int i_colon      = opt.indexOf(":");
				int i_comma      = opt.lastIndexOf(",");
				
				if (i_first == 0 && i_last == op_length) {
					opt = opt.substring(1, op_length);
				} else if (i_colon == i_first - 1 && (i_last == op_length || i_last == i_comma - 1)) {
					opt = opt.substring(0, i_first) + opt.substring(i_first+1, i_last);
				}
				
				opts.add(opt);
			}
			command = opts.get(0);
			
			options = Arrays.copyOfRange(opts.toArray(new String[opts.size()]), 1, opts.size());

			if      (command.equalsIgnoreCase("createAlbum"))     createAlbum(options);
			else if (command.equalsIgnoreCase("deleteAlbum"))     deleteAlbum(options);
			else if (command.equalsIgnoreCase("listAlbums"))      listAlbums();
			else if (command.equalsIgnoreCase("getPhotosByDate")) getPhotosByDate(options);
			else if (command.equalsIgnoreCase("getPhotosByTag"))  getPhotosByTag(options);
			else if (command.equalsIgnoreCase("listPhotos"))      listPhotos(options);
			else if (command.equalsIgnoreCase("addPhoto"))        addPhoto(options);
			else if (command.equalsIgnoreCase("movePhoto"))       movePhoto(options);
			else if (command.equalsIgnoreCase("deleteTag"))       deleteTag(options);
			else if (command.equalsIgnoreCase("listPhotoInfo"))   listPhotoInfo(options);
			else if (command.equalsIgnoreCase("removePhoto"))     removePhoto(options);
			else if (command.equalsIgnoreCase("addTag"))          addTag(options);
			else if (command.equalsIgnoreCase("logout"))          break;
			else displayCommands();
		}

		try {
			control.saveSession();
		} catch (IOException e) {
			System.err.println("Error: session couldn't be saved");
		}
	}

	/**
	 * Create a new album
	 * 
	 * @param args: command line supplied arguments
	 */
	private void createAlbum(String[] options) {
		if(options.length != 1){
			System.err.println("No name for the album found");
			return;
		}
		
		String albumName = options[0];
		try {
			control.getUser(user_ID).addAlbum(albumName);
			System.out.println(user_ID + " created an album");
			System.out.println(albumName);
		} catch (Exception e) {
			System.out.println(user_ID + " already has an album");
			System.err.println(albumName);
			return;
		}
	}

	/**
	 * Delete album
	 * 
	 * @param args: command line supplied arguments
	 */
	private void deleteAlbum(String[] options) {
		if(options.length != 1){
			System.err.println("To delete album, please provide a name");
			return;
		}
		
		String albumName = options[0];
		try {
			control.getUser(user_ID).delAlbum(albumName);
			System.out.println(user_ID + " deleted an album");
			System.out.println(albumName);
		} catch (Exception e) {
			System.out.println(user_ID + " does not have an album with that name");
			System.out.println(albumName);
			return;
		}
	}

	/**
	 * Get all album names
	 */
	private void listAlbums() {
		
		List<Album> albums = new ArrayList<Album>();
		try {
			albums = control.getUser(user_ID).getAlbums();
			Collections.sort(albums, albumComp);
		} catch (Exception e) {

		}
		
		if(albums.isEmpty()){
			System.out.println(user_ID + " has no albums available");
			return;
		}
		
		System.out.println(user_ID + " albums: ");
		
		for(Album album : albums){
			System.out.println(album);
		}
	}

	/**
	 * Get all photos in the album
	 * 
	 * @param args: command line supplied arguments
	 */
	private void listPhotos(String[] args) {
		if (args.length != 1) {
			System.err.println("Please provide an album name");
			return;
		}
		
		String albumName = args[0];
		try {
			List<Photo> photos = new ArrayList<Photo>();
			photos = control.getUser(user_ID).getAlbum(albumName).getPhotos();
			Photo[] sortedPhotos = photos.toArray(new Photo[photos.size()]);
			Arrays.sort(sortedPhotos);
			
			if (photos.size() == 0) {
				System.out.println(albumName + " is empty");
				return;
			}
			
			System.out.println(albumName + "contains : ");
			for(Photo photo: sortedPhotos){
				System.out.println(photo);
			}
			
		} catch (Exception e) {
			System.err.println("Album couldn't be found");
		} 
	}

	/**
	 * Add photo to album
	 * 
	 * @param args: command line supplied arguments
	 */
	private void addPhoto(String[] args) {
		if (args.length != 3) {
			System.err.println("Incorrect amount of arguments." + args.length + " not equal to 3");
			return;
		}
		
		String pn    = args[0];
		String pc = args[1];
		String an    = args[2];
		
		try {
			control.addPhoto(control.getUser(user_ID), pn, pc, an);
			System.out.println("Added photo " + pn + ":");
			System.out.println(control.getUser(user_ID).getPhoto(pn).getCaption() + " - Album: " + an);
		} catch (Exception e){
			
		}
	}

	/**
	 * Arrange photos
	 * 
	 * @param args: command line supplied arguments
	 */
	private void movePhoto(String[] args) {
		if (args.length < 3) {
			System.err.println("Incorrect amount of arguments." + args.length + " not equal to 3");
			return;
		}
		
		String pn    = args[0];
		String an = args[1];
		String dest_an = args[2];
		try {
			control.movePhoto(control.getUser(user_ID), pn, an, dest_an);
			System.out.println("Moved photo " + pn);
			System.out.println(pn+" - From album "+ an +"to album "+ dest_an);
		} catch (Exception e){
			
		}
	}

	/**
	 * Delete photo
	 * 
	 * @param args: command line supplied arguments
	 */
	private void removePhoto(String[] args) {
		if (args.length < 2) {
			System.err.println("Not enough number of arguments");
			return;
		}
		
		String pn = args[0];
		String an = args[1];
		try {
			control.removePhoto(control.getUser(user_ID), pn, an);
			System.out.println("Removed photo");
			System.out.printf(pn + " - From album " + an);
		} catch (Exception e){
			
		}
	}
	
	/**
	 * Add a tag to a specified photo
	 * 
	 * @param args: command line supplied arguments
	 */
	private void addTag(String[] args) {
		if (args.length < 2) {
			System.err.println("Not enough number of arguments");
			return;
		}
		
		String pn = args[0];
		String[] tag = args[1].split(":");
		if (tag.length < 2) {
			System.err.println("Error with tag. Correct Example: tag:\"value\"");
			return;
		}
		
		String type  = tag[0];
		String value = tag[1];
		
		Photo photo;
		try {
			photo = control.getUser(user_ID).getPhoto(pn);
			photo.addTag(type.toLowerCase(), value);
			System.out.println("Added tag:");
			System.out.println(pn + type+": "+value);
		} catch (Exception e){
			
		}
		
	}

	/**
	 * Remove tag
	 * 
	 * @param args: command line supplied arguments
	 */
	private void deleteTag(String[] args) {
		if (args.length < 2) {
			System.err.println("Not enough number of arguments");
			return;
		}
		
		String photoName = args[0];
		String[] tag = args[1].split(":");
		if (tag.length < 2) {
			System.err.println("Error with tag. Correct Example: tag:\"value\"");
			return;
		}
		
		String type  = tag[0];
		String value = tag[1];
		
		Photo photo;
		try {
			photo = control.getUser(user_ID).getPhoto(photoName);
			photo.deleteTag(value);
			System.out.println("Deleted tag:");
			System.out.println(photoName + type+": "+value);
		} catch (Exception e){
			
		}
	}

	/**
	 * Get photo information
	 * 
	 * @param args: command line supplied arguments
	 */
	private void listPhotoInfo(String[] args) {
		if (args.length < 1) {
			System.err.println("Not enough numebr of arguments");
			return;
		}
		
		String pn = args[0];
		Photo photo;
		try {
			photo = (Photo)control.getUser(user_ID).getPhoto(pn);
		} catch (Exception e) {
			System.err.println("Photo not found");
			return;
		} 

		System.out.println("Photo file name: " + photo.getFileName());
		System.out.print("Album: ");
		ArrayList<Album> albums = photo.getAlbums();
		for (int i = 0; i < albums.size(); i++) {
			if (i > 0) System.out.print(", ");
			System.out.print(albums.get(i).getAlbumName());
		}
		System.out.println();
		System.out.println("Date: " + dateFormat.format(photo.getTime().getTime()));
		System.out.println("Caption: " + photo.getCaption());
		System.out.println("Tags:");
		
		//Todo: Not finished
	}

	/**
	 * Get all photos by chronological order.
	 * 
	 * @param args command line supplied arguments
	 * @throws Exception 
	 */
	private void getPhotosByDate(String[] args) throws Exception {
		if (args.length < 2) {
			System.err.println("Not enough number of arguments.");
			return;
		}
		
		Calendar start = Calendar.getInstance();
		Calendar end   = Calendar.getInstance();

		String start_str = args[0];
		String end_str   = args[1];
		try {
			start.setTime(dateFormat.parse(start_str));
			end.setTime(dateFormat.parse(end_str));

			if (start.compareTo(end) > 0) {
				System.err.println("Start date cannot be a later date than end");
				return;
			}
		} catch (ParseException e) {
			System.err.println("MM/DD/YYYY-hh:mm:ss is the correct format. An incorrect format was found.");
			return;
		}
		
		List<Photo> photos;
		try {
			photos = control.getPhotosByDate(control.getUser(user_ID), start, end);
		} catch (Exception e) {
			System.err.println("Something failed");
			return;
		}
		
		Collections.sort(photos, dateComp);
		if (photos.size() > 0) {
			System.out.printf("Photos for user "+ user_ID +" in range " + start_str + " to "+ end_str);
			for (Photo photo: photos) System.out.println(photo.toString());
		} else {
			System.out.println("No photos found");
		}
	}

	/**
	 *	Get all photos in chronological order based on tags
	 * 
	 * @param args command line supplied arguments
	 * @throws Exception 
	 */
	private void getPhotosByTag(String[] options) throws Exception {
		if (options.length < 1) {
			System.err.println("Not enough number of arguments");
			return;
		}

		ArrayList<Photo> photos;
		try {
			photos = control.getTagPhoto(control.getUser(user_ID), options);
		} catch (Exception e) {
			System.err.println("Something failed");
			return;
		}
		
		if (photos.size() > 0) {
			Collections.sort(photos, dateComp);
			System.out.print("Photos for user "+  user_ID +" with tags:");
			for (String tag: options) System.out.print(" " + tag);
			System.out.println();
			for (Photo photo: photos) System.out.println(photo.toString());
		} else {
			System.out.println("No photos met that critera");
		}
	}
	
	/**
	 * User help. Display all commands
	 * 
	 */
	private void displayCommands() {
		System.out.print("Error: Invalid command (valid commands:");
		for (String command: validCommands) {
			System.out.print(" " + command);
		}
		System.out.println(")");
	}
	
	/**
	 *	System.in
	 * 
	 * @return user input
	 */
	private String captureInput() {
		return in.nextLine();
	}
}