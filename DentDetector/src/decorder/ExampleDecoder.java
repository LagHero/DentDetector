package decorder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import dentDetector.IDecodeService;
import modelData.ExampleModelData;

public class ExampleDecoder implements IDecodeService<ExampleModelData> {

    private static Logger LOGGER = Logger.getLogger(ExampleDecoder.class.getSimpleName());

    @Override
    public ExampleModelData decode(File inputFile) {
	final ExampleModelData modelData = new ExampleModelData();
	try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
	    LOGGER.info("Decoding file: " + inputFile.getPath());
	    int lineCount = 1;
	    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
		line = line.trim();
		if (line.isEmpty()) {
		    continue;
		}
		if (line.startsWith("#")) // comment
		{
		    continue;
		} else {
		    modelData.addLine(line);
		}
		// } else {
		// LOGGER.warning("Unable to parse line " + lineCount + ": " +
		// line);
		// }
		lineCount++;
	    }
	    LOGGER.info("Decoded " + lineCount + " lines");
	} catch (final IOException e) {
	    final String errorMessage = "Unable to read the input file";
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	return modelData;
    }
}
