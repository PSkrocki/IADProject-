package Tables;

import java.util.List;

public class Data {

	Inputs inputs;
	
	public Data(){
		inputs= new Inputs();
	}
	
	public List<Double> getInputList(){
		return inputs.getInputList();
	}
	
	public void addInput(Double value){
		inputs.addInput(value);
	}
	
	public void clearInput(){
		inputs.deleteInputs();
	}
}
