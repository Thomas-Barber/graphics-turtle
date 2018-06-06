import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame
{	
	//Create JFrame to add all panels onto.
	JFrame graphics = new JFrame();
	//Create graphics panel Object to allow us to draw onto the image.
	GraphicsPanel gp = new GraphicsPanel();
	//Create JPanel for statistics such as current bearing and save status.
	JPanel stats = new JPanel();
		//Create a JLabel for the current turtle co-ords.
		JLabel coords = new JLabel("X: " + Turtle.getxCoord() + "  " + "Y: " + Turtle.getyCoord() + "  ");
		//Create a JLabel for save status.
		JLabel saved = new JLabel("Saved: " + gp.getSaved() + "  ");
		//Create a JLabel for bearing.
		JLabel bearing = new JLabel("Bearing: " + Turtle.getBearing() + "  ");
	
	//Create JPanel for; enter button, command line and basic operation buttons such as forward, back, left, right, save, load and clear.
	JPanel cmdLine = new JPanel();
		//Create JTextField 'input' to add to above JPanel.
		JTextField input = new JTextField(10);
		//Create JButton 'enter' to add to above JPanel.
		JButton button = new JButton("Enter");
		//The String that will hold what the user enters into the cmdLine.
		private String command = new String();
		//Create JButton 'forward' to add to above JPanel.
		JButton forward = new JButton("Forward");
		//Create JButton 'backward' to add to above JPanel.
		JButton back = new JButton("Backward");
		//Create JButton 'turn left' to add to above JPanel.
		JButton left = new JButton("Turn Left");
		//Create JButton 'turn right' to add to above JPanel.
		JButton right = new JButton("Turn Right");
		//Create JButton 'save' to add to the above JPanel.
		JButton saveB = new JButton("Save");
		//Create JButton 'load' to add to the above JPanel.
		JButton loadB = new JButton("Load");
		//Create JButton 'clear' to add to the above JPanel.
		JButton clear = new JButton("Clear");
		//Create a JButton 'Colour' to add to the above JPanel.
		JButton colourButton = new JButton("Colour");
		//Create JColorChooser that will be activated when the 'Colour' button is pressed.
		JColorChooser colour = new JColorChooser();
		
	//Create JMenuBar.
	JMenuBar menuBar = new JMenuBar();
		//Create 'File' in the menu.
	    JMenu file = new JMenu("File");
	    	//Add 'new' image icon to drop down list.
	    	ImageIcon newImg = new ImageIcon("resources\\new.png");
		    //Create 'New' in the file drop down menu.
		    JMenuItem newMenu = new JMenuItem("New", newImg);
		    //Add 'load' image icon to drop down list.
		    ImageIcon load = new ImageIcon("resources\\load.png");
			//Create 'Load' in the file drop down menu.
			JMenuItem loadMenu = new JMenuItem("Load", load);
			//Add 'save' image icon to drop down list.
		    ImageIcon save = new ImageIcon("resources\\save.png");
			//Create 'Save' in the file drop down menu.
			JMenuItem saveMenu = new JMenuItem("Save", save);
			//Add 'exit' image icon to drop down list.
		    ImageIcon exit = new ImageIcon("resources\\exit.png");
			//Create 'Exit' in the file drop down menu.
			JMenuItem exitMenu = new JMenuItem("Exit", exit);
	    //Create 'Help' in the menu.
	    JMenu help = new JMenu("Help");
		    //Add 'about' image icon to drop down list.
		    ImageIcon about = new ImageIcon("resources\\about.png");
			//Create 'About' in the file drop down menu. 
			JMenuItem aboutMenu = new JMenuItem("About", about);
	
	//Constructor.
	GUI()
	{
		//MISC STUFF--------------------
		//Creates toolkit object so we can now use toolkit methods.
		Toolkit tk = Toolkit.getDefaultToolkit();
		//Hold width and height of screen.
		Dimension dim = tk.getScreenSize();
		//------------------------------
		
		//Window Data-------------------
		//Set title.
		this.setTitle("Graphical Application - C3481722");
		//Set window size.
		this.setSize(900, 800);
		
		//Set window to centre of screen.
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set the command line text to say this message.
		input.setText("Type Here");
		//Add JButton  'button(enter)' to the JPanel 'cmdLine'.
		cmdLine.add(button);
		//Add JTextField 'input' to the JPanel 'cmdLine'.
		cmdLine.add(input);
		//Add JButton 'left' to the JPanel 'cmdLine'.
		cmdLine.add(left);
		//Add JButton 'forward' to the JPanel 'cmdLine'.
		cmdLine.add(forward);
		//Add JButton 'back' to the JPanel 'cmdLine'.
		cmdLine.add(back);
		//Add JButton 'right' to the JPanel 'cmdLine'.
		cmdLine.add(right);
		//Add JButton 'save' to the JPanel 'cmdLine'.
		cmdLine.add(saveB);
		//Add JButton 'load' to the JPanel 'cmdLine'.
		cmdLine.add(loadB);
		//Add JButton 'clear' to the JPanel 'cmdLine'.
		cmdLine.add(clear);
		cmdLine.add(colourButton);
		//Add the JPanel 'cmdLine' to the 	JFrame, placed at the bottom of the window.
		this.getContentPane().add(cmdLine, BorderLayout.SOUTH);
		
		//Add JLabel 'coords' to the JPanel 'stats'.
		stats.add(coords);
		//Add JLabel 'saved' to the JPanel 'stats'.
		stats.add(saved);
		//Add JLabel 'bearing' to the JPanel 'stats'.
		stats.add(bearing);
		//Add JPanel 'stats' to the JFrame, placed at the bordr of the window.
		this.getContentPane().add(stats, BorderLayout.NORTH);
		
		//Add the GraphicsPanel Object 'gp' to the JFrame, placed in the centre of the window.
		this.getContentPane().add(gp, BorderLayout.CENTER);
	
		
        //Allows keyboard shortcut, alt + f activates the 'file' drop down.
        file.setMnemonic(KeyEvent.VK_F);
        //Allows keyboard shortcut, alt + h activates the 'help' drop down.
        help.setMnemonic(KeyEvent.VK_H);
        
        //'new' drop down option (alt + 1).
        newMenu.setMnemonic(KeyEvent.VK_1);
        //Set ToolTip message, activates when mouse hovers over 'newMenu'.
        newMenu.setToolTipText("Reset the graphics panel");
        //Action listener for when 'newMenu, is pressed.
        newMenu.addActionListener((ActionEvent event) -> 
        {
        	if(gp.getSaved() == false)
        	{
        		//Create an option window for the user to check if they want to make a new image.
            	int reply = JOptionPane.showConfirmDialog(null, "Would you like to save changes to the graphics panel before clearing?", "Clear Panel", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reply == JOptionPane.YES_OPTION) 
                {
                	gp.save();
                	JOptionPane.showMessageDialog(null, "Saved", "Clear Panel", JOptionPane.INFORMATION_MESSAGE);
                	gp.clear();
                	repaint();
                }
                else if (reply == JOptionPane.NO_OPTION) 
                {
                	gp.clear();
                	repaint();
                }
                else 
                {
                   JOptionPane.showMessageDialog(null, "Returning to application", "Clear Panel", JOptionPane.INFORMATION_MESSAGE);
                }
        	}
        	else
        	{
        		gp.clear();
        		repaint();
        	}
        	saved.setText("Saved: " + gp.getSaved());
        });
        
        //'load' menu drop down option (alt + 2).
        loadMenu.setMnemonic(KeyEvent.VK_2);
        //Set ToolTip message, activates when mouse hovers over 'loadMenu'.
        loadMenu.setToolTipText("Load existing graphics panel from file");
        //Action listener for when 'loadMenu' is pressed.
        loadMenu.addActionListener((ActionEvent e) -> 
        {
        	if(gp.getSaved() == false)
        	{
	        	//Create an option window for the user to check if they want to load an existing image.
	        	int reply = JOptionPane.showConfirmDialog(null, "Would you like to save changes to the graphics panel before loading a previous panel?", "Load Panel", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	            if (reply == JOptionPane.YES_OPTION) 
	            {
	            	gp.save();
	            	JOptionPane.showMessageDialog(null, "Saved", "Load Panel", JOptionPane.INFORMATION_MESSAGE);
	            	gp.load();
	            	repaint();
	            }
	            else if (reply == JOptionPane.NO_OPTION) 
	            {
	            	gp.load();
	            	repaint();
	            }
	            else 
	            {
	               JOptionPane.showMessageDialog(null, "Returning to application", "Load Panel", JOptionPane.INFORMATION_MESSAGE);
	            }
        	}
        	else
        	{
        		gp.load();
        		repaint();
        	}     
        	saved.setText("Saved: " + gp.getSaved());
        });
        
        //'save' menu drop down option (alt + 3).
        saveMenu.setMnemonic(KeyEvent.VK_3);
        //Set ToolTip message, activates when mouse hovers over 'saveMenu'.
        saveMenu.setToolTipText("Save current graphics panel to a file");
        //Action listener for when 'saveMenu' is pressed.
        saveMenu.addActionListener((ActionEvent event) -> 
        {
        	//Create an option window for the user to check if they want to save current image.
        	int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to save the current graphics panel?", "Save Panel", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) 
            {
              gp.save();
              repaint();
              JOptionPane.showMessageDialog(null, "Saving...", "Save Panel", JOptionPane.INFORMATION_MESSAGE);
            }
            else 
            {
               JOptionPane.showMessageDialog(null, "Returning to application", "Save Panel", JOptionPane.INFORMATION_MESSAGE);
            }
            saved.setText("Saved: " + gp.getSaved());
        });
        
        //'exit' menu drop down option (alt + 4).
        exitMenu.setMnemonic(KeyEvent.VK_4);
        //Set ToolTip message, activates when mouse hovers over 'exitMenu'.
        exitMenu.setToolTipText("Exit application");
        //Action listener for when 'exitMenu' is pressed.
        exitMenu.addActionListener((ActionEvent event) -> 
        {
        	if(gp.getSaved() == false)
        	{
	        	//Create an option window for the user to check if they want to save before exiting.
	        	int reply = JOptionPane.showConfirmDialog(null, "Would you like to save the grphics panel before exiting?", "Exit Panel", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	            if (reply == JOptionPane.YES_OPTION) 
	            {
	            	gp.save();
	            	JOptionPane.showMessageDialog(null, "Saved", "Exit Panel", JOptionPane.INFORMATION_MESSAGE);
	            	System.exit(0);
	            }
	            else if (reply == JOptionPane.NO_OPTION) 
	            {
	            	System.exit(0);
	            }
	            else 
	            {
	               JOptionPane.showMessageDialog(null, "Returning to application", "Exit Panel", JOptionPane.INFORMATION_MESSAGE);
	            }
	            saved.setText("Saved: " + gp.getSaved());
        	}
        	else
        	{
        		System.exit(0);
        	}
        });

        //'about' menu drop down option (alt + 5).
        aboutMenu.setMnemonic(KeyEvent.VK_5);
        //Set ToolTip message, activates when mouse hovers over 'aboutMenu'.
        aboutMenu.setToolTipText("Trigger pop up containing 'about' information");
        //Action listener for when 'aboutMenu' is pressed.
        aboutMenu.addActionListener((ActionEvent event) -> 
        {
            try 
            {
            	//Create new Strings.
            	String reader = new String();
            	String help = new String();
            	//FileReader reads text files in the default encoding.
                FileReader fileReader = new FileReader("resources\\about.txt");
                //Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                //While input(game data from file) is not empty.
				while((reader = bufferedReader.readLine()) != null) 
				{
					//File reader reads line by line, so I add each line into the same string removing new lines.
					//So when the about button is pressed, all information is presented in one JDialog box.
					help = help + reader + "\n";
				}
				//Present the contents of about.txt in one single JDialog box.
				JOptionPane.showMessageDialog(null, help, "Help", JOptionPane.INFORMATION_MESSAGE);
				//Close the file.
				bufferedReader.close();
			} 
            catch (IOException e) 
            {
            	//If an exception is caught, it will present a Dialog box with a message notifying the user, and a more in-depth message describing the problem.
            	JOptionPane.showMessageDialog(null, "An error has occured, file could not be found/read.\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
			}   
        });
        
        //Add the drop down menu to the 'file' tab.
        file.add(newMenu);
        file.add(loadMenu);
        file.add(saveMenu);
        file.add(exitMenu);
        //Add the drop down menu to the 'help' tab.
        help.add(aboutMenu);
        //Add tabs to the JMenuBar.
        menuBar.add(file);
        menuBar.add(help);
        setJMenuBar(menuBar);     
        
        //Create an listener to listen for button activity.
        ListenForButton lForButton = new ListenForButton();
        //All buttons share the same listener because only one button will be pressed at a time, so can share the same listener.
		button.addActionListener(lForButton);
		left.addActionListener(lForButton);
		forward.addActionListener(lForButton);
		back.addActionListener(lForButton);
		right.addActionListener(lForButton);
		saveB.addActionListener(lForButton);
		loadB.addActionListener(lForButton);
		clear.addActionListener(lForButton);
		colourButton.addActionListener(lForButton);
		
		//Set everything above to be visible.
		this.setVisible(true);
		//--------------------
	}
	
	//When the command line is given a String and enter is pressed then this class is called retrieve the user input and pass it to the 'cmds()' method.
	private class ListenForButton implements ActionListener
	{
		//When a button is pressed.
		public void actionPerformed(ActionEvent e)
		{
			//If the 'enter' button was pressed, then retrieve the string.
			if(e.getSource() == button)
			{
				command = input.getText();
				input.setText("");
				cmds(command);
			}
			//If 'left' was pressed, turn the turtle 90 left.
			else if(e.getSource() == left)
			{
				if(Turtle.getBearing() == 0)
				{
					Turtle.setBearing(270);
				}
				else
				{
					Turtle.setBearing(Turtle.getBearing() - 90);
				}
				bearing.setText("Bearing: " + Turtle.getBearing());
			}
			//If 'forward' was pressed, move the turtle 10px forward.
			else if(e.getSource() == forward)
			{
				gp.forward(10);
				repaint();
				saved.setText("Saved: " + gp.getSaved());
				coords.setText("X: " + Turtle.getxCoord() + "  " + "Y: " + Turtle.getyCoord() + "  ");
			}
			//If 'backward' was pressed, move the turtle 10px back.
			else if(e.getSource() == back)
			{
				gp.backward(10);
				repaint();
				saved.setText("Saved: " + gp.getSaved());
				coords.setText("X: " + Turtle.getxCoord() + "  " + "Y: " + Turtle.getyCoord() + "  ");
			}
			//If 'right' was pressed, turn the turtle 90 right.
			else if(e.getSource() == right)
			{
				if(Turtle.getBearing() == 270)
				{
					Turtle.setBearing(0);
				}
				else
				{
					Turtle.setBearing(Turtle.getBearing() + 90);
				}
				bearing.setText("Bearing: " + Turtle.getBearing());
			}
			//If 'save' was pressed, save the current image.
			else if(e.getSource() == saveB)
			{
				//Create an option JDialog box for the user to check if they want to save current image.
	        	int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to save the current graphics panel?", "Save Panel", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	            if (reply == JOptionPane.YES_OPTION) 
	            {
	              gp.save();
	              repaint();
	              JOptionPane.showMessageDialog(null, "Saving...", "Save Panel", JOptionPane.INFORMATION_MESSAGE);
	            }
	            else 
	            {
	               JOptionPane.showMessageDialog(null, "Returning to application", "Save Panel", JOptionPane.INFORMATION_MESSAGE);
	            }
	            saved.setText("Saved: " + gp.getSaved());
			}
			//If 'load' was pressed, load image.
			else if(e.getSource() == loadB)
			{
				if(gp.getSaved() == false)
	        	{
		        	//Create an option JDialog box for the user to check if they want to load an existing image.
		        	int reply = JOptionPane.showConfirmDialog(null, "Would you like to save changes to the graphics panel before loading a previous panel?", "Load Panel", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		            if (reply == JOptionPane.YES_OPTION) 
		            {
		            	gp.save();
		            	JOptionPane.showMessageDialog(null, "Saved", "Load Panel", JOptionPane.INFORMATION_MESSAGE);
		            	gp.load();
		            	repaint();
		            }
		            else if (reply == JOptionPane.NO_OPTION) 
		            {
		            	gp.load();
		            	repaint();
		            }
		            else 
		            {
		               JOptionPane.showMessageDialog(null, "Returning to application", "Load Panel", JOptionPane.INFORMATION_MESSAGE);
		            }
	        	}
	        	else
	        	{
	        		gp.load();
	        		repaint();
	        	}     
	        	saved.setText("Saved: " + gp.getSaved());
			}
			//If 'clear' was pressed, clear current image.
			else if(e.getSource() == clear)
			{
				if(gp.getSaved() == false)
	        	{
	        		//Create an option JDialog box for the user to check if they want to make a new image.
	            	int reply = JOptionPane.showConfirmDialog(null, "Would you like to save changes to the graphics panel before clearing?", "Clear Panel", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	                if (reply == JOptionPane.YES_OPTION) 
	                {
	                	gp.save();
	                	JOptionPane.showMessageDialog(null, "Saved", "Clear Panel", JOptionPane.INFORMATION_MESSAGE);
	                	gp.clear();
	                	repaint();
	                }
	                else if (reply == JOptionPane.NO_OPTION) 
	                {
	                	gp.clear();
	                	repaint();
	                }
	                else 
	                {
	                   JOptionPane.showMessageDialog(null, "Returning to application", "Clear Panel", JOptionPane.INFORMATION_MESSAGE);
	                }
	        	}
	        	else
	        	{
	        		gp.clear();
	        		repaint();
	        	}
	        	saved.setText("Saved: " + gp.getSaved());
			}
			//If 'Colour' is pressed then activate the JColorChooser in a dialog box.
			else if(e.getSource() == colourButton)
			{
				gp.setMoreColour(JColorChooser.showDialog(GUI.this, "Choose Background Color",Color.black));
			}
		}
		
		//Set the user string to lower case, then check to see how many spaces are in the string, if non then run the methods that only require a command, 
		//If there is a space then we can assume that it is a command and an int, and so run the commands that require the command and an int.
		public void cmds(String a)
		{ 
			a.toLowerCase();
			if(a.contains(" "))
			{
				try
				{
					String[] cmdArr = a.split(" ");
					int amount = Integer.parseInt(cmdArr[1]);
				
					//Forward.
					if(cmdArr[0].equals("forward"))
					{
						gp.forward(amount);
						repaint();
						saved.setText("Saved: " + gp.getSaved());
						coords.setText("X: " + Turtle.getxCoord() + "  " + "Y: " + Turtle.getyCoord() + "  ");
					}
					//Backward.
					else if(cmdArr[0].equals("backward"))
					{
						gp.backward(amount);
						repaint();
						saved.setText("Saved: " + gp.getSaved());
						coords.setText("X: " + Turtle.getxCoord() + "  " + "Y: " + Turtle.getyCoord() + "  ");
					}
					//Circle.
					else if(cmdArr[0].equals("circle"))
					{
						gp.circle(amount);
						repaint();
						saved.setText("Saved: " + gp.getSaved());
						coords.setText("X: " + Turtle.getxCoord() + "  " + "Y: " + Turtle.getyCoord() + "  ");
					}
					//Square.
					else if(cmdArr[0].equals("square"))
					{
						gp.square(amount);
						repaint();
						saved.setText("Saved: " + gp.getSaved());
						coords.setText("X: " + Turtle.getxCoord() + "  " + "Y: " + Turtle.getyCoord() + "  ");
					}
					//Triangle.
					else if(cmdArr[0].equals("triangle"))
					{
						gp.triangle(amount);
						repaint();
						saved.setText("Saved: " + gp.getSaved());
						coords.setText("X: " + Turtle.getxCoord() + "  " + "Y: " + Turtle.getyCoord() + "  ");
					}
					else
					{
						invalidCommand();
					}
				}
				catch(NumberFormatException e)
				{
					invalidCommand();
				}
			}
			
			//Single commands,
			else if(a.contains(""))
			{
				//Penup.
				if(a.equals("penup"))
				{
					Turtle.setPenUp(true);
				}
				//Pendown.
				else if(a.equals("pendown"))
				{
					Turtle.setPenUp(false);
				}
				//Turnleft.
				else if(a.equals("turnleft"))
				{
					if(Turtle.getBearing() == 0)
					{
						Turtle.setBearing(270);
					}
					else
					{
						Turtle.setBearing(Turtle.getBearing() - 90);
					}
					bearing.setText("Bearing: " + Turtle.getBearing());
				}
				//Turnright.
				else if(a.equals("turnright"))
				{
					if(Turtle.getBearing() == 270)
					{
						Turtle.setBearing(0);
					}
					else
					{
						Turtle.setBearing(Turtle.getBearing() + 90);
					}
					bearing.setText("Bearing: " + Turtle.getBearing());
				}
				//Black.
				else if(a.equals("black"))
				{
					int ColourCount = 0;
					gp.setColour(ColourCount);
					repaint();
				}
				//Green.
				else if(a.equals("green"))
				{
					int ColourCount = 1;
					gp.setColour(ColourCount);
					repaint();
				}
				//Red.
				else if(a.equals("red"))
				{
					int ColourCount = 2;
					gp.setColour(ColourCount);
					repaint();
				}
				//Reset.
				else if(a.equals("reset"))
				{
					gp.clear();
					repaint();
					saved.setText("Saved: " + gp.getSaved());
				}
				//Save.
				else if(a.equals("save"))
				{
					gp.save();
					repaint();
					saved.setText("Saved: " + gp.getSaved());
				}
				//Load.
				else if(a.equals("load"))
				{
					gp.load();
					repaint();
					saved.setText("Saved: " + gp.getSaved());
				}
				else
				{
					invalidCommand();
				}
			}
		}
		
		//Method to display a JDialog box in the event that a valid command is not entered.
		public void invalidCommand()
		{
			JOptionPane.showMessageDialog(null, "Please enter a valid command.\nFor more information please reference the 'about' guide in the help menu.", 
					"Invalid Command", JOptionPane.ERROR_MESSAGE);
		}
	}
}
