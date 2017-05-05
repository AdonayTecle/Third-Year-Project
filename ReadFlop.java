
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class ReadFlop {

	public static void main(String[] args) throws IOException {

		File file = new File("FORMATTED_abs NLH handhq_1-OBFUSCATED");
		int count = -1;
		int count2 = 0;
		boolean seat4=false;
		boolean seat6=false;
		int four=0;
		int six=0;
		boolean flop=false;
		ArrayList<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> output = new ArrayList<Integer>();
		ArrayList<Integer> raiseBy = new ArrayList<Integer>();
		boolean seat4Player=false;
		boolean turn = true;
		int count3=0;
		String name="";
		try {

			Scanner sc = new Scanner(file);
			boolean skip = true;
			boolean skip2=false;
			boolean gate = false;
			while (sc.hasNextLine()) {
				String i = sc.nextLine();
				Scanner sc2 = new Scanner(i);
				while (sc2.hasNext()) {

					String j = sc2.next();
					
					
					if(i.substring(0, 9).equalsIgnoreCase("Seat 4 - "))
					{
						name= i.substring(9, 31);
						//System.out.println(name);
					}
					
					if(j.equalsIgnoreCase("stage"))
					{//check=false;
						if(skip2==true){
							if(seat4==true&&seat6==true)
							{
								//System.out.println(seat4+"  "+seat6);
								output.add(0);
							}
							else if(seat4==false&&seat6==true)
							{
								output.add(-1);
							}else if(seat4==true&&seat6==false)
							{
								output.add(1);
							}	 
						
						
					}
						four=0;
						six=0;
						seat4=false;
						seat6=false;
						skip2=false;}
//					if(j.equalsIgnoreCase("total"))
//					{check=true;}
//					
//					if(check==true)
//					{System.out.println(j);}
					
					if(j.equalsIgnoreCase("4:"))
					{
						four=1;
						//System.out.print(i.substring(3));
						
					}
					
					
					
					if(j.equalsIgnoreCase("6:"))
					{
						six=1;
					}
					//raiseBy.add(j);
					if(four==1 && j.equalsIgnoreCase("total"))
					{
						seat4=true;
					}
					
					if(six==1 && j.equalsIgnoreCase("total"))
					{
						seat6=true;
						//System.out.println("seat4:  "+seat4);
					}
					if (j.equalsIgnoreCase("flop")) {
						skip = false;
						flop=true;
						turn=false;
					}
					
					if (j.equalsIgnoreCase("turn")) {
					turn=true;
					}
					
					if(flop==true){
						
						if(turn==false)
						{
							
							if(j.equalsIgnoreCase(name))
							{seat4Player=true;
							//System.out.println(name);
							}
							//System.out.println("dubfhdhuehdjnfjknejnfjkrnejfjkrkjfnrekjnfjkhdhcjd");
							//System.out.println(seat4Player);
							if(raiseBy.size()<input.size()){
								
								if(j.equalsIgnoreCase("checks") && seat4Player==true){
									
									raiseBy.add(0);
								}
							
								if(j.equalsIgnoreCase("calls") && seat4Player==true){
								
									raiseBy.add(2);
								}
							
								if(j.equalsIgnoreCase("raises") && seat4Player==true){
								
									raiseBy.add(2);
								}
								
							}else{
								if(j.equalsIgnoreCase("checks") && seat4Player==true){
									
									raiseBy.set(raiseBy.size()-1, 0);
								}
							
								if(j.equalsIgnoreCase("calls") && seat4Player==true){
								
									raiseBy.set(raiseBy.size()-1, 2);
								}
							
								if(j.equalsIgnoreCase("raises") && seat4Player==true){
								
									raiseBy.set(raiseBy.size()-1, 2);
								}
								
							}
							
							
							
						
						}
					}
					
					
					
					if (skip == false) {
						char firstBracket = "[".charAt(0);
						char secondBracket = "]".charAt(0);

						if (j.charAt(j.length() - 1) == secondBracket) {
							// System.out.println(j.substring(0,j.length()-1));
							//System.out.print("uewhdhehduehiure " + input.get(0).add(j.substring(0, j.length() - 1)));
							// input.set(0,
							ArrayList<String> temp3 = new ArrayList<String>();
							temp3 = input.get(count);
							temp3.add(j.substring(0,j.length()-1));
						input.set(count,temp3);
							skip = true;
							gate = false;
							skip2=true;
							//System.out.println();
						}

						if (gate == true) {
							// System.out.print(j+" ");
							if(count!=-1){
								//System.out.print("edjeneb   "+count);
								ArrayList<String> temp2 = new ArrayList<String>();
								//System.out.println(input);
								//System.out.println(input.size());
								//System.out.println(count);
								
								//System.out.println("jfhweuhfdhewhgdf "+input.get(count));
								
								temp2 = input.get(count);
								temp2.add(j);
								input.set(count,temp2);
							}
							
						}

						if (j.charAt(0) == firstBracket) {
							count++;
							
							ArrayList<String> temp = new ArrayList<String>();
							temp.add(j.substring(1));
							//System.out.println("ehdebhdebhdbe "+j.substring(1));
							input.add(temp);
							
							//System.out.println(count);
							gate = true;
						}

						// System.out.println(j);
					}
				}
				
				seat4Player=false;
				if(seat4==false)
				{
					four=0;
				}
				
				if(seat6==false)
				{
					six=0;
				}
				skip = true;
				
				if(seat4==true&&seat6==true){
					System.out.print("DRAW       "+input.get(input.size()-1));
				}

			}
			System.out.println("count " + count);
			System.out.println("count2 " + count2);
			System.out.println("count3 " + count3);
			System.out.println("RaiseBy " + raiseBy.size());
			
			System.out.println(input);
			System.out.println(raiseBy);
			System.out.println(output);
//		for(ArrayList<String>b:input){
//				System.out.println(b);
//			
//		}//System.out.println(input.size());
		
		
//		for(int b:output){
//			System.out.println(b);	
//	}
//		
//		System.out.println(output.size());

//			for(int b:raiseBy){
//				System.out.println(b);	
//		}
			
//			System.out.println(raiseBy.size());
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
