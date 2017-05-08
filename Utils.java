//
//import java.util.Random;
//import java.math.*;
//import java.util.ArrayList;
//public class Utils {
//	
//	Random r = new Random();
//	//Possible_Hands p = new Possible_Hands();
//	WeightingCards p = new WeightingCards();
//	
//	public int evaluate(ArrayList<Card>list) {//fitness function
//		Global.evalCount++;
//		return Math.abs(p.evaluate(r.nextInt(9)+1, list));
//	}
//	
//	public void getRandF_or_RingT_Neighbours(int curr, boolean topology) {
//		if (topology)
//		{
//			Global.leftN = curr - 1;
//			Global.rightN = curr + 1;
//
//			if (curr == 0)
//				Global.leftN = Global.popSize - 1;
//			if (curr == Global.popSize - 1)
//				Global.rightN = 0;
//		}
//	}
//
//	public void findBestFly() {
//		double max = -1;
//		for (int i = 0; i < Global.popSize; i++) {
//			if (Global.fly[i].getFitness() > max) {
//				max = Global.fly[i].getFitness();
//				Global.bestIndex = i;
//			}
//		}
//	}
//
//	public double[] getRandPos() {  							
//		double[] a = new double[Global.dim];
//		for (int d = 0; d < Global.dim; d++)
//		{
//			a[d] = -Global.imgW / 2 + 2 * Global.imgW / 2 * r.nextDouble();
//		}
//		return a.clone();
//	}
//	int dimension = Global.dim;
//	double offset = -0;
//}
