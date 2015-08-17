import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar implements ActionListener {
	JMenu file;
	JMenuItem add;
	Planner planner;
	
	//Constructor called from the Planner constructor to make the Menubar at the top
	public Menu(Planner p){
		planner = p;
		file = new JMenu("File");
		this.add(file);
		
		//Adds the "add" button. You need all these lines to add more menu items
		add = new JMenuItem("Add");
		add.addActionListener(this);
		add.setActionCommand("add");
		file.add(add);
		
	}

	
	//When the event calls the ActionCommand "add" , createActivity() is called.
	@Override
	public void actionPerformed(ActionEvent e) {
		String event =  e.getActionCommand();
		if(event.equals("add")){
			planner.createActivity();
		}
	}
}
