package mainPackage;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 

public class GUI{
	
	private static boolean choice = false;
  
	private JFrame frame = new JFrame ("QR Code"); 
	private JPanel[] panels = new JPanel [30]; 
	private JTextField textField0 = new JTextField();
	private JTextField textField1 = new JTextField();
	private JTextField textField2 = new JTextField();
	private JTextField textField3 = new JTextField();
	private JTextField textField4 = new JTextField();
	private JTextField textField5 = new JTextField();
	private JTextField textField6 = new JTextField();
	private JTextField textField7 = new JTextField();
	private JButton disabledButton = new JButton ("I want to take the lift !");
	public JButton gotocourseButton = new JButton ("Go To My Course !");
	public static Room room = new Room();
	public static Directions directions = new Directions();
	

	/* Initial setup function */
	public GUI(){
		/*  The GUI is laid out in a frame.  The frame contains a number of rows.
		 *  Each row contains a number of buttons.*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		JPanel contentPane = (JPanel) frame.getContentPane();
		// initialize panels 
		for (int i = 0; i < panels.length; i++) { 
			panels[i] = new JPanel (); 
		}

		/* Text Field 0 for the welcoming message */
		textField0.setText ("  Hello ! Check if you want to take the lift, then press Go To My Course  "); 
		textField0.setHorizontalAlignment (JTextField.CENTER);
		panels[1].add (textField0);
		
		/* Text Field 1 for the choice check message */
		textField1.setColumns(20);
		textField1.setText (" Defaut - Stairs ");
		textField1.setHorizontalAlignment (JTextField.CENTER);
		panels[3].add (textField1);
		
		/* Text Field 2 for the departure instruction */
		textField2.setColumns(25);
		textField2.setHorizontalAlignment (JTextField.CENTER);
		panels[10].add (textField2);
		
		/* Text Field 3 for the first instructions (building) */
		textField3.setColumns(95);
		textField3.setHorizontalAlignment (JTextField.CENTER);
		panels[13].add (textField3);
		
		/* Text Field 4 for the second instructions (floor) */
		textField4.setColumns(25);
		textField4.setHorizontalAlignment (JTextField.CENTER);
		panels[14].add (textField4);
		
		/* Text Field 5 for the third instructions (room) */
		textField5.setColumns(30);
		textField5.setHorizontalAlignment (JTextField.CENTER);
		panels[15].add (textField5);
		
		/* Text Field 6 for the QR creation Confirmation */
		textField6.setColumns(50);
		textField6.setHorizontalAlignment (JTextField.CENTER);
		panels[21].add (textField6);
		
		/* Text Field 7 for the course recap' */
		textField7.setColumns(60);
		textField7.setHorizontalAlignment (JTextField.CENTER);
		textField7.setText ("  A Recap' of your course will be displayed here  ");
		panels[6].add (textField7);
		
		/* Creation of the two buttons */
		panels[2].setLayout (new FlowLayout (FlowLayout.CENTER));
		panels[2].add (disabledButton);
		panels[7].add (gotocourseButton);
		
		disabledButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(choice == false){
					choice = true;
					textField1.setText ("  You have chosen to take the lift !  ");
					disabledButton.setText("I want to take the Stairs !");
				}
				else{
					choice = false;
					textField1.setText ("  You have chosen to take the stairs !  ");
					disabledButton.setText("I want to take the Lift !");
				}
			}
		});
		
		gotocourseButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	/* Print the brute barcode (Optional)
	  		 * System.out.println("Here are the information about this course : \n" + QRdata);*/
	    	//Print some info and find the Room:
	    	textField6.setText ("If you want to see again those information, a QR Image has been created in your Explorer :) !");
	    	String theDay = room.getday(QRCodeMain.QRData);
	  		String theSubject = room.getsubject(QRCodeMain.QRData);
	  		String theTime = room.gettime(QRCodeMain.QRData);
	  		String theRoom = room.getroom(QRCodeMain.QRData);
	  		//Print a Recap' of the course read:
	  		textField7.setText ("The " + theSubject + " course you're looking for is dispended on " + theDay + " at " + theTime + " in" + theRoom + ".");
	  		// If the room given is incorrect...
	  		if (directions.validate(theRoom) == false) {
	  			textField2.setText ("This room doesn't exist or is mispelled");
	  			textField7.setText ("The course you're looking for may be mispelled");
	  			textField6.setText ("A QR Image has been created in your Explorer, even if it leads to a wrong room ;) !");
	  		}
	  		else {
	  			//Give directions
	  			textField2.setText (" Here is your route to " + theSubject + " !");
	  			textField3.setText (directions.toBuilding());
	  			textField4.setText (directions.toFloor());
	  			textField5.setText (directions.toLocation());
	  		}
	      }
	    });
    
    contentPane.setLayout (new BoxLayout (contentPane, BoxLayout.Y_AXIS)); 
    for (JPanel jPanel : panels) { 
      contentPane.add (jPanel); 
    }
    frame.pack (); 
    frame.setVisible (true); 
	}
	
	public void setchoice(boolean choice) {
		this.choice = choice;
	}
	public static boolean getchoice()
	{
		return(choice);
	}
}