/**
 * 
 */
package eu.tondryk.jsfol.type;

/**
 * @author ptondryk
 *
 */
public class Coordinate {

	/**
	 * 
	 */
	private double x;

	/**
	 * 
	 */
	private double y;

	/**
	 * @param x
	 * @param y
	 */
	public Coordinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

}