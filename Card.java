
public class Card {
	private String suit;//the suit of the card
	private int number;//the number value of the card
	private String img;//stores the path of the image of the card
	
	public Card(String suit, int num, String img)
	{
		this.suit = suit;
		this.number = num;
		this.img = img;
	}
	public Card(){}
	
	public void setSuit(String suit)
	{
		this.suit = suit;
	}
	
	public void setNumber(int num)
	{
		this.number = num;
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
	public String getSuit()//get the suit of the card
	{
		return this.suit;
	}
	
	public String getImg()//get the path of the image of the card
	{
		return this.img;
	}
	
	public int getNumber()//gets the number value of the card
	{
		return this.number;
	}
	
	public void create(String cardName)
	{
		String name= String.valueOf(cardName.charAt(cardName.length()-1));
		
		String num1=cardName.substring(0, cardName.length()-1);
		int num;
		if(num1.equalsIgnoreCase("k"))//if the card is a king assign it the value 13
			num=13;
		else if(num1.equalsIgnoreCase("a"))//if the card is a ace assign it the value 1
			num=1;
		else if(num1.equalsIgnoreCase("j"))//if the card is a jack assign it the value 11
			num=11;
		else if(num1.equalsIgnoreCase("q"))//if the card is a queen assign it the value 12
			num=12;
		else
			num = Integer.parseInt(cardName.substring(0, cardName.length()-1));
		
		this.number=num;
		this.img="img/"+this.number+"_of_"+this.suit+".png";
		if(name=="c")
			this.suit="club";
		else if(name=="d")
			this.suit="diamond";
		else if(name=="h")
			this.suit="heart";
		else if(name=="s")
			this.suit="spead";
		
	}
}
