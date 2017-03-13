package pl.edu.pjwstk.lab2;

import java.util.List;

public interface Vector {
    List<Double> add( List<Double> vector) throws ErrorException;
    List<Double> add( VectorImpl vecrotImpl) throws ErrorException;
    List<Double> sub(List<Double> vector) throws ErrorException;
    List<Double> sub(VectorImpl vectorImpl) throws ErrorException;
}
