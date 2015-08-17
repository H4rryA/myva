import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {

	static Planner p;
	
	//Starts everything up. Creates the main window and initiates the time loop.
	public static void main(String[] args) {
		p = new Planner();
		loop();
	}

	//Checks every 15 seconds to see if any event has started
	private static void loop() {
		Date current = new Date();
		boolean event = false;
		boolean done = false;
	    DateFormat df = new SimpleDateFormat("kk:mm", Locale.ENGLISH);
	    
		while(!done){		//infinite loop, not good programming! Will close later
			try {
				Thread.sleep(15000);	//waits 15000 milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			String d = df.format(cal.getTimeInMillis());
			try {
				current = df.parse(d);	//gets the current time
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			if(p.contains == true){
				Date[] z = p.dates;
				for(int a = 0; a <= p.dates.length; a++){
					event = z[a].after(current);
					if(event){				//If an event has started
						String url = p.urls[a];
						if(Desktop.isDesktopSupported()){
							try {
								Desktop.getDesktop().browse(new URI(url));	//launch the Url for the event.
							} catch (IOException e) {
								e.printStackTrace();
							} catch (URISyntaxException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
	}

}
