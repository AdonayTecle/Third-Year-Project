import java.io.IOException;
import java.util.*;
 
public class NeuralNetwork {

    final Random rand = new Random();
    final static ArrayList<Neuron> inputLayer = new ArrayList<Neuron>();
    final static ArrayList<Neuron> hiddenLayer = new ArrayList<Neuron>();
    final static ArrayList<Neuron> outputLayer = new ArrayList<Neuron>();
    final Neuron bias = new Neuron();
    final int[] layers;
    final int randomWeightMultiplier = 1;
    final double epsilon = 0.00000000000001;
 
    double learningRate = 9;
    final double momentum = 0.7;
 
    static ArrayList<ArrayList<Double>> inputs = new ArrayList<ArrayList<Double>>();
    static ArrayList<double[]> actualOutputs = new ArrayList<double[]>();
    static ArrayList<double[]> predictedOutputs = new ArrayList<double[]>();
    static double output[];
    static int what=0;

    
//  public static void main(String[] args) throws IOException {
//	
//	ReadData rd = new ReadData("FORMATTED_abs NLH handhq_2-OBFUSCATED.txt", 0, "RIVER", "SHOW DOWN");
//	inputs = rd.inputs;
//	actualOutputs = rd.actualOutputs;
//	System.out.println("actualOutputs size "+actualOutputs.size());
//	System.out.println("actualOutputs size "+actualOutputs.size());
//	for(int i=0;i<actualOutputs.size()-1;i++){
//		double ab[] = {-1};
//		predictedOutputs.add(ab);	
//	}
//	
//	
//    NeuralNetwork nn = new NeuralNetwork(2, 4, 1);
//    int maxRuns =5000;
//    double minErrorCondition = 0.001;
//    nn.run(maxRuns, minErrorCondition);
//    
//    ReadData rd2 = new ReadData("FORMATTED_abs NLH handhq_2-OBFUSCATED.txt",0, "RIVER", "SHOW DOWN");
//    inputs.clear();
//    actualOutputs.clear();
//	inputs = rd2.inputs;
//	actualOutputs = rd2.actualOutputs;
//	
//	for(int i=0;i<actualOutputs.size()-1;i++){
//		double ab[] = {-1};
//		predictedOutputs.add(ab);	
//	}
//	double error = 1;
//	int count=0;
//	
//	for (int p = 0; p < inputs.size()-1; p++)
//	{
//		feedForward(inputs.get(p));
//          
//		output = getOutput();
//		predictedOutputs.set(p, output);
//          
//		for (int j = 0; j < (actualOutputs.get(p).length); j++) 
//		{	 
//			double err = Math.pow(predictedOutputs.get(j)[0] - actualOutputs.get(p)[j], 2);
//			error += err;
//              
//			if(err<1)
//				count++;       
//		}
//	}
//	  System.out.println("Correctly guessed amount are : "+count+"  / "+actualOutputs.size());
//}
 
    public NeuralNetwork(int input, int hidden, int output) {
        this.layers = new int[] { input, hidden, output };
        
         // Create all neurons and connections Connections are created in the neuron class
         
        for (int i = 0; i < layers.length; i++) {
            if (i == 0) { // input layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    inputLayer.add(neuron);
                }
            } else if (i == 1) { // hidden layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    neuron.addConnections(inputLayer);
                    neuron.addBiasConnection(bias);
                    hiddenLayer.add(neuron);
                }
            }
 
            else if (i == 2) { // output layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    neuron.addConnections(hiddenLayer);
                    neuron.addBiasConnection(bias);
                    outputLayer.add(neuron);
                }
            } else {
                System.out.println("!Error NeuralNetwork init");
            }
        }
 
        // initialize random weights
        for (Neuron neuron : hiddenLayer) {
            ArrayList<Connection> connections = neuron.getAllConnections();
            for (Connection conn : connections) {
                double newWeight = getRandom();
                conn.setWeight(newWeight);
            }
        }
        for (Neuron neuron : outputLayer) {
            ArrayList<Connection> connections = neuron.getAllConnections();
            for (Connection conn : connections) {
                double newWeight = getRandom();
                conn.setWeight(newWeight);
            }
        }
 
        // reset id counters
        Neuron.counter = 0;
        Connection.counter = 0;
    }
 
    // random
    double getRandom(){
        return randomWeightMultiplier * (rand.nextDouble() * 2 - 1); // [-1;1[
    }
 
    public static double[] getOutput() {
        double[] outputs = new double[outputLayer.size()];
        for (int i = 0; i < outputLayer.size(); i++)
            outputs[i] = outputLayer.get(i).getOutput();
        return outputs;
    }
 
    
    //Calculate the output of the neural network based on the input The forward operation
    public static void feedForward(ArrayList<Double> inputs) {
    	for (int l = 0; l < inputLayer.size(); l++)//putting the inputs through the input layer
        	inputLayer.get(l).setOutput(inputs.get(l));
    	
        for (Neuron n : hiddenLayer)
        	n.calculateOutput();
        
        for (Neuron n : outputLayer)
        	n.calculateOutput();
    }
 
    
      //all output propagate back
     //  actualOutput  first calculate the partial derivative of the error with
     //            respect to each of the weight leading into the output neurons
     //            bias is also updated here
     
    public void backpropagate(double actualOutput[]) {
 
        // error check, normalize value ]0;1[
//        for (int i = 0; i < actualOutput.length; i++) {
//            double d = actualOutput[i];
//            if (d < 0 || d > 1) {
//                if (d < 0)
//                	actualOutput[i] = 0 + epsilon;
//                else
//                	actualOutput[i] = 1 - epsilon;
//            }
//        }
 
        int i = 0;
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllConnections();
            for (Connection con : connections) {
                double ak = n.getOutput();
                double ai = con.from.getOutput();
                double desiredOutput = actualOutput[i];
 
                double partialDerivative = -ak * (1 - ak) * ai * (desiredOutput - ak);
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + (momentum * con.getPrevDeltaWeight()));
            }
            i++;
        }
 
        // update weights for the hidden layer
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllConnections();
            for (Connection con : connections) {
                double aj = n.getOutput();
                double ai = con.from.getOutput();
                double sumKoutputs = 0;
                int j = 0;
                for (Neuron out_neu : outputLayer) {
                    double wjk = out_neu.getConnection(n.id).getWeight();
                    double desiredOutput = (double) actualOutput[j];
                    double ak = out_neu.getOutput();
                    j++;
                    sumKoutputs = sumKoutputs + (-(desiredOutput - ak) * ak * (1 - ak) * wjk);
                }
 
                double partialDerivative = aj * (1 - aj) * ai * sumKoutputs;
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + (momentum * con.getPrevDeltaWeight()));
            }
        }
    }
 
    void run(int maxSteps, double minError) {
        int i;
        // Train neural network until minError reached or maxSteps exceeded
        double error = 1;
        for (i = 0; i < maxSteps && error > minError; i++) {
            error = 0;
            for (int p = 0; p < inputs.size()-1; p++) {
 
                feedForward(inputs.get(p));
 
                output = getOutput();
                
                predictedOutputs.set(p, output);
               
                for (int j = 0; j < actualOutputs.get(p).length; j++) {
                    double err = Math.pow(output[j] - actualOutputs.get(p)[j], 2);
                    error += err;
                }
 
                backpropagate(actualOutputs.get(p));
            }
           // System.out.println("ERROR " + error);
            updateLearningRate(i);
            //System.out.println("learning rate  " + learningRate);
        }
         
       // System.out.println("Sum of squared errors = " + error);
        //System.out.println("##### EPOCH " + i+"\n");
        if (i == maxSteps) {
            System.out.println("!Error training try again");
            
        } else {
        	//System.out.println("Success");
        }
        
    }
    
    public static double[] getPrediction(ArrayList<Double> input){
    	feedForward(input);
		output = getOutput();//get the predicted output for the inputs
		return output;//return the predicted outputs
    }
    
    public void updateLearningRate(double time){//updates the learning rate
    	learningRate = learningRate/(time+1); 
    }
}