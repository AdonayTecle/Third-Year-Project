import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener {
	Deck deck = new Deck();

    JButton fold = new JButton("Fold");// creates a button called guess
    JButton check = new JButton("Check");
    JButton raise = new JButton("Raise");
    JButton call = new JButton("Call");
    //JButton showCards = new JButton("showCards");
    JPanel a_i_HoleCards = new JPanel();
    static JLabel[] a_i_card;
    JPanel opponentHoleCards = new JPanel();
    JLabel[] opponentCard;
    JPanel communityCards = new JPanel();
    JPanel community;
    static JLabel[] communityCard;
    static JLabel pot;
    static double potAmount=0;
    static JLabel a_i_Chips;
    static double a_i_ChipsAmount=1569.70;
    static JLabel opponentChips;
    static double opponentChipsAmount=1753.07;
    int raisedBy;
    static ArrayList<Player> playersIn = new ArrayList<Player>();//contains all the players still playing
    static ArrayList<Player> playersOut = new ArrayList<Player>();//contains all the players that are out of the game
    static Player ai = new Player();
    static Player opponent = new Player();
    static int round=0;
    static boolean turn;
    static int littleBlind;
    static int bigBlind;
    Color bl2 = new Color(227, 192, 237);//128,128,128
    static String emotions[] = {"neutral", "anger", "disgust", "happy", "surprise"} ;
    static ImageIcon[] icon;
    static ArrayList<ArrayList<Double>> inputs = new ArrayList<ArrayList<Double>>();
    static ArrayList<double[]> actualOutputs = new ArrayList<double[]>();
    static ArrayList<double[]> predictedOutputs = new ArrayList<double[]>();
    static NeuralNetwork network;
    static int inRoundTurns = 0;
    static ArrayList<Card> allCommunityCards = new ArrayList<Card>();
    static int emotion;
    static double prediction;
    public GUI(int h, int w) throws IOException {
    	
    	icon = new ImageIcon[4];
    	for(int i=0; i<4;i++)
    	{
    		BufferedImage img=ImageIO.read(new File(deck.getCard().getImg()));
    		icon[i]=new ImageIcon(img);
    	}
    	
    	JPanel a = new JPanel();
    	JPanel b = new JPanel();        
    	JPanel d = new JPanel();
    	
        opponentCard = new JLabel[2];
        opponentCard[0]= new JLabel("", JLabel.LEFT);
        opponentCard[1]= new JLabel("", JLabel.LEFT);
        opponentCard[0].setIcon(icon[0]);
        opponentCard[1].setIcon(icon[1]);
        opponentHoleCards.add(opponentCard[0]);
        opponentHoleCards.add(opponentCard[1]);
        opponentHoleCards.setBackground(bl2);
        
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(opponentHoleCards);
        opponentChips =new JLabel("Chips:", JLabel.CENTER);
        bottomPanel.add(opponentChips);
        bottomPanel.setBackground(bl2);
        d.add(bottomPanel);
        d.setBackground(bl2);
        
        
    	JPanel optionsPanel = new JPanel();
    	optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        optionsPanel.setLayout(new GridLayout(1, 3));
        optionsPanel.add(fold);
        optionsPanel.add(check);
        optionsPanel.add(call);
        optionsPanel.add(raise);
        optionsPanel.setBackground(bl2);
        d.add(optionsPanel);
        d.setVisible(false);
        add(d,"South");
        
        
        a_i_card = new JLabel[2];
        a_i_card[0]= new JLabel("", JLabel.LEFT);
        a_i_card[1]= new JLabel("", JLabel.LEFT);
        BufferedImage blankImg=ImageIO.read(new File("img/blank.png"));
    	ImageIcon blankIcon=new ImageIcon(blankImg);
    	a_i_card[0].setIcon(blankIcon);
    	a_i_card[1].setIcon(blankIcon);
        
        a_i_HoleCards.add(a_i_card[0]);
        a_i_HoleCards.add(a_i_card[1]);
        //a_i_HoleCards.add(showCards);
        a_i_HoleCards.setBackground(bl2);
        
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topPanel.setLayout(new GridLayout(1, 3));
        topPanel.add(a_i_HoleCards);
        topPanel.setBackground(bl2);
        a_i_Chips = new JLabel("Chips:", JLabel.CENTER);
        topPanel.add(a_i_Chips);
        a.add(topPanel);
        a.setBackground(bl2);
         
        add(a,"North");
        
        
        communityCard = new JLabel[5];
        for(int k=0; k<5;k++)
        	communityCard[k]= new JLabel("", JLabel.CENTER);
        for(int j=0; j<5; j++)
        {
        	allCommunityCards.add(deck.getCard());
        	BufferedImage img=ImageIO.read(new File(allCommunityCards.get(j).getImg()));
        	ImageIcon communityIcon=new ImageIcon(img);
        	communityCard[j].setIcon(communityIcon);
        	communityCard[j].setVisible(false);
        }
        for(int i=0;i<5;i++)
        	communityCards.add(communityCard[i]);
        community = new JPanel();
        community.setLayout(new FlowLayout());
        community.add(communityCards);
        pot = new JLabel("Pot:", JLabel.CENTER);
        community.add(pot);
        b.add(community);
        b.setVisible(false);
        this.add(b,"Center");
        
        
        //this.setBackground(Color.ORANGE);
         Random random = new Random();
         double ran=random.nextDouble();
        if(ran>0.5)
        {
        	 littleBlind=5;//HAVE TO MAKE THE BLIND VISSIBLE TO THE PLAYER
        	 ai.raise(littleBlind);
 			setNumberOfChips("a_i", littleBlind);
     		boolean blind;
     		do{
     			blind=true;
     		try{
     			bigBlind = Integer.parseInt(JOptionPane.showInputDialog("The small blind is £5. Enter the big blind for the game:"));
     		}catch(InputMismatchException |  NumberFormatException ex)
     		{
     			blind=false;
     		}
     		
     		}while(blind==false);
     		opponent.raise(bigBlind);
			setNumberOfChips("opponent", bigBlind);
        	turn=true;
        }
        else
        {	
     		boolean blind;
     		do{
     			blind=true;
     		try{
     			littleBlind = Integer.parseInt(JOptionPane.showInputDialog("Enter the small blind for the game:"));
     		}catch(InputMismatchException |  NumberFormatException ex)
     		{
     			blind=false;
     		}
     		
     		}while(blind==false);
     		opponent.raise(littleBlind);
			setNumberOfChips("opponent", littleBlind);
			
     		bigBlind=littleBlind*2;
     		ai.raise(bigBlind);
			setNumberOfChips("a_i", bigBlind);
			
     		turn=false;
        }
        setNumberOfChips("pot", opponent.getBet()+ai.getBet());
       
		
		d.setVisible(true);
		a.setVisible(true);
		b.setVisible(true);
		
		 setDefaultCloseOperation(3);
	        setTitle("Texas hold'em AI");
	        setMinimumSize(new Dimension(w * 50, h * 50));
	        pack();
	        setVisible(true);
	        fold.addActionListener(this);
	        check.addActionListener(this);
	        raise.addActionListener(this);
	        call.addActionListener(this);
	       //showCards.addActionListener(this);
    }

    public static void setNumberOfChips(String check, double chips)
    {
    	if(check.equalsIgnoreCase("a_i"))
    	{
    		//a_i_ChipsAmount = a_i_ChipsAmount - chips;
    		a_i_Chips.setText("Chips: "+Double.toString(ai.getMoney()));
    	}else if(check.equalsIgnoreCase("opponent")){
    		//opponentChipsAmount = opponentChipsAmount - chips;
    		opponentChips.setText("Chips: "+Double.toString(opponent.getMoney()));
    	}
    	else if(check.equalsIgnoreCase("pot"))
    	{
    		//potAmount = chips;
    		pot.setText("Pot: "+ Double.toString(chips));
    	}
    }
    
    public static void trainNetwork(int choice, String currentRound, String nextRound){
    	ReadData rd = new ReadData("FORMATTED_abs NLH handhq-OBFUSCATED.txt", choice, currentRound, nextRound);
    	inputs = rd.inputs;
    	actualOutputs = rd.actualOutputs;
//    	System.out.println("actualOutputs size "+actualOutputs.size());
//    	System.out.println("actualOutputs size "+actualOutputs.size());
    	for(int i=0;i<actualOutputs.size()-1;i++){
    		double ab[] = {-1};
    		predictedOutputs.add(ab);	
    	}

    }
    
    public static void getPrediction(ArrayList<Double> testInput){
    	
    	prediction = network.getPrediction(testInput)[0];
    }
    public static void emotionDetection()
    {
    	try{
			 Process p = Runtime.getRuntime().exec("python classifier.py");
			 }catch(Exception e2){}
    	File file = new File("haha.txt");
    	Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	//System.out.println(emotions[Integer.parseInt(sc.next())]);
    	
    	emotion = Integer.parseInt(sc.next());
    }
    
    public void actionPerformed(ActionEvent e) {
    	boolean checkReponse= false;
    	//System.out.println(Thread.currentThread().getName());
    	
    	
    	if(ai.getActive()==true && opponent.getActive()==true && turn==false)
        {  
    		if(e.getSource()==raise)
    		{
    			boolean matchType;
        		do{
        			matchType=true;
        			try{
        				raisedBy = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount you want to raise by:"));
        			}catch(InputMismatchException |  NumberFormatException ex)
        			{
        				matchType=false;
        			}
        		
        		}while(matchType==false);
        		
        		if(raisedBy>opponent.getMoney())
        			opponent.raise(opponent.getMoney());
        		else
        			opponent.raise(raisedBy);
        		
        		setNumberOfChips("opponent", raisedBy);
        		turn=true;
        		inRoundTurns++;
    		}
    		else if(e.getSource()==check)
    		{
    			System.out.println("CHECK");
    			if(opponent.getBet()==ai.getBet()){
    				turn=true;
    				inRoundTurns++;
    			}
    			
    		}else if(e.getSource()==call)
    		{
    			if((ai.getBet()-opponent.getBet())>0)
    			{
    				double temp =ai.getBet()-opponent.getBet();
    				opponent.raise(temp);
    				setNumberOfChips("opponent", raisedBy);
    				turn=true;
    				inRoundTurns++;
    			}
    		}
    		else if(e.getSource()==fold)
    		{
    			opponent.setActive(false);//The opponent is no longer in the game
    			System.out.println("FOLD");
    		}
        }
    }

  
    public static void main(String[] args) throws IOException{
//        try {
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
    	 network = new NeuralNetwork(2, 4, 1);
         int maxRuns =5000;
         double minErrorCondition = 0.001;
         network.run(maxRuns, minErrorCondition);
    	 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception e) {
             System.out.println(e.toString());
         }
    	
        new GUI(10, 4);
        
		playersIn.add(ai);
		playersIn.add(opponent);
		String currentRound="";
		String nextRound="";
		boolean showDown=false;
		do{
//			if(turn==true){
//				System.out.println(Thread.currentThread().getName());
//			}
//			else if(turn==false)
//			{
//				System.out.println("false");
//			}
			
			if(ai.getActive()==true && opponent.getActive()==true && turn==true)
        	{  
				System.out.println("A.I - Round "+round);
				System.out.println("A.I - In Round Turns "+inRoundTurns);
    			//NEURAL NETWORK DECIDES MOVES
				double difference = opponent.getBet()-ai.getBet();
				if(round>0)
				{
					if(round==1)
						trainNetwork(1, "FLOP","TURN");
					else if(round==2)
						trainNetwork(1, "TURN","RIVER");
					else if(round==3)
						trainNetwork(1, "RIVER", "SHOW DOWN");
					
					ArrayList<Double> testInput=new ArrayList<Double>();
					DFO d = new DFO();
					double allCommunityCardsValue=d.calculate(allCommunityCards);
					testInput.add(allCommunityCardsValue);
					testInput.add(difference);	
					System.out.println(testInput);
					getPrediction(testInput);
					double raise = prediction;
					
					ArrayList<Double> testInput2=new ArrayList<Double>();
					if(round==1)
						trainNetwork(0, "FLOP","TURN");
					else if(round==2)
						trainNetwork(0, "TURN","RIVER");
					else if(round==3)
						trainNetwork(0,"RIVER", "SHOW DOWN");
					
					testInput2.add(allCommunityCardsValue);
					testInput2.add(raise);
					
					getPrediction(testInput);
					double result =  prediction;
					if(result ==1)
					{
						if(emotion<3)
							ai.raise(raise);
						else 
							ai.setActive(false);
					}
					else
					{
						if(emotion>=3)
							ai.setActive(false);
						else 
							ai.raise(raise);
					}
    				setNumberOfChips("a_i", raise);
				}else
				{
					ai.raise(difference);
				}
				
				inRoundTurns++;//increments the variable by one
        		turn=false;//change the who's turn it is from the A.I to the opponent 
        		
        	}
			
			if(ai.getBet()==opponent.getBet() && inRoundTurns>1)
	    	{
	    		round++;
	    		inRoundTurns=0;
	    		if(round==1)
	        	{
	    			//trainNetwork("FLOP", "TURN");
	        		communityCard[0].setVisible(true);
	        		communityCard[1].setVisible(true);
	        		communityCard[2].setVisible(true);
	        		currentRound="FLOP";
	        		nextRound="TURN";
	        		emotionDetection();
	        	}
	        	else if(round==2)
	        	{
	        		//trainNetwork("TURN", "RIVER");
	        		communityCard[3].setVisible(true);
	        		currentRound="TURN";
	        		nextRound="RIVER";
	        		emotionDetection();
	        	}
	        	else if(round==3)
	        	{
	        		//trainNetwork("RIVER", "SHOW DOWN");
	        		communityCard[4].setVisible(true);
	        		currentRound="RIVER";
	        		nextRound="SHOW DOWN";
	        		emotionDetection();
	        	}
	        	else if(round==4){
	        		showDown=true;
	        	}
	    	}
			
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(turn);
		}while(ai.getActive()==true && opponent.getActive()==true && showDown==false);
		a_i_card[0].setIcon(icon[2]);
    	a_i_card[1].setIcon(icon[3]);
    	if(ai.getActive()==false && opponent.getActive()==true && showDown==false)
    	{
    		System.out.println("YOU HAVE WON!!!!");
    	}else if(ai.getActive()==true && opponent.getActive()==false && showDown==false){
    		System.out.println("THE A.I HAS WON!!!");
    	}
    	else if(showDown==true && ai.getActive()==true && opponent.getActive()==true){
    		
    		///CALCULATE WHO HAS WON
    		
    	}
    	else{
    		System.out.println("THE GAME IS BROKEN");
    	}
		System.out.println("Game Over");
    }
}
