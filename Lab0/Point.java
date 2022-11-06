/**
 * CS2030S Lab 0: Point.java
 * Semester 1, 2022/23
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author XXX
 */

import java.lang.Math.*;

class Point {
  // TODO

	private double x;
	private double y;

	public Point(double x, double y) {

		this.x = x;
		this.y = y;

	}


	public void setX(double x) {

		this.x = x;

	}

	public void setY(double y) {

		this.y = y;

	}

	public double getX() {

		return this.x;

	}

	public double getY() {

		return this.y;

	}

	public double distanceTo(Point c) {


		double distance = Math.sqrt( (Math.pow((this.y - c.y), 2)) + Math.pow(this.x - c.x, 2) );
		return distance;

	}

	@Override
	public String toString() {

		return "(" + this.x + ", " + this.y + ")";

	}


}
