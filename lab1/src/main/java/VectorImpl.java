import java.util.ArrayList;
import java.util.List;

public class VectorImpl implements Vector{

    List<Double> vector;

    public void setVector(List<Double> vector) {
        this.vector = vector;
    }

    public List<Double> getVector() {
        return vector;
    }

    public VectorImpl(){
        this.vector = new ArrayList<>();
    }


    @Override
    public List<Double> add(List<Double> vector) throws ErrorException {

        List<Double> tempList = new ArrayList<>();
        for(int i = 0; i< vector.size(); i++){
        	tempList.add(vector.get(i) + this.vector.get(i));
        }
        return tempList;
        
    }

	@Override
	public List<Double> add(VectorImpl vecrotImpl) throws ErrorException {
		List<Double> temp = new ArrayList<>();
		for(int i = 0; i< this.vector.size(); i++){
			temp.add(vecrotImpl.getVector().get(i) + this.vector.get(i));
		}
		return temp;
	}
}
