import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Planner {
	JFrame frame;
	JPanel main;
	Menu menu;
	JLabel[] activities = new JLabel[10];
	boolean contains;
	Date[] dates = new Date[10];
	String[] urls = new String[10];
	Dimension preferredSize = new Dimension(300, 300);
	String name;
	int counter = 0;
	Color black = new Color(255, 255, 255);
	
	//Constructor for the class. When the main calls Planner(), this is initiated creating the window.
	public Planner(){
		frame = new JFrame();
		main = new JPanel();
		menu = new Menu(this);
		frame.setPreferredSize(preferredSize);
		frame.add(main);
		frame.setJMenuBar(menu);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Myva");
		JLabel loading = new JLabel();
		JOptionPane pane = new JOptionPane();
        pane.showMessageDialog( null, "Hi. ");
        name = pane.showInputDialog("What is your name:");
	}

	
	//When you click add in the menu this is called.
	public void createActivity() {
		JLabel newActivity = new JLabel();
		JOptionPane pane = new JOptionPane();
		frame.add(pane);
		
		//Asks for the info
		String activity = pane.showInputDialog("What activity whould you like to input?");
		String time = pane.showInputDialog("When do you want to schedule that activity? (ie. 4:00-6:00)");
		int x = pane.showConfirmDialog(null, "Would you like me to direct you to a website for that activity?");
		String site = null;
		//Sets the text to display
		newActivity.setText(activity + "\n" + time);
		
		//If they want to add a url, open this dialog.
		if(x == 0) {
			site = pane.showInputDialog("Please input the website url");
			newActivity.setText("<html>"+activity + " <br/>" + time + "<br/>" + site+"</br> </html>");
		}

		//Confirmation Dialog
		pane.showMessageDialog( frame, "Your activity is now scheduled. ");
		//Gets the Date for the event.
		String[] times = time.split("-");
	    DateFormat df = new SimpleDateFormat("kk:mm", Locale.ENGLISH);
		Calendar c = df.getCalendar();
	    c.set(2015, 8, 16);
	    Date result = c.getTime();
		try {
			result = df.parse(times[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		//Puts all the info in public arrays for display use, Not Optimal. Will Replace Soon.
		activities[counter] = newActivity;
		dates[counter] = result;
		urls[counter]  = site;
		counter++;
		contains = true;
		main.add(newActivity);
		frame.revalidate();		//refreshes the screen after adding the latest JPanel
	}
}
	