package Objects;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

import Handlers.ChooseColour;

public class Circle extends JComponent
{
	private int radius;
	private double posx;
	private double posy;
	private double velx;
	private double vely;
	private double maxv;
	private Color colour;
	
	Graphics2D g2;
	Ellipse2D.Double e;
	
	public Circle()
	{}
	
	public Circle(int radius, double posx, double posy, double velx, double vely, double maxv)
	{
		this.radius = radius;
		this.posx = posx;
		this.posy = posy;
		this.velx = velx;
		this.vely = vely;
		this.maxv = maxv;
		colour = ChooseColour.chooseColour(velx, vely, maxv);
	}
	
	public boolean equals(Circle c)
	{
		if ((this.posx == c.posx)||(this.posy == c.posy)||
				(this.velx == c.velx)||(this.vely == c.vely))
		{
			return true;
		}
		return false;
	}
	
	public double getPosx()
	{ return this.posx; }
	public void setPosx(double posx)
	{ this.posx = posx; }
	
	public double getPosy()
	{ return this.posy; }
	public void setPosy(double posy)
	{ this.posy = posy; }
	
	public double getVelx()
	{ return this.velx; }
	public void setVelx(double velx)
	{ 
		colour = ChooseColour.chooseColour(velx, this.vely, maxv);
		this.velx = velx;
	}
	
	public double getVely()
	{ return this.vely; }
	public void setVely(double vely)
	{ 
		colour = ChooseColour.chooseColour(this.velx, vely, maxv);
		this.vely = vely;
	}
	
	public int getRadius()
	{ return this.radius; }
	public void setRadius(int radius)
	{ this.radius = radius; }
	
	public void paintComponent(Graphics graphics)
	{
		g2 = (Graphics2D) graphics;
		e = new Ellipse2D.Double(posx - radius, posy - radius, radius * 2, radius * 2);
		
		g2.setPaint(colour);
		g2.fill(e);
		g2.draw(e);
	}
}
