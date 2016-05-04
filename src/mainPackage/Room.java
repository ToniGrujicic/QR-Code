package mainPackage;

public class Room {
	private String room;
	private String day;
	private String subject;
	private String time;

	public Room() {
	}
	
	public String getday(String data)
	{
		String delims = "[:\n]";
		String[] tokens = data.split(delims);
	    day = tokens[1].toString();
		return(day);
	}
	
	public String gettime(String data)
	{
		String delims = "[:\n]";
		String[] tokens = data.split(delims);
	    time = tokens[3].toString();
		return(time);
	}
	
	public String getsubject(String data)
	{
		String delims = "[:\n]";
		String[] tokens = data.split(delims);
	    subject = tokens[5].toString();
		return(subject);
	}
	
	public String getroom(String data)
	{
		String delims = "[:\n]";
		String[] tokens = data.split(delims);
	    room = tokens[7].toString();
		return(room);
	}
}