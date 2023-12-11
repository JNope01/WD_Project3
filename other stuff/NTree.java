package FinalDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Tree Class. Extends LandscapObject Class. Note this class inherits 3
 * protected attributes from LandscapeObject for convenience in coding methods.
 * protected int currentX protected int currentY protected Graphics2D
 * 
 * When creating a Tree object in the DrawPanel.paintComponent(Graphics) method,
 * usually the following syntax is used.
 * 
 * Tree t1 = new Tree(args); t1.draw();
 * 
 * @author Jeffrey L Light
 * @version 1.0
 * @since 2019-11-26
 */
public class NTree extends LandscapeObject {
	// Attributes
	private Color trunkColor;
	private Color leafColor;
	private int trunkWidth = 4;
	private int trunkHeight = 20;
	private int branchWidth = 7;
	private int branchLength = 1;
	private int leafSize = 10;
	private int stroke;
	private Graphics2D rotateGraphic = g2;
	private AffineTransform old = rotateGraphic.getTransform();

	public NTree(Graphics2D g2, int x, int y, double scale, String s_trunkColor, String s_leafColor) {
		super(g2, x, y, scale);
		this.trunkColor = new Color(Integer.parseInt(s_trunkColor, 16));
		this.leafColor = new Color(Integer.parseInt(s_leafColor, 16));
		stroke = 1;
	}// end of Constructor Tree

	// Methods

	public void draw() {
		applyScale();
		g2.setStroke(new BasicStroke(stroke));
		drawCanopy1();
		drawTrunk();
		drawCanopy2();
	}// end of method draw()

	public void applyScale() {
		trunkWidth = (int) (getScale() * trunkWidth);
		trunkHeight = (int) (getScale() * trunkHeight);
		branchWidth = (int) (getScale() * branchWidth);
		branchLength = (int) (getScale() * branchLength);
		leafSize = (int) (getScale() * leafSize);
		if (getScale() <= 4) {
			stroke = (int) (getScale() * stroke);
		} else {
			stroke = 5;
		}
	}// end of method applyScale()

	private void removeRotation() {
		rotateGraphic.setTransform(old);
	}

	private double ranNumGen(int min, int max) {
		double num = Math.random() * (max - min + 1) + min;
		return num;
	}

	private void drawTrunk() {
		Rectangle2D.Double trunk = new Rectangle2D.Double(currentX, currentY, trunkWidth, trunkHeight);
		Rectangle2D.Double trunkTop = new Rectangle2D.Double(currentX, currentY, (getScale()*1),(getScale()*1));
		double trunkTopCenterX = trunkTop.getCenterX();
		double trunkTopCenterY = trunkTop.getCenterY();
		double branchBase1XCord = (trunkTopCenterX-(getScale()*.5));
		double branchBase1YCord = (trunkTopCenterY+(getScale()*9));
		Rectangle2D.Double branchBase1 = new Rectangle2D.Double(branchBase1XCord, branchBase1YCord, branchWidth, branchLength);
		double branchBase2XCord = (trunkTopCenterX+(getScale()*2));
		double branchBase2YCord = (trunkTopCenterY+(getScale()*9));
		Rectangle2D.Double branchBase2 = new Rectangle2D.Double(branchBase2XCord, branchBase2YCord, branchWidth, branchLength);

		
		// Draw
		g2.setColor(Color.BLACK);
		rotateGraphic.rotate(3.5,branchBase1XCord,branchBase1YCord);
		g2.draw(branchBase1);
		removeRotation();
		rotateGraphic.rotate(5.5,branchBase2XCord,branchBase2YCord);
		g2.draw(branchBase2);
		removeRotation();
		g2.draw(trunk);
		

		// Fill
		g2.setColor(trunkColor);
		rotateGraphic.rotate(3.5,branchBase1XCord,branchBase1YCord);
		g2.fill(branchBase1);
		removeRotation();
		rotateGraphic.rotate(5.5,branchBase2XCord,branchBase2YCord);
		g2.fill(branchBase2);
		removeRotation();
		g2.fill(trunk);

	}// end of method drawTrunk()


	private void drawCanopy1() {
		Rectangle2D.Double trunk = new Rectangle2D.Double(currentX, currentY, trunkWidth, trunkHeight);
		double trunkCenterX = trunk.getCenterX();
		double trunkCenterY = trunk.getCenterY();
		double leafXCord = (trunkCenterX - (getScale() * 5));
		double leafYCord = (trunkCenterY - (getScale() * 20));
		Ellipse2D.Double leaves = new Ellipse2D.Double(leafXCord, leafYCord, leafSize, leafSize);
		double leafCenterX = leaves.getCenterX();
		double leafCenterY = leaves.getCenterY();
		g2.setColor(Color.BLACK);
		g2.draw(leaves);
		g2.setColor(leafColor);
		g2.fill(leaves);
		Ellipse2D.Double leaves2 = new Ellipse2D.Double(leafCenterX + (getScale() * .75),
				leafCenterY + (getScale() * .50), leafSize, leafSize);
		int leafNum = (int) ranNumGen(10, 25);
		int indexSkew = (int) ranNumGen(1, 20);
		
		for (int i = 0; i < leafNum; i++) {
			rotateGraphic.rotate(i + indexSkew, leafCenterX, leafCenterY);
			g2.setColor(Color.BLACK);
			g2.draw(leaves2);
		}
		removeRotation();
		
		for (int i = 0; i < leafNum; i++) {
			rotateGraphic.rotate(i + indexSkew, leafCenterX, leafCenterY);
			g2.setColor(leafColor);
			g2.fill(leaves2);
		}
		removeRotation();
	}

	private void drawCanopy2() {
		Rectangle2D.Double trunk = new Rectangle2D.Double(currentX, currentY, trunkWidth, trunkHeight);
		double trunkCenterX = trunk.getCenterX();
		double trunkCenterY = trunk.getCenterY();
		double leaf1XCord = (trunkCenterX - (getScale() * 8));
		double leaf1YCord = (trunkCenterY - (getScale() * 15));
		Ellipse2D.Double leaves1 = new Ellipse2D.Double(leaf1XCord, leaf1YCord, leafSize, leafSize);
		double leaf2XCord = (trunkCenterX - (getScale() * 3));
		double leaf2YCord = (trunkCenterY - (getScale() * 15));
		Ellipse2D.Double leaves2 = new Ellipse2D.Double(leaf2XCord, leaf2YCord, leafSize, leafSize);
		double leaf3XCord = (trunkCenterX - (getScale() * 5));
		double leaf3YCord = (trunkCenterY - (getScale() * 18));
		Ellipse2D.Double leaves3 = new Ellipse2D.Double(leaf3XCord, leaf3YCord, leafSize, leafSize);
		g2.setColor(Color.BLACK);
		g2.draw(leaves1);
		g2.draw(leaves2);
		g2.draw(leaves3);
		g2.setColor(leafColor);
		g2.fill(leaves1);
		g2.fill(leaves2);
		g2.fill(leaves3);
	}

}// end of class Tree
