package Logic;

import java.util.List;
import java.util.Random;

public class Neuron {

	private List<Double> inputs;
	public List<Double> getInputs() {
		return inputs;
	}
	public void setInputs(List<Double> inputs) {
		this.inputs = inputs;
	}

	private double[] weights;
	private double beta;
	private double bias;

	public Neuron(List<Double> inputs){
		neuronConstructor(inputs);
	}
	public Neuron(){
		
	}

	public Neuron(List<Double> inputs, double bias){
		neuronConstructor(inputs);
		this.bias=bias;
	}

	private void neuronConstructor(List<Double> inputs){
		this.inputs=inputs;	
		this.weights=new double[inputs.size()];
		this.beta=1;
		
		for(int i=0;i<inputs.size();i++)
			this.weights[i]=randomWeight();
		bias=0;
	}

	private double sigmoidalFunction(double x){
		return 1.0/(1.0+Math.exp(-beta * x));
	}

	private double randomWeight(){
		Random rand = new Random();
		double randomWeight;
		return randomWeight= -1.0 + (1.0 + 1.0) * rand.nextDouble();
	}

	private double AddInputsMultiplyWeights(){
		double value=0;
		for(int i=0;i<inputs.size();i++)
		{
			try{
				value+=inputs.get(i)*weights[i];
			}
			catch (NullPointerException e){
				System.out.println("Brakuje ktorejs wagi");
				break;
			}					
		}
		value+=bias;
		return value;
	}
	
	public double startNeuron(){
		return sigmoidalFunction(AddInputsMultiplyWeights());
	}
	

}
