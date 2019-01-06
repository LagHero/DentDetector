package modelData;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dentDetector.IModelData;

public class StandardModelData implements IModelData {

    private final List<Point> points = new LinkedList<>();
    private final List<Face> faces = new LinkedList<>();

    public Iterator<Point> getPointsIterator() {
	return points.iterator();
    }

    public Iterator<Face> getFacesIterator() {
	return faces.iterator();
    }

    public static class Point {
	private final int i;
	private final float x, y, z;
	private int color = 0;

	public Point(int i, float x, float y, float z) {
	    super();
	    this.i = i;
	    this.x = x;
	    this.y = y;
	    this.z = z;
	}

	public int getIndex() {
	    return i;
	}

	public float getX() {
	    return x;
	}

	public float getY() {
	    return y;
	}

	public float getZ() {
	    return z;
	}

	public int getColor() {
	    return color;
	}

	public void setColor(int color) {
	    this.color = color;
	}

	@Override
	public String toString() {
	    return "Point " + i + " [x=" + x + ", y=" + y + ", z=" + z + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + Float.floatToIntBits(x);
	    result = prime * result + Float.floatToIntBits(y);
	    result = prime * result + Float.floatToIntBits(z);
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
		return true;
	    }
	    if (obj == null) {
		return false;
	    }
	    if (getClass() != obj.getClass()) {
		return false;
	    }
	    final Point other = (Point) obj;
	    if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x)) {
		return false;
	    }
	    if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y)) {
		return false;
	    }
	    if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z)) {
		return false;
	    }
	    return true;
	}

    }

    public static class Face {

	private final Point a, b, c;

	public Face(Point a, Point b, Point c) {
	    super();
	    this.a = a;
	    this.b = b;
	    this.c = c;
	}

	public Point getA() {
	    return a;
	}

	public Point getB() {
	    return b;
	}

	public Point getC() {
	    return c;
	}

	@Override
	public String toString() {
	    return "Face [a=" + a + ", b=" + b + ", c=" + c + "]";
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((a == null) ? 0 : a.hashCode());
	    result = prime * result + ((b == null) ? 0 : b.hashCode());
	    result = prime * result + ((c == null) ? 0 : c.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
		return true;
	    }
	    if (obj == null) {
		return false;
	    }
	    if (getClass() != obj.getClass()) {
		return false;
	    }
	    final Face other = (Face) obj;
	    if (a == null) {
		if (other.a != null) {
		    return false;
		}
	    } else if (!a.equals(other.a)) {
		return false;
	    }
	    if (b == null) {
		if (other.b != null) {
		    return false;
		}
	    } else if (!b.equals(other.b)) {
		return false;
	    }
	    if (c == null) {
		if (other.c != null) {
		    return false;
		}
	    } else if (!c.equals(other.c)) {
		return false;
	    }
	    return true;
	}

    }
}
