import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GraphicsPanel extends JPanel 
{
	//The default BG colour of the image.
	private final static Color BACKGROUND_COL = Color.WHITE;
	//The underlying image used for drawing. This is required so any previous drawing activity is persistent on the panel.
	private static BufferedImage image;
	//The Colour of the pen, default is black.
	private static Color colour = Color.BLACK;
	//Create a file chooser Object.
    JFileChooser fc = new JFileChooser();
    //Create 2 JTextFields for the save method.
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    //Boolean to decide if any changes to an image has been saved.
    private static Boolean saved = false;
    
    //Get method to check current save status
    public Boolean getSaved()
    {
    	return saved;
    }
    
    
    public void setMoreColour(Color a)
    {
    	colour = a;
    }
	//Get the colour for the pen.
	public static Color getColour()
	{
		return colour;
	}
	//Set the colour for the pen
	public void setColour(int a)
	{
		if(a == 0)
		{
			colour = Color.BLACK;
		}
		else if(a == 1)
		{
			colour = Color.GREEN;
		}
		else if(a == 2)
		{
			colour = Color.RED;
		}
	}
	
	//Clears the image contents.
	public void clear() 
	{
		saved = true;
		Graphics g = image.getGraphics();
		g.setColor(BACKGROUND_COL);
		g.fillRect(0, 0, image.getWidth(),  image.getHeight());
	}
	
	//Sets the colour and draws a line.
	public static void drawLine(Color colour, int x1, int y1, int x2, int y2) 
	{
		saved = false;
		Graphics g = image.getGraphics();
		g.setColor(getColour());
		g.drawLine(x1, y1, x2, y2);
	}
	
	//Allows the turtle to move forward, takes into account the turtle bearing.
	public void forward(int amount)
	{
		if(Turtle.getBearing() == 0)
		{
			if(Turtle.getPenUp() == false)
			{
				drawLine(getColour(), Turtle.getxCoord(), Turtle.getyCoord(), Turtle.getxCoord(), Turtle.getyCoord() - amount);
			}
			Turtle.setyCoord(Turtle.getyCoord() - amount);
		}
		else if(Turtle.getBearing() == 90)
		{
			if(Turtle.getPenUp() == false)
			{
				drawLine(getColour(), Turtle.getxCoord(), Turtle.getyCoord(), Turtle.getxCoord() + amount, Turtle.getyCoord());
			}
			Turtle.setxCoord(Turtle.getxCoord() + amount);
		}
		else if(Turtle.getBearing() == 180)
		{
			if(Turtle.getPenUp() == false)
			{
				drawLine(getColour(), Turtle.getxCoord(), Turtle.getyCoord(), Turtle.getxCoord(), Turtle.getyCoord() + amount);
			}
			Turtle.setyCoord(Turtle.getyCoord() + amount);
		}
		else if(Turtle.getBearing() == 270)
		{
			if(Turtle.getPenUp() == false)
			{
				drawLine(getColour(), Turtle.getxCoord(), Turtle.getyCoord(), Turtle.getxCoord() - amount, Turtle.getyCoord());
			}
			Turtle.setxCoord(Turtle.getxCoord() - amount);
		}
		saved = false;
	}
	
	//Allow the turtle to move back, takes into account turtle bearing.
	public void backward(int amount) 
	{
		if(Turtle.getBearing() == 0)
		{
			if(Turtle.getPenUp() == false)
			{
				drawLine(getColour(), Turtle.getxCoord(), Turtle.getyCoord(), Turtle.getxCoord(), Turtle.getyCoord() + amount);
			}
			Turtle.setyCoord(Turtle.getyCoord() + amount);
		}
		else if(Turtle.getBearing() == 90)
		{
			if(Turtle.getPenUp() == false)
			{
				drawLine(getColour(), Turtle.getxCoord(), Turtle.getyCoord(), Turtle.getxCoord() - amount, Turtle.getyCoord());
			}
			Turtle.setxCoord(Turtle.getxCoord() - amount);
		}
		else if(Turtle.getBearing() == 180)
		{
			if(Turtle.getPenUp() == false)
			{
				drawLine(getColour(), Turtle.getxCoord(), Turtle.getyCoord(), Turtle.getxCoord(), Turtle.getyCoord() - amount);
			}
			Turtle.setyCoord(Turtle.getyCoord() - amount);
		}
		else if(Turtle.getBearing() == 270)
		{
			if(Turtle.getPenUp() == false)
			{
				drawLine(getColour(), Turtle.getxCoord(), Turtle.getyCoord(), Turtle.getxCoord() + amount, Turtle.getyCoord());
			}
			Turtle.setxCoord(Turtle.getxCoord() + amount);
		}
		saved = false;
	}
	
	//Draws a circle.
	public void circle(int amount)
	{
		saved = false;
		Graphics g = image.getGraphics();
		g.setColor(getColour());
		g.drawOval(Turtle.getxCoord(), Turtle.getyCoord(), amount, amount);
	}
	//Draws a square.
	public void square(int amount)
	{
		saved = false;
		Graphics g = image.getGraphics();
		g.setColor(getColour());
		g.drawRect(Turtle.getxCoord(), Turtle.getyCoord(), amount, amount);
	}
	//Draws a triangle.
	public void triangle(int amount)
	{
		saved = false;
		int[] x = new int[] {Turtle.getxCoord() + (amount/2), Turtle.getxCoord(), Turtle.getxCoord() + amount};
		int[] y = new int[] {Turtle.getyCoord(), Turtle.getyCoord() + amount, Turtle.getyCoord() + amount};
		Graphics g = image.getGraphics();
		g.setColor(getColour());
		g.drawPolygon(x, y, 3);
	}
	//Saves current image.
	public void save()
	{
		int rVal = fc.showSaveDialog(GraphicsPanel.this);
		if (rVal == JFileChooser.APPROVE_OPTION)  
		{
			File file = fc.getSelectedFile();
			try
			{
				ImageIO.write(image, "png", file);
			}
			catch(IOException e)
			{	
				JOptionPane.showMessageDialog(null, "An error has occured, file could not be written.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		saved = true;
	}
	//Loads a image.
	public void load()
	{
        int returnVal = fc.showOpenDialog(GraphicsPanel.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            JOptionPane.showMessageDialog(null, "Loading...", "Load", JOptionPane.INFORMATION_MESSAGE);
            try 
            {
				image = ImageIO.read(new File(file.getName()));
			} catch (IOException e) 
            {
				JOptionPane.showMessageDialog(null, "An error has occured, file could not be found/read.\n " + e, "Error", JOptionPane.ERROR_MESSAGE);
			}
        }
        saved = true;
	}

	//Paints the image on the panel.
	@Override
	public void paint(Graphics g) 
	{
		// render the image on the panel.
		g.drawImage(image, 0, 0, null);
	}

	//Constructor.
	GraphicsPanel() 
	{
		dir.setEditable(false);
		filename.setEditable(false);
		setPreferredSize(new Dimension(500, 300));
		image = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_RGB);
		// Set max size of the panel, so that is matches the max size of the image.
		setMaximumSize(new Dimension(image.getWidth(), image.getHeight()));
		clear();
	}
}

