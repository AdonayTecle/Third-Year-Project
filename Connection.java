public class Connection {
    private double weight = 0;
    private double prevDeltaWeight = 0; // for momentum
    private double deltaWeight = 0;
 
    final Neuron from;
    final Neuron to;
    static int counter = 0;
    final public int id; // auto increment, starts at 0
 
    public Connection(Neuron fromN, Neuron toN) {
        from = fromN;
        to = toN;
        id = counter;
        counter++;
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