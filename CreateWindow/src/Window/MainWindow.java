package Window;

import Objects.Circle;
import Handlers.UpdateCircle;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class MainWindow 
{
	private int windowWidth = 500;
	private int windowHeight = 500;
	
	JFrame window;
	
	public MainWindow()
	{
		window = new JFrame("2D Particle Simulation");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(windowWidth, windowHeight);
		
		window.setVisible(true);
	}
	
	public int getWindowWidth()
	{ return this.windowWidth; }
	
	public int getWindowHeight()
	{ return this.windowHeight; }
	
	public JFrame getWindow()
	{ return this.window; }
	
	public double [] randVel(int vel)
	{
		Random r = new Random();
		double velrng =r.nextInt(vel - 1) + 1;
		double randvelx = r.nextInt(1000)*velrng/1000.0;
		
		double randvely = Math.sqrt(Math.pow(velrng,2) - Math.pow(randvelx, 2));
		
		double [] randvel = {randvelx, randvely};
		
		return randvel;
	}
	
	public double [] randPos()
	{
		Random r = new Random();
		double randposx = r.nextInt(windowWidth);
		
		double randposy = r.nextInt(windowHeight);
		
		double [] randpos = {randposx, randposy};
		
		return randpos;
	}
	
	public ArrayList<Circle> drawCircles(int number, int radius, int velocity)
	{
		ArrayList<Circle> c = new ArrayList<>();
		for (int i = 0; i < number; i++)
		{
			double [] vel = randVel(velocity);
			double [] pos = randPos();
			c.add(new Circle(radius, pos[0], pos[1], vel[0], vel[1], velocity));
		}
		return c;
	}
	
	public static void main(String [] args)
	{
		MainWindow m = new MainWindow();
		ArrayList<Circle> c = m.drawCircles(100, 15, 7);
		UpdateCircle uc = new UpdateCircle(m.getWindow(), m.getWindowWidth(), m.getWindowHeight(), c);
		uc.update();
	}
}
