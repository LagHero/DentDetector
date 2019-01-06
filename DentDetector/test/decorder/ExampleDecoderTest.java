package decorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import modelData.ExampleModelData;

public class ExampleDecoderTest {

    @Test
    public void testDecode() throws IOException {
	final ExampleDecoder decoder = new ExampleDecoder();

	final File temp = File.createTempFile("test", ".stl");
	temp.deleteOnExit();
	final BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
	bw.write("#comment");
	bw.newLine();
	bw.write("test line of text");
	bw.newLine();
	bw.write("second line of text");
	bw.flush();
	bw.close();

	final ExampleModelData data = decoder.decode(temp);

	final Iterator<String> iter = data.iterator();

	assertTrue(iter.hasNext());
	assertEquals("test line of text", iter.next());

	assertTrue(iter.hasNext());
	assertEquals("second line of text", iter.next());

	assertFalse(iter.hasNext());

	temp.delete();
    }
}
