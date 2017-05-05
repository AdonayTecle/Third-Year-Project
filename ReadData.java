import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadData {
	ArrayList<ArrayList<Double>> inputs = new ArrayList<ArrayList<Double>>();//contains all the inputs for the neural network
    ArrayList<double[]> actualOutputs = new ArrayList<double[]>();//contains all the outputs of the inputs
    
    
    
    public ReadData(String fileName, int choice, String currentRound, String nextRound)
    {
    	File file = new File(fileName);
		int count = -1;
		boolean seat4=false;
		boolean seat6=false;
		boolean flop=false;
		ArrayList<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> outcome = new ArrayList<Integer>();
		ArrayList<Double> raiseBy = new ArrayList<Double>();//contains the amount by which the player raises at each stage
		ArrayList<Double> previousRaiseBy = new ArrayList<Double>();
		boolean seat4Player=false;
		boolean turn = true;
		String name="";
		ArrayList<double[]> profit = new ArrayList<double[]>();
		String previous="";
		boolean flopCheck=false;
		boolean tempVar= false;
		ArrayList<String> inputTemp = new ArrayList<String>();
		double raiseByTemp=-1;
		String previousRaiseByTemp="3";
		boolean previousRaiseTemp=true;
		double total=0;
		boolean totalTemp=false;
		try {

			Scanner sc = new Scanner(file);
			boolean skip = true;
			boolean gate = false;
			while (sc.hasNextLine()) {
				String i = sc.nextLine();
				Scanner sc2 = new Scanner(i);
				
				if(i.contains("Seat 4:"))
					if(i.contains("Total"))
						seat4=true;
					
				if(i.contains("Seat 6:"))
					if(i.contains("Total"))
						seat6=true;
	
				if(i.contains("Stage"))
				{
					ArrayList<String> temp = new ArrayList<String>();
					temp = (ArrayList<String>) inputTemp.clone();
					
					if(flopCheck==true){
							//if(seat4==true){
								input.add(temp);
								raiseBy.add(raiseByTemp/total);
								//System.out.println(previousRaiseByTemp);
								previousRaiseBy.add(Double.parseDouble(previousRaiseByTemp.replace(",", ""))/total);
							//}
					
							if(seat4==true&&seat6==true)
								outcome.add(0);
							else if(seat4==false&&seat6==true)
								outcome.add(-1);
							else if(seat4==true&&seat6==false)
								outcome.add(1); 
					}
						
					inputTemp.clear();
					flopCheck= false;
					seat4=false;
					seat6=false;
					previousRaiseTemp=true;
				}
				
				if(i.contains("Seat 4:"))
				{
					if(i.contains("Total ("))
						tempVar= true;
				}
				
				if(i.contains(currentRound))
				{
					flopCheck=true;
					skip = false;
					flop=true;
					turn=false;
					previousRaiseTemp=false;
				}
				//System.out.println(i);
				while (sc2.hasNext()) {

					String j = sc2.next();
					
					if(i.substring(0, 9).equalsIgnoreCase("Seat 4 - "))
					{
						name= i.substring(9, 31);
						totalTemp=true;
					}				
					
					if(totalTemp==true){
						char sum="(".charAt(0);
						if(j.charAt(0) == sum)
							total=Double.parseDouble(j.substring(2).replace(",", ""));
						
						totalTemp=false;
					}

					if(tempVar==true && flopCheck==true && previous.equalsIgnoreCase("total"))
					{		
							if(j.charAt(0)=="(".charAt(0)){
								String temp = j.substring(2);
								temp = temp.substring(0, temp.length()-1);
								temp = temp.replace(",","");
								double[] profitTemp = {Double.parseDouble(temp)};
								profit.add(profitTemp);
							}	
					}
					
					
					if (j.equalsIgnoreCase(nextRound))
						turn=true;
					
					if(flop==true){
						
						if(turn==false)
						{
							if(j.equalsIgnoreCase(name))
								seat4Player=true;
							
							if(j.equalsIgnoreCase("checks") && seat4Player==true)
								raiseByTemp=0;
								
							if(previous.equalsIgnoreCase("calls") && seat4Player==true)
								raiseByTemp=Double.parseDouble(j.substring(1));
								
							if(previous.equalsIgnoreCase("to") && seat4Player==true){
								try  
								  {  
									raiseByTemp=Double.parseDouble(j.substring(1));  
								  }  
								  catch(NumberFormatException n)  
								  {  
								    
								  }  
								
								
								}
							
							if(previousRaiseTemp==true  && seat4Player==true)
							{
								//System.out.println(previous+" "+j);
								if(j.equalsIgnoreCase("checks")){
									previousRaiseByTemp="0";
								}else{
								previousRaiseByTemp = j.substring(1);}
							}
						}
					}
					
					if (skip == false) {//stores the flop cards in the input
						char firstBracket = "[".charAt(0);
						char secondBracket = "]".charAt(0);
						
						if (j.charAt(j.length() - 1) == secondBracket) 
						{	
							if(j.charAt(0) != firstBracket){
							inputTemp.add(j.substring(0,j.length()-1));
							if(currentRound=="FLOP"){
								skip = true;}
								gate = false;
							}
						}
						
						if (gate == true) 
						{
							if(count!=-1)
								inputTemp.add(j);
						}

						if (j.charAt(0) == firstBracket) 
						{
							if(currentRound=="FLOP")
							{
								count++;
								inputTemp.add(j.substring(1));
								gate = true;
							}
							else
							{
								if(j.charAt(j.length() - 1)==secondBracket)
								{
									inputTemp.add(j.substring(1,j.length()-1));
									skip = true;
								}
								else{
									count++;
									inputTemp.add(j.substring(1));
									gate = true;
								}
							}
						}
					}
					previous=j;
				}
				tempVar=false;
				seat4Player=false;
				skip = true;
				sc2.close();
			}
			//actualOutputs.clear();//delete this line if you want other output
			//actualOutputs=profit;//delete this line if you want other output
			
//			System.out.println("count " + count);
//			System.out.println("input size "+input.size());
//			System.out.println("RaiseBy " + raiseBy.size());
//			System.out.println("profit size "+profit.size());
//			System.out.println("outcome size "+outcome.size());

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
//		for(ArrayList<String> y:input){
//			System.out.println(y);
//		}
		
		DFO d = new DFO();
    	for(int u=0;u<input.size();u++)
    	{
    		ArrayList<Card> testTemp=new ArrayList<Card>();
    		for(String test2:input.get(u)){
    			Card a= new Card();
    			a.create(test2);
    			testTemp.add(a);
    		}
    		double temp2=d.calculate(testTemp);
    		ArrayList<Double> t = new ArrayList<Double>();
    		t.add(temp2);
    		if(choice==0)
    			t.add(raiseBy.get(u));
    		else if(choice==1)
    			t.add(previousRaiseBy.get(u));
    		inputs.add(t);	
    	}
		
    	if(choice==0){
    		for(int l=0; l<outcome.size();l++)
    		{
    			double r[] = new double[1];
    			r[0] = outcome.get(l);
    			actualOutputs.add(r);
    		}
    	}
    	
    	if(choice==1){
    		for(int l=0; l<outcome.size();l++){
    			double r[] = new double[1];
    			r[0] = raiseBy.get(l);
    			actualOutputs.add(r);
    		}
    	}
    }
}