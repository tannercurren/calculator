import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TurtlePanel extends JPanel {
	// Turtle position
	private double x;
	private double y;
	private double angle;
	
	public void forward(double length) {
		double ex, ey;
		
		ex = x + length * Math.cos(Math.toRadians(angle));
		ey = y + length * Math.sin(Math.toRadians(angle));
		
		// Draw the line
		Graphics2D g = (Graphics2D)getGraphics();
		g.drawLine((int)x, (int)y, (int)ex, (int)ey);
		
		// Update the turtle position
		x = ex;
		y = ey;
	}
	
	public void backward(double length) {
		double ex, ey;
		
		ex = x - length * Math.cos(Math.toRadians(angle));
		ey = y - length * Math.sin(Math.toRadians(angle));
		
		// Draw the line
		Graphics2D g = (Graphics2D)getGraphics();
		g.drawLine((int)x, (int)y, (int)ex, (int)ey);
		
		// Update the turtle position
		x = ex;
		y = ey;
	}
	
	public void left(double a) {
		angle += a;
		correctAngle(angle);
	}
	
	public void right(double a) {
		angle -= a;
		correctAngle(angle);
	}
	
	private void correctAngle(double a) {
		while(angle > 360) {
			angle %= 360;
		}
		
		while(angle < 0) {
			angle += 360;
		}
	}
}
