package Handlers;

import java.awt.*;

public class ChooseColour 
{
	public static Color chooseColour(double velx, double vely, double maxv)
	{
		double vel = Math.sqrt(Math.pow(velx, 2) + Math.pow(vely, 2));
		if (vel < maxv/4)
		{ return Color.BLUE; }
		else if (vel < maxv/2)
		{ return Color.YELLOW; }
		else if (vel < maxv*3/4)
		{ return new Color(225,43,0); }
		else
		{ return Color.RED; }
	}
}