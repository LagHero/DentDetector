package encoder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

import dentDetector.IEncodeService;
import modelData.ExampleModelData;

public class ExampleEncoder implements IEncodeService<ExampleModelData> {

    private static Logger LOGGER = Logger.getLogger(ExampleEncoder.class.getSimpleName());

    @Override
    public void encode(ExampleModelData modelData, File outputFile) {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
	    LOGGER.info("Encoding file: " + outputFile.getPath());
	    int lineCount = 1;
	    for (final Iterator<String> iter = modelData.iterator(); iter.hasNext();) {
		writer.write(iter.next());
		writer.newLine();
		lineCount++;
	    }
	    LOGGER.info("Encoded " + lineCount + " lines");
	} catch (final IOException e) {
	    final String errorMessage = "Unable to write to the output file";
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
    }
}
