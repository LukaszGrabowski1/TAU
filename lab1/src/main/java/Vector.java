import java.util.List;


public interface Vector {
    List<Double> add( List<Double> vector) throws ErrorException;
    List<Double> add( VectorImpl vecrotImpl) throws ErrorException;
}
