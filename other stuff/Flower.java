package FinalDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Flower extends LandscapeObject {

	// attributes
	private Color stemColor;
	private Color bloomColor;
	private char flowerType;
	private int stemHeight = 9;
	private int stemWidth = 1;
	private double leafHeight = 1.5;
	private double leafWidth = 1.5;
	private int bloomHeight = 4;
	private int bloomWidth = 6;
	private int stroke = 1;
	private Graphics2D rotateGraphic = g2;
	private AffineTransform old = rotateGraphic.getTransform();

	// constructor

	public Flower(Graphics2D g2, int x, int y, double scale, String s_stemColor, String s_bloomColor, char fT) {
		super(g2, x, y, scale);
		this.flowerType = fT;
		this.stemColor = new Color(Integer.parseInt(s_stemColor, 16));
		this.bloomColor = new Color(Integer.parseInt(s_bloomColor, 16));
	}

	public void draw() {
		applyScale();
		g2.setStroke(new BasicStroke(stroke));
		drawStem();
		drawLeaf();
		drawBloom();
	}

	public void applyScale() {
		stemHeight = (int) (getScale() * stemHeight);
		stemWidth = (int) (getScale() * stemWidth);
		leafHeight = (int) (getScale() * leafHeight);
		leafWidth = (int) (getScale() * leafWidth);
		bloomHeight = (int) (getScale() * bloomHeight);
		bloomWidth = (int) (getScale() * bloomWidth);
		if (getScale() <= 4) {
			stroke = (int) (getScale() * stroke);
		} else {
			stroke = 5;
		}
	}

	public void drawStem() {
		Rectangle2D.Double stem = new Rectangle2D.Double(currentX, currentY, stemWidth, stemHeight);
		g2.setColor(Color.BLACK);
		g2.draw(stem);
		g2.setColor(stemColor);
		g2.fill(stem);
	}

	private void drawLeaf() {
		Rectangle2D.Double stem = new Rectangle2D.Double(currentX, currentY, stemWidth, stemHeight);
		double stemCenterX = stem.getCenterX();
		double stemCenterY = stem.getCenterY();
		Ellipse2D.Double leaf = new Ellipse2D.Double(stemCenterX - (getScale() * 2), stemCenterY - (getScale() * 1),
				leafWidth, leafHeight);
		double leafCenterX = leaf.getCenterX();
		double leafCenterY = leaf.getCenterY();
		int lTX1 = (int) (leafCenterX);
		int lTX2 = (int) (leafCenterX + (getScale() * .75));
		int lTX3 = (int) (leafCenterX - (getScale() * .75));
		int lTY1 = (int) (leafCenterY - getScale() * 1.75);
		int lTY2 = (int) (leafCenterY);
		int lTY3 = (int) (leafCenterY);
		int[] lTXCords = { lTX1, lTX2, lTX3 };
		int[] lTYCords = { lTY1, lTY2, lTY3 };
		g2.rotate(-45, leafCenterX, leafCenterY);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(lTXCords, lTYCords, 3);
		g2.draw(leaf);
		g2.setColor(stemColor);
		g2.fillPolygon(lTXCords, lTYCords, 3);
		g2.fill(leaf);
		removeRotation();

	}

	public void drawBloom() {
		g2.setColor(bloomColor);
		Rectangle2D.Double stem = new Rectangle2D.Double(currentX, currentY, stemWidth, stemHeight);
		double stemCenterX = stem.getCenterX();
		double stemCenterY = stem.getCenterY();
		Ellipse2D.Double daisy = new Ellipse2D.Double(stemCenterX - (getScale() * 1), stemCenterY - (getScale() * 5.5),
				(int) (getScale() * 2), (int) (getScale() * 2));
		double daisyCenterX = daisy.getCenterX();
		double daisyCenterY = daisy.getCenterY();
		if (flowerType == 'd') {
			Ellipse2D.Double petal = new Ellipse2D.Double(daisyCenterX, daisyCenterY, (int) (getScale() * 1),
					(int) (getScale() * 3));
			for (int i = 0; i < 9; i++) {
				g2.rotate(1.4, daisyCenterX, daisyCenterY);
				g2.setColor(Color.BLACK);
				g2.draw(petal);
				g2.setColor(Color.WHITE);
				g2.fill(petal);
			}
			removeRotation();
			g2.setColor(Color.BLACK);
			g2.draw(daisy);
			g2.setColor(Color.YELLOW);
			g2.fill(daisy);
			
		} else {
			int mX1 = (int) (currentX + (0.5 * stemWidth) - (0.5 * bloomWidth));
			int mX2 = (int) (currentX + (0.5 * stemWidth) + (0.5 * bloomWidth));
			int mX3 = (int) (currentX + (0.5 * stemWidth));
			int mY1 = currentY;
			int mY2 = currentY;
			int mY3 = currentY - bloomHeight;
			int[] midxCords = { mX1, mX2, mX3 };
			int[] midyCords = { mY1, mY2, mY3 };

			Polygon mid = new Polygon(midxCords, midyCords, 3);

			int lX1 = (int) (currentX + (0.5 * stemWidth) - (0.6 * bloomWidth));
			int lX2 = (int) (currentX + (bloomWidth) - (stemWidth * 2.25 ));
			int lX3 = (int) (currentX + (0.5 * stemWidth) - (0.5 * bloomWidth));
			int lY1 = (currentY);
			int lY2 = (currentY);
			int lY3 = (currentY - bloomHeight);
			int[] leftxCords = { lX1, lX2, lX3 };
			int[] leftyCords = { lY1, lY2, lY3 };

			Polygon left = new Polygon(leftxCords, leftyCords, 3);

			int rX1 = (int) (currentX + (0.5 * stemWidth) - (0.5 * bloomWidth));
			int rX2 = (int) (currentX + (bloomWidth) - (stemWidth*1.75));
			int rX3 = (int) (currentX + (bloomWidth) - (stemWidth*2));
			int rY1 = currentY;
			int rY2 = (currentY);
			int rY3 = (currentY - bloomHeight);
			int[] rightxCords = { rX1, rX2, rX3 };
			int[] rightyCords = { rY1, rY2, rY3 };

			Polygon right = new Polygon(rightxCords, rightyCords, 3);

			g2.setColor(Color.BLACK);
			g2.draw(left);
			g2.draw(right);
			g2.draw(mid);

			g2.setColor(bloomColor);
			g2.fill(left);
			g2.fill(right);
			g2.fill(mid);

		}
	}

	private void removeRotation() {
		rotateGraphic.setTransform(old);
	}

}
