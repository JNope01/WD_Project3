package FinalDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

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
public class Pine extends LandscapeObject {
	// Attributes
	private int levels; // The number of levels (branch triangles) the tree will have.
	private Color trunkColor; // The color of the trunk. Set in the constructor.
	private Color branchColor; // The color of the branches. Set in the constructor.
	private int trunkWidth = 10; // The trunk width in pixels. Default of 10 for a scale of 1. Modified by
									// applyScale method().
	private int trunkHeight = 15; // The trunk height in pixels. Default of 15 for a scale of 1. Modified by
									// applyScale method().
	private int branchWidth = 30; // The branch width in pixels. Default of 30 for a scale of 1. Modified by
									// applyScale method().
	private int branchHeight = 30; // The branch height in pixels. Default of 30 for a scale of 1. Modified by
									// applyScale method().
	private double branchOverlap = .45; // Indicates how much of each triangle is overlapped by the one above it.
	private int stroke=1; // Stroke of 1 pixel use to outline each shape of the tree.

	/**
	 * Primary Constructor. Sets all class attributes.
	 * 
	 * @param g2            The Graphics2D interface.
	 * @param x             int. The startX coordinate. currentX is also set to x
	 *                      upon object creation.
	 * @param y             int. The startY coordinate. currentX is also set to y
	 *                      upon object creation.
	 * @param levels        int indicating how many levels (branch triangles) the
	 *                      tree will have.
	 * @param scale         double. Scale multiplier for the object.
	 * @param s_trunkColor  A String representing the Hex code for the color used
	 *                      for the trunk of the tree.
	 * @param s_branchColor A String representing the Hex code for the color used
	 *                      for the branches of the tree.
	 * 
	 * @return void
	 */
	public Pine(Graphics2D g2, int x, int y, double scale, int levels, String s_trunkColor, String s_branchColor) {
		super(g2, x, y, scale);
		this.levels = levels;
		this.trunkColor = new Color(Integer.parseInt(s_trunkColor, 16));
		this.branchColor = new Color(Integer.parseInt(s_branchColor, 16));
		
	}// end of Constructor Tree

	// Methods
	/**
	 * draw() Calls applyScale to apply the scale multiplier to all shape dimensions
	 * for the object. Calls drawTrunk to draw the trunk of the tree. Calls
	 * drawBranches to draw the branches of the tree.
	 * 
	 * @return void.
	 */
	public void draw() {
		applyScale();
		g2.setStroke(new BasicStroke(stroke));
		drawTrunk();
		drawBranches();
	}// end of method draw()

	/**
	 * applyScale Applies the scale multiplier to all shape dimensions for the
	 * object. Called by draw()
	 * 
	 * @return void.
	 */
	public void applyScale() {
		trunkWidth = (int)(getScale() * trunkWidth);
		trunkHeight = (int)(getScale() * trunkHeight);
		branchWidth = (int)(getScale() * branchWidth);
		branchHeight = (int)(getScale() * branchHeight);
		if (getScale() <= 4) {
			stroke = (int) (getScale() * stroke);
		} else {
			stroke = 5;
		}
	}// end of method applyScale()

	/**
	 * drawTrunk() Draws a rectangle for the trunk of the tree. Called by draw()
	 * 
	 * @return void.
	 */
	private void drawTrunk() {
		// Fill
		g2.setColor(trunkColor);
		g2.fillRect(currentX, currentY, trunkWidth, trunkHeight);

		// Stroke
		g2.setColor(Color.BLACK);
		g2.drawRect(currentX, currentY, trunkWidth, trunkHeight);
	}// end of method drawTrunk()

	/**
	 * drawBranches() Draws 'level' number of triangles above the trunk overlapped
	 * by 'branchOverlap'. Called by draw()
	 * 
	 * @return void.
	 */
	private void drawBranches() {
		currentX = (int) (currentX + (trunkWidth * .5) - (branchWidth * .5));
		
		for (int i = 0; i < levels; i++) {
			int[] xCords = getXCords();
			int[] yCords = getYCords();
			
			// Fill
			g2.setColor(branchColor);
			g2.fillPolygon(xCords, yCords, 3);
			
			// Stroke
			g2.setColor(Color.BLACK);
			g2.drawPolyline(xCords, yCords, 3);
			
			// Move up
			currentY = (int)(currentY - (branchHeight * branchOverlap));
		}
	}// end of method drawBranches()

	private int[] getXCords() {
		int[] xCords = { currentX, (currentX + branchWidth), (int) (currentX + (branchWidth * .5)) };
		return xCords;
	}

	private int[] getYCords() {
		int[] yCords = { currentY, currentY, (currentY - branchHeight) };
		return yCords;
	}
}// end of class Tree
