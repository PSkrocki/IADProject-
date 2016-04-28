package Tables;

import java.util.ArrayList;
import java.util.List;


public class Inputs {
	
	private List<Double> inputList;
	
	public Inputs(){
		inputList = new ArrayList<Double>();
	}

	public List<Double> getInputList() {
		return inputList;
	}

	public void addInput(Double value){
		inputList.add(value);
	}
	
	public void deleteInputs(){
		inputList.clear();
	}
}
