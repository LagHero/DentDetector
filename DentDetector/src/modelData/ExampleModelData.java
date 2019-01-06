package modelData;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dentDetector.IModelData;

public class ExampleModelData implements IModelData {

    private final List<String> lines = new LinkedList<>();

    public void addLine(String line) {
	lines.add(line);
    }

    public Iterator<String> iterator() {
	return lines.iterator();
    }

}
