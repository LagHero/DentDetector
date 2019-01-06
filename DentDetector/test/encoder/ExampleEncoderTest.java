package encoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import modelData.ExampleModelData;

public class ExampleEncoderTest {

    @Test
    public void testEncode() throws IOException {
	final ExampleEncoder encoder = new ExampleEncoder();

	final ExampleModelData data = new ExampleModelData();
	data.addLine("test line of text");
	data.addLine("second line of text");

	final File temp = File.createTempFile("test", ".obj");
	temp.deleteOnExit();

	encoder.encode(data, temp);

	final BufferedReader br = new BufferedReader(new FileReader(temp));

	String line = br.readLine();
	assertFalse(line == null);
	assertEquals("test line of text", line);

	line = br.readLine();
	assertFalse(line == null);
	assertEquals("second line of text", line);

	line = br.readLine();
	assertTrue(line == null);

	br.close();
	temp.delete();
    }
}
