package Handlers;

import java.util.ArrayList;

import Objects.Circle;
import javax.swing.*;

public class UpdateCircle {
	private JFrame window;
	
	private final double xshift = 15;
	private final double yshift = 40;
	private double windowWidth;
	private double windowHeight;
	
	private ArrayList<Double> tempvx;
	private ArrayList<Double> tempvy;
	
	private ArrayList<Circle> circles;
	
	public UpdateCircle(JFrame window, int windowWidth, int windowHeight, ArrayList<Circle> circles)
	{
		this.window = window;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.circles = circles;
	}
	
	public void update()
	{
		for (int i = 0; i < circles.size(); i++){
			window.add(circles.get(i));
			window.validate();
		}
		
		while(true){
			try {
				Thread.sleep(10);
				tempvx = new ArrayList<>();
				tempvy = new ArrayList<>();
				double energy = 0;
				for (Circle t: circles) {
					tempvx.add(t.getVelx());
					tempvy.add(t.getVely());
					energy += 0.5*(Math.pow(t.getVelx(),2) + Math.pow(t.getVely(), 2));
				}
				System.out.println(energy);
				for (Circle c: circles) {
					checkCollision(c);
					
					c.setPosx(c.getPosx() + c.getVelx());
					c.setPosy(c.getPosy() + c.getVely());
					
					c.setVelx(checkVelx(c));
					c.setVely(checkVely(c));
				}
				window.validate();
				window.repaint();
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public double checkVelx(Circle c)
	{
		if (c.getPosx() + c.getRadius() + xshift > windowWidth) {
			c.setPosx(c.getPosx() - 5);
			return -c.getVelx();
		}
		if (c.getPosx() - c.getRadius() < 0) {
			c.setPosx(c.getRadius() + 5);
			return -c.getVelx();
		}
		return c.getVelx();
	}
	
	public double checkVely(Circle c)
	{
		if (c.getPosy() + c.getRadius() + yshift > windowHeight) {
			c.setPosy(c.getPosy() - 5);
			return -c.getVely();
		}
		if (c.getPosy() - c.getRadius() < 0) {
			c.setPosy(c.getRadius() + 5);
			return -c.getVely();
		}
		return c.getVely();
	}
	public void checkCollision(Circle c)
	{
		for (int i = 0; i < circles.size(); i++)
		{
			if (!((circles.get(i).getPosx() == c.getPosx())&&
					(circles.get(i).getPosx() == c.getPosx()))) {
				double dx = c.getPosx() - circles.get(i).getPosx();
				double dy = c.getPosy() - circles.get(i).getPosy();
				double d = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
				int radlen = c.getRadius()/2 + circles.get(i).getRadius()/2;
				
				double dnx = dx/d;
				double dny = dy/d;
				
				double tv = Math.sqrt(Math.pow(tempvx.get(i), 2) + Math.pow(tempvy.get(i), 2));
				double cv = Math.sqrt(Math.pow(c.getVelx(), 2) + Math.pow(c.getVely(), 2));
				
				if (d < radlen) {
					double cvx = tv * dnx;
					double cvy = tv * dny;
					double tvx = - cv * dnx;
					double tvy = - cv * dny;
					c.setVelx(cvx);
					c.setVely(cvy);
					circles.get(i).setVelx(tvx);
					circles.get(i).setVely(tvy);
					
					c.setPosx(c.getPosx() + 3*dnx);
					c.setPosy(c.getPosy() + 3*dny);
				}
			}
		}
	}
}
