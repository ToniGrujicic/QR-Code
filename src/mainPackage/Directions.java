package mainPackage;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Directions {
	private final int ROOM_LENGTH = 6; /* Size of the room string */
	private char building;
	private char floor;
	private String locationOnFloor = null;

/*===========================SET AND GET======================================*/
	public void setBuilding(char building) {
		this.building = building;
	}
	public char getBuilding() {
		return building;
	}

	public void setFloor(char floor) {
		this.floor = floor;
	}
	public char getFloor() {
		return floor;
	}
	
	public void setlocationOnFloor(String locationOnFloor) {
		this.locationOnFloor = locationOnFloor;
	}
	public String getlocationOnFloor() {
		return locationOnFloor;
	}
/*=========================================================================*/	
	
	/* Check and store the info of the room */
	public boolean validate(String room) {
		/*=================VERIFICATION BLOCK=====================*/
		if (room.length() != ROOM_LENGTH) {	//If we enter a room which doesn't respect the IT Standards.
			this.playit("WRONGROOMLENGHT");
			delayfor(10);
			return false;
		}
		if (Character.isLetter(room.charAt(1)) == false) {
			this.playit("WRONGMUSTSTARTWITHLETTER");
			delayfor(10);
			return false; // room must start with a letter
		}
		for (int i = 2; i < ROOM_LENGTH; i++) {
			if (Character.isDigit(room.charAt(i)) == false) {
				this.playit("WRONGNUMBERSONLY");
				delayfor(10);
				return false; // room must be composed by numbers only.
			}
		}
		/*========================================================*/
		// If (as expected) all seems ok -> store the info
		building = room.charAt(1);	/*Stores the letter of the building which is in 2 position */
		floor = room.charAt(2);		/*Stores the number of the floor which is in 3 position */
		locationOnFloor = room.substring(3);	/*Stores the number of the room which begins in 4 position */
		
		return true;
	}


	
	/* Get directions to building */
	public String toBuilding(){
		String directions = null;
		boolean choice = GUI.getchoice();
		switch (this.building) {
		case 'A':
			directions = "From reception, walk straight ahead and then turn to your right. The block A is this part of the IT Sligo.";
			this.playit("BUILDINGA");
			delayfor(10);
			break;
		case 'B':
			if(choice == false){
				directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest";
				this.playit("BUILDINGB");
				delayfor(10);
				break;
			}
			else{
				directions = "From reception, take the lift on your left and go to the first floor. Then turn right. Walk for 40m past Library until you read the Booknest";
				this.playit("BUILDINGBD");
				delayfor(10);
				break;
			}
			
		case 'C':
			if(choice == false){
				directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and conrinute through long corridor";
				this.playit("BUILDINGC");
				delayfor(10);
				break;
			}
			else{
				directions = "From reception, take the lift on your left and go to the first floor. Then turn right.  Walk for 40m past Library until you read the Booknest, then turn left and conrinute through long corridor";
				this.playit("BUILDINGCD");
				delayfor(10);
				break;
			}
		case 'D':
			if(choice == false){
				directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and conrinute through long corridor";
				this.playit("BUILDINGD");
				delayfor(10);
				break;
			}
			else{
				directions = "From reception, take the lift on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and conrinute through long corridor";
				this.playit("BUILDINGDD");
				delayfor(10);
				break;
			}
		case 'E':
			directions = "From reception, move the the centre of reception and turn left into the engineering building";
			this.playit("BUILDINGE");
			delayfor(10);
			break;
		case 'F':
			directions = "From reception, walk outside and turn to your right. Walk past the engineering building and the F block is straigt in front";
			this.playit("BUILDINGF");
			delayfor(10);
			break;
		default:
			directions = "Ooops ! it seems that the building you're looking for doesn't exist !";
			this.playit("BUILDINGUNKNOWN");
			delayfor(10);
			break;
		}
		return(directions);
	}


	/* Get directions to floor */
	public String toFloor(){
		String directions = null;
		boolean choice = GUI.getchoice();
		switch (this.floor) {
		case '0':
			directions = "Stay on this floor";
			this.playit("FLOOR0");
			delayfor(10);
			break;
		case '1':
			if(choice == false){
				directions = "Ascend the stairs to the first floor";
				this.playit("FLOOR1");
				delayfor(10);
				break;
			}
			else{
				directions = "Take the lift to the first floor";
				this.playit("FLOOR1D");
				delayfor(10);
				break;
			}
		case '2':
			if(choice == false){
				directions = "Ascend two flight of stairs to the second floor";			
				this.playit("FLOOR2");
				delayfor(10);
				break;
			}
			else{
				directions = "Take the lift to the second floor";			
				this.playit("FLOOR2D");
				delayfor(10);
				break;
			}
		default:
			directions = "Oops ! It seems that the floor " + this.floor + " doesn't exist !";
			this.playit("FLOORUNKNOWN");
			delayfor(10);
			break;
		}
		return(directions);
	}


	/* Get directions to room */
	public String toLocation() {
		String directions = null;
		switch (this.locationOnFloor){
		case "006":
			directions = "This is a room to the right on this level";
			this.playit("ROOM6");
			delayfor(10);
			break;
		case "007":
			directions = "This is a room to the right on this level";			
			this.playit("ROOM7");
			delayfor(10);
			break;
		case "003":
			directions = "This is the last room to the right on this level";			
			this.playit("ROOM3");
			delayfor(10);
			break;
		case "004":
			directions = "This is the second last room to the right on this level";			
			this.playit("ROOM4");
			delayfor(10);
			break;
		default:
			directions = "Oops ! It seems that this room doesn't exist !";
			this.playit("ROOMUNKNOWN");
			delayfor(10);
			break;
		}
		return(directions);
	}


	
	/* Play the sound given in argument */
	public void playit(String soundWanted){
		String fn = soundWanted;
		File sound;
		fn = "./src/sounds/"+soundWanted+".wav";

		// Go for it!
		try {
			// Open an audio input stream.
			sound = new File(fn);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
			// plays
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* Wait for the time given in argument */
	public static void delayfor(int n)
	{
		try {
		    Thread.sleep(n * 100);                 //100 milliseconds is 0,1 second (We will multiply when necessary).
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
}