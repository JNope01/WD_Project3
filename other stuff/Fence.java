package FinalDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Fence extends LandscapeObject {
	private int postHeight = 5;
	private int postWidth = 1;
	private double railHeight = .5;
	private int railLength = 7;
	private Color fenceColor;
	private int stroke = 1;
	private char condition;
	private Graphics2D rotateGraphic = g2;
	private AffineTransform old = rotateGraphic.getTransform();

	public Fence(Graphics2D g2, int x, int y, double scale, String fC, char c) {
		super(g2, x, y, scale);
		this.fenceColor = new Color(Integer.parseInt(fC, 16));
		condition = c;
	}

	public void draw() {
		applyScale();
		g2.setStroke(new BasicStroke(stroke));
		drawFence();
	}

	public void applyScale() {
		postHeight = (int) (getScale() * postHeight);
		postWidth = (int) (getScale() * postWidth);
		railHeight = (int)(getScale()* railHeight);
		railLength = (int)(getScale()* railLength);
		if (getScale() <= 4) {
			stroke = (int) (getScale() * stroke);
		} else {
			stroke = 5;
		}
	}
	
	private void drawFence() {
		drawPost();
		drawRails();
	}
	
	private void drawPost() {
		g2.setColor(Color.BLACK);
		Rectangle2D.Double post = new Rectangle2D.Double(currentX, currentY, postWidth, postHeight);
		g2.draw(post);
		g2.drawRoundRect(currentX, (int) (currentY-(getScale()*1)), postWidth, postHeight, 40, 40);
		g2.setColor(fenceColor);
		g2.fill(post);
		g2.fillRoundRect(currentX, (int) (currentY-(getScale()*1)), postWidth, postHeight, 40, 40);
	}
	
	private void drawRails() {
		Rectangle2D.Double post = new Rectangle2D.Double(currentX, currentY, postWidth, postHeight);
		double postCX = post.getCenterX();
		double postCY = post.getCenterY();
		if (condition == 'l') {
			int rail1X = (int)(postCX);
			int rail1Y = (int)(postCY - (getScale()*2.5));
			Rectangle2D.Double rail1 = new Rectangle2D.Double(rail1X, rail1Y, railLength, railHeight);
			int rail2X = rail1X;
			int rail2Y = (int)(rail1Y + (getScale()*2));
			Rectangle2D.Double rail2 = new Rectangle2D.Double(rail2X, rail2Y, railLength, railHeight);
			g2.setColor(Color.BLACK);
			g2.draw(rail1);
			g2.draw(rail2);
			g2.setColor(fenceColor);
			g2.fill(rail1);
			g2.fill(rail2);
		} else if (condition == 'r') {
			int rail1X = (int)(postCX - (getScale()*7));
			int rail1Y = (int)(postCY - (getScale()*2.5));
			Rectangle2D.Double rail1 = new Rectangle2D.Double(rail1X, rail1Y, railLength, railHeight);
			int rail2X = rail1X;
			int rail2Y = (int)(rail1Y + (getScale()*2));
			Rectangle2D.Double rail2 = new Rectangle2D.Double(rail2X, rail2Y, railLength, railHeight);
			g2.setColor(Color.BLACK);
			g2.draw(rail1);
			g2.draw(rail2);
			g2.setColor(fenceColor);
			g2.fill(rail1);
			g2.fill(rail2);
		} else if (condition == 'b') {
			int rail1X = (int)(postCX-(getScale()*2.5));
			int rail1Y = (int)(postCY - (getScale()*2.5));
			Rectangle2D.Double rail1 = new Rectangle2D.Double(rail1X, rail1Y, railLength, railHeight);
			int rail2X = rail1X;
			int rail2Y = (int)(rail1Y + (getScale()*4.5));
			Rectangle2D.Double rail2 = new Rectangle2D.Double(rail2X, rail2Y, railLength, railHeight);
			g2.rotate(-35,postCX,postCY);
			g2.setColor(Color.BLACK);
			g2.draw(rail1);
			g2.setColor(fenceColor);
			g2.fill(rail1);
			removeRotation();
			g2.setColor(Color.BLACK);
			g2.draw(rail2);
			g2.setColor(fenceColor);
			g2.fill(rail2);
		}
	}
	
	private void removeRotation() {
		rotateGraphic.setTransform(old);
	}
}
