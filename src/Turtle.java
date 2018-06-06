//This class holds information on the turtles bearing and position, as well as if the pen is up or down.
public class Turtle 
{
	//Turtle Co-ords.
	private static int xCoord = 400;
	private static int yCoord = 400;
	//Pen status.
	private static Boolean penUp = false;
	//Turtle bearing(north = 0, east = 90, south = 180, west = 270).
	private static int bearing = 180;
	
	//Methods to get x, y turtle co-ords.
	public static int getxCoord()
	{
		return xCoord;
	}
	public static int getyCoord()
	{
		return yCoord;
	}
	
	//Methods to set turtle x,y co-ords.
	public static void setxCoord(int a)
	{
		xCoord = a;
	}
	public static void setyCoord(int a)
	{
		yCoord = a;
	}
	
	//Methods to set pen to up or down.
	public static void setPenUp(Boolean a)
	{
		penUp = a;
	}
	public static Boolean getPenUp()
	{
		return penUp;
	}
	
	//Methods to get and set bearing.
	public static void setBearing(int a)
	{
		bearing = a;
	}
	public static int getBearing()
	{
		return bearing;
	}
}
