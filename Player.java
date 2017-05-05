import java.util.ArrayList;
public class Player {
	private ArrayList<Card> hand = new ArrayList<Card>();//contains the two cards that the player has
	//private String name;//players name
	private double money=1000;//amount of money players starts with
	private double bet=0;//amount of money that the player has bet
	private int finalHand;
	private boolean active=true;
	private boolean check = false;
	public void dealt(Card hand)
	{
		this.hand.add(hand);
	}
	
//	public String getName()
//	{
//		return this.name;
//	}
	public ArrayList<Card>getHand()
	{
		return hand;
	}
	public int getFinalHand()
	{
		return finalHand;
	}
	public boolean getCheck()
	{
		return check;
	}
	
	public void setCheck(boolean check)
	{
		this.check = check;
	}
	public void setFinalHand(int num)
	{
		this.finalHand = num;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	public boolean getActive()//get the money the player has
	{
		return this.active;
	}
	public double getMoney()//get the money the player has
	{
		return this.money;
	}
	
	public double getBet()//gets the amount of money the player has bet so far
	{
		return this.bet;
	}
	
	public void raise(double raise)//updates the amount of money the player has and how much they have bet so far
	{
		this.money = this.money - raise; 
		this.bet = this.bet + raise;
	}
	
	public void setBet()
	{
		this.bet=0;
	}
	
	public void setMoney(int money)
	{
		this.money=this.money+money;
	}
	
	public void printHand()
	{
		for(Card card: hand)
		{
			System.out.println(card.getNumber()+" of "+card.getSuit());
		}
	}
}
