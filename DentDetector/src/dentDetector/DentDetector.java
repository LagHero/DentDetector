package dentDetector;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class DentDetector<ModelData extends IModelData, DecodeService extends IDecodeService<ModelData>, IdentifyService extends IIdentifyService<ModelData>, EncodeService extends IEncodeService<ModelData>> {

    private static Logger LOGGER = Logger.getLogger(DentDetector.class.getSimpleName());

    private final DecodeService decoder;
    private final IdentifyService detector;
    private final EncodeService encoder;

    private DentDetector(DecodeService decoder, IdentifyService detector, EncodeService encoder) {
	super();
	this.decoder = decoder;
	this.detector = detector;
	this.encoder = encoder;
    }

    public static <ModelData extends IModelData, DecodeService extends IDecodeService<ModelData>, IdentifyService extends IIdentifyService<ModelData>, EncodeService extends IEncodeService<ModelData>> DentDetector<ModelData, DecodeService, IdentifyService, EncodeService> create(
	    DecodeService decoder, IdentifyService detector, EncodeService encoder) {
	return new DentDetector<>(decoder, detector, encoder);
    }

    public void detectDents(String inputfilePath, String outputfilePath) {

	// Validate that the first arg is a file.
	final File inputFile = validateInputFilePath(inputfilePath);

	// Validate that the second arg is a file.
	final File outputFile = validateOutputFilePath(outputfilePath);

	// Decode the input file into a model
	final ModelData modelData = decoder.decode(inputFile);

	// Detect dents in the model
	detector.detectDents(modelData);

	// Encode the model into the output file
	encoder.encode(modelData, outputFile);
    }

    private static File validateInputFilePath(String inputfilePath) {
	if (inputfilePath == null) {
	    final String errorMessage = "The input file path cannot be empty";
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	final File inputFile = new File(inputfilePath);
	if (!inputFile.exists()) {
	    final String errorMessage = "Unable to find input file: " + inputfilePath;
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	if (!inputFile.isFile()) {
	    final String errorMessage = "The input file must be a file: " + inputfilePath;
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	if (!inputFile.canRead()) {
	    final String errorMessage = "Unable to read the input file: " + inputfilePath;
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	return inputFile;
    }

    private static File validateOutputFilePath(String outputfilePath) {
	if (outputfilePath == null) {
	    final String errorMessage = "The output file path cannot be empty";
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	final File outputFile = new File(outputfilePath);
	if (outputFile.exists()) {
	    final String errorMessage = "The output file already exists: " + outputFile;
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	try {
	    if (!outputFile.createNewFile()) {
		final String errorMessage = "Unable to create the output file : " + outputFile;
		LOGGER.severe(errorMessage);
		throw new IllegalArgumentException(errorMessage);
	    }
	} catch (final IOException e) {
	    final String errorMessage = "Exception while creating the output file: " + outputFile;
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage, e);
	}
	if (!outputFile.isFile()) {
	    final String errorMessage = "The output file must be a file: " + outputFile;
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	if (!outputFile.canWrite()) {
	    final String errorMessage = "Unable to write the output file: " + outputFile;
	    LOGGER.severe(errorMessage);
	    throw new IllegalArgumentException(errorMessage);
	}
	return outputFile;
    }
}
