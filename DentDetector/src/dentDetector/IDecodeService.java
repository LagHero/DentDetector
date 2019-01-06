package dentDetector;

import java.io.File;

public interface IDecodeService<ModelData extends IModelData> {

    public ModelData decode(File inputFile);
}
