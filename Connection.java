public class Connection {
    private double weight = 0;
    private double prevDeltaWeight = 0; // for momentum
    private double deltaWeight = 0;
 
    final Neuron from;
   
    public Connection(Neuron fromN) {
        from = fromN;
    }
 
    public double getWeight() {
        return weight;
    }
 
    public void setWeight(double w) {
        weight = w;
    }
 
    public void setDeltaWeight(double w) {
        prevDeltaWeight = deltaWeight;
        deltaWeight = w;
    }
 
    public double getPrevDeltaWeight() {
        return prevDeltaWeight;
    }
 
    public Neuron getFromNeuron() {
        return from;
    }
 
//    public Neuron getToNeuron() {
//        return to;
//    }
}