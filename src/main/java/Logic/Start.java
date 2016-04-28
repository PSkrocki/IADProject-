package Logic;

import Tables.Data;
import Tables.Inputs;

/**
 * Hello world!
 *
 */
public class Start 
{
    public static void main( String[] args )
    {
       Data data= new Data();
       Neuron neuron=new Neuron(data.getInputList());
    }
}
