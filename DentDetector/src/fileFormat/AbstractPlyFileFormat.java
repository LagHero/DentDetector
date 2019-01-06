package fileFormat;

import modelData.StandardModelData.Face;
import modelData.StandardModelData.Point;

// http://paulbourke.net/dataformats/ply/
// https://people.sc.fsu.edu/~jburkardt/data/ply/ply.html
public abstract class AbstractPlyFileFormat extends AbstractFileFormat {

    protected String createHeader(int pointsSize, int facesSize) {
	final StringBuilder sb = new StringBuilder();
	sb.append("ply").append(NEWLINE);
	sb.append("format ascii 1.0").append(NEWLINE);
	sb.append("element vertex ").append(pointsSize).append(NEWLINE);
	sb.append("property float x").append(NEWLINE);
	sb.append("property float y").append(NEWLINE);
	sb.append("property float z ").append(NEWLINE);
	sb.append("property uchar red").append(NEWLINE);
	sb.append("element face ").append(facesSize).append(NEWLINE);
	sb.append("property list uchar int vertex_index").append(NEWLINE);
	sb.append("end_header").append(NEWLINE);
	return sb.toString();
    }

    protected String printPoint(Point point) {
	return String.format("%s %s %s %s%n", Float.toString(point.getX()), Float.toString(point.getY()),
		Float.toString(point.getZ()), Integer.toString(point.getColor()));
    }

    protected String printFace(Face face) {
	return String.format("3 %s %s %s%n", Float.toString(face.getA().getIndex()),
		Float.toString(face.getB().getIndex()), Float.toString(face.getC().getIndex()));
    }

    protected Point parsePoint(String line, int index) {
	if (line == null || line.isEmpty()) {
	    throw new IllegalArgumentException("The given line cannot be empty");
	}
	final String[] lineParts = line.split(" ");
	if (lineParts.length != 4) {
	    throw new IllegalArgumentException("The given line does not have x, y, z, and color: " + line);
	}
	float x, y, z;
	try {
	    x = Float.parseFloat(lineParts[0]);
	    y = Float.parseFloat(lineParts[1]);
	    z = Float.parseFloat(lineParts[2]);
	} catch (final NumberFormatException e) {
	    throw new IllegalArgumentException("Unable to parse the given line into x, y, z: " + line, e);
	}
	final Point point = new Point(index, x, y, z);
	try {
	    point.setColor(Integer.parseInt(lineParts[3]));
	} catch (final NumberFormatException e) {
	    throw new IllegalArgumentException("Unable to parse the color from given line: " + line, e);
	}
	return point;
    }
}
