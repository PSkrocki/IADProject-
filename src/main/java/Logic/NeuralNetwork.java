package Logic;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

	private List<Neuron> inputLayer;
	private List<Neuron> hiddenLayer;
	private List<Neuron> outputLayer;
	
	private List<Inputs> inputsList;

	private int howManyHiddenLayer=1;
	private int howManyEpoch;


	private int howManyInputNeurons=4;
	private int howManyHiddenNeurons=2;
	private int howManyOutputNeurons=4;

	public NeuralNetwork(){
		InitialLayers();
	}

	
	private void InitialLayers(){
		for(int i=0;i<howManyInputNeurons;i++)
			inputLayer.add(new Neuron(inputsList.get(i).getInputsList()));

		for(int i=0;i<howManyHiddenNeurons;i++)
			hiddenLayer.add(new Neuron());

		for(int i=0;i<howManyOutputNeurons;i++)
			hiddenLayer.add(new Neuron());
	}

	private void learn(){
		List<Double> tempHidden=new ArrayList<Double>();
		
		while(howManyEpoch>0){
			
			for(int i=0;i<hiddenLayer.size();i++){
				tempHidden.add(hiddenLayer.get(i).startNeuron());
			}
			
			for(int i=0;i<outputLayer.size();i++){
				Neuron outputNeuron = outputLayer.get(i);
				outputNeuron.setInputs(tempHidden);
			}
			howManyEpoch--;
		}
		
		
	}
}
