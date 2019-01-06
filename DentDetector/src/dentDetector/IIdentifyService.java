package dentDetector;

public interface IIdentifyService<ModelData extends IModelData> {

    public void detectDents(ModelData modelData);
}
