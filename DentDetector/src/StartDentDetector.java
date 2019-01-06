import decorder.ExampleDecoder;
import dentDetector.DentDetector;
import dentDetector.IDecodeService;
import dentDetector.IEncodeService;
import dentDetector.IIdentifyService;
import detector.ExampleDetector;
import encoder.ExampleEncoder;
import modelData.ExampleModelData;

public class StartDentDetector {

    public static void main(String[] args) {

	if (args.length < 2) {
	    throw new IllegalArgumentException("Dent Detector needs an input file and an output file");
	}

	final String inputfilePath = args[0];
	final String outputfilePath = args[1];

	// Create the service classes
	final IDecodeService<ExampleModelData> decodeService = new ExampleDecoder();
	final IIdentifyService<ExampleModelData> identifyService = new ExampleDetector();
	final IEncodeService<ExampleModelData> encodeService = new ExampleEncoder();

	// Create the Dent Detector and detect dents!
	final DentDetector<ExampleModelData, IDecodeService<ExampleModelData>, IIdentifyService<ExampleModelData>, IEncodeService<ExampleModelData>> dentDetector = DentDetector
		.create(decodeService, identifyService, encodeService);
	dentDetector.detectDents(inputfilePath, outputfilePath);

    }

}
