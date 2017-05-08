//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class DFO{
//
//	public double calculate(ArrayList<Card> l) {
//		Utils utils = new Utils();
//		Random r = new Random();
//
//		for (int i = 0; i < Global.popSize; i++)
//			Global.fly[i] = new Fly(utils.getRandPos(), l);
//
//		utils.findBestFly();
//		
//		while (Global.evalCount < Global.FE_allowed) {
//			
//			for (int i = 0; i < Global.popSize; i++)
//			{
//				Global.fly[i].setFitness(utils.evaluate(Global.fly[i].list));
//			}
//
//			utils.findBestFly();
//
//			for (int i = 0; i < Global.popSize; i++) {
//				if (i == Global.bestIndex)
//					continue;
//
//				utils.getRandF_or_RingT_Neighbours(i, true);
//				double leftP, rightP;
//				if (true) {
//					leftP = Global.fly[Global.leftN].getFitness();
//					rightP = Global.fly[Global.rightN].getFitness();
//				}		
//				int chosen;
//				if (leftP < rightP)
//					chosen = Global.leftN;
//				else
//					chosen = Global.rightN;
//				
//				int dCounter = 0;
//				
//				double[] temp = new double[Global.dim];
//				for (int d = 0; d < Global.dim; d++) {
//					temp[d] = Global.fly[chosen].getPos(d)+r.nextDouble() * (Global.fly[Global.bestIndex].getPos(d) - Global.fly[i].getPos(d));
//					if (true)
//						if (r.nextDouble() < Global.dt)
//						{
//							temp[d] = //utils.getGaussian(Global.imgW/2.0, Global.imgW/2.0);
//							dCounter++;
//						}
//				}
//				Global.fly[i].setPos(temp.clone());
//			}
//		}	
//		Global.dim = 1;
//		Global.popSize = 20;
//		Global.dt = 0.001;
//		Global.FE_allowed = 1000;
//		Global.evalCount = 0;
//		return Global.fly[Global.bestIndex].fitness;
//	}
//}