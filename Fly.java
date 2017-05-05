import java.util.ArrayList;
public class Fly {
	double[] pos = new double[Global.dim];
	int fitness = 0;
	ArrayList<Card> list = new ArrayList<Card>();

	public Fly(double[] position, ArrayList<Card>l) {
		pos = position.clone();
		list = l;
	}

	public double[] getPos() {
		return pos.clone();
	}

	public double getPos(int n) {
		return pos[n];
	}

	public void setPos(double[] position) {
		pos = position.clone();
	}

	public void setPos(int i, double n) {
		pos[i] = n;
	}

	public void setFitness(int t) {
		fitness = t;
	}

	public double getFitness() {
		return fitness;
	}

	public double getDistance(int n) {
		double squaredSum = 0;
		for (int d = 0; d < Global.dim - 1; d++) {
			squaredSum = squaredSum + Math.pow(getPos(d) - Global.fly[n].getPos(d), 2);
		}
		return Math.sqrt(squaredSum);
	}

}