import java.util.*;
 
public class Neuron {   
    static int counter = 0;//number of neurons in the network
    final public int id;  // auto increment, starts at 0
    Connection biasConnection;
    final double bias = 1;
    double output;
     
    ArrayList<Connection> allConnections = new ArrayList<Connection>();
    HashMap<Integer,Connection> connectionLookup = new HashMap<Integer,Connection>();
     
    public Neuron(){        
        id = counter;
        counter++;
       // System.out.println(counter);    
        while(counter>100){}
    }
     
    /**
     * Compute Sj = Wij*Aij + w0j*bias
     */
    public void calculateOutput(){
        double s = 0;
        for(Connection con : allConnections){
            Neuron leftNeuron = con.getFromNeuron();
            double weight = con.getWeight();
            double a = leftNeuron.getOutput(); //output from previous layer
             
            s = s + (weight*a);
        }
        s = s + (biasConnection.getWeight()*bias);
         
        output = sigmoid(s);
    }
     
    double sigmoid(double x) {
        return 1.0 / (1.0 +  (Math.exp(-x)));//sigmoid function
    	//return Math.exp(-(Math.pow(x, 2)));
    }
     
    public void addConnections(ArrayList<Neuron> neurons){
        for(Neuron n: neurons){
            Connection con = new Connection(n);
            allConnections.add(con);
            connectionLookup.put(n.id, con);
        }
    }
     
    public Connection getConnection(int neuronIndex){
        return connectionLookup.get(neuronIndex);
    }
 
    public void addBiasConnection(Neuron n){
        Connection con = new Connection(n);
        biasConnection = con;
        allConnections.add(con);
    }
    public ArrayList<Connection> getAllConnections(){
        return allConnections;
    }
    
    public double getOutput() {
        return output;
    }
    public void setOutput(double o){
        output = o;
    }
}