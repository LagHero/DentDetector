package dentDetector;

import java.io.File;

public interface IEncodeService<ModelData extends IModelData> {

    public void encode(ModelData modelData, File outputFile);
}
