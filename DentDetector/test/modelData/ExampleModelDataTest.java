package modelData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

public class ExampleModelDataTest {

    @Test
    public void testExampleModelData() throws IOException {
	final ExampleModelData data = new ExampleModelData();

	Iterator<String> iter = data.iterator();
	assertFalse(iter.hasNext());

	data.addLine("test");
	iter = data.iterator();
	assertTrue(iter.hasNext());
	assertEquals("test", iter.next());
	assertFalse(iter.hasNext());

    }
}
