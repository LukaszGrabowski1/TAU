import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class VectorTests {


	@Test 
	public void addVectortoExisting() throws ErrorException{
		 List<Double> vector1 = new ArrayList<>();
		 VectorImpl vectorImpl1 = new VectorImpl();
		 vector1.add(4.8);
		 vectorImpl1.setVector(vector1);
		 
		 List<Double> vector2 = new ArrayList<>();
		 VectorImpl vectorImpl2 = new VectorImpl();
		 vector2.add(5.2);
		 vectorImpl2.setVector(vector2);
		 
		 List<Double> suma1= vectorImpl1.add(vector2);
		 List<Double> suma2 = vectorImpl1.add(vectorImpl2);
		 
		 assertEquals(suma1, suma2);
	}
	
	@Test
	public void isVectorEmpty(){
		VectorImpl vector = new VectorImpl();
		List<Double> vectorList = new ArrayList<>();
		assertTrue(vector.getVector().isEmpty());
		assertTrue(vectorList.isEmpty());
	}

}
