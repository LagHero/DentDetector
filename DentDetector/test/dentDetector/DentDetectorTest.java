package dentDetector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DentDetectorTest {

    private static final TestModelData DATA = new TestModelData();
    private static final TestDecoder DECODER = new TestDecoder();
    private static final TestDetector DETECTOR = new TestDetector();
    private static final TestEncoder ENCODER = new TestEncoder();
    private static File INPUT_FILE;
    private static String OUTPUT_FILE_PATH;
    private static int METHOD_CALL_ORDER = 0;

    @BeforeClass
    public static void createTempFiles() throws IOException {
	INPUT_FILE = File.createTempFile("test", ".stl");
	INPUT_FILE.deleteOnExit();
	OUTPUT_FILE_PATH = new File(INPUT_FILE.getParent(), "test.obj").getPath();
    }

    @AfterClass
    public static void deleteTempFiles() throws IOException {
	if (INPUT_FILE != null) {
	    INPUT_FILE.delete();
	}
	if (OUTPUT_FILE_PATH != null) {
	    new File(OUTPUT_FILE_PATH).delete();
	}
    }

    @Test
    public void testEncode() throws IOException {

	final DentDetector<TestModelData, TestDecoder, TestDetector, TestEncoder> testDentDetector = DentDetector
		.create(DECODER, DETECTOR, ENCODER);
	testDentDetector.detectDents(INPUT_FILE.getPath(), OUTPUT_FILE_PATH);

    }

    private static class TestModelData implements IModelData {

    }

    private static class TestDecoder implements IDecodeService<TestModelData> {

	@Override
	public TestModelData decode(File inputFile) {
	    assertTrue(inputFile != null);
	    assertTrue(inputFile.equals(INPUT_FILE));

	    assertEquals(0, METHOD_CALL_ORDER);
	    METHOD_CALL_ORDER++;

	    return DATA;
	}

    }

    private static class TestDetector implements IIdentifyService<TestModelData> {

	@Override
	public void detectDents(TestModelData modelData) {
	    assertTrue(modelData != null);
	    assertTrue(modelData.equals(DATA));

	    assertEquals(1, METHOD_CALL_ORDER);
	    METHOD_CALL_ORDER++;
	}

    }

    private static class TestEncoder implements IEncodeService<TestModelData> {

	@Override
	public void encode(TestModelData modelData, File outputFile) {
	    assertTrue(modelData != null);
	    assertTrue(modelData.equals(DATA));

	    assertTrue(outputFile != null);
	    assertTrue(outputFile.getPath().equals(OUTPUT_FILE_PATH));

	    assertEquals(2, METHOD_CALL_ORDER);
	    METHOD_CALL_ORDER++;
	}

    }

}
