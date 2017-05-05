import java.util.ArrayList;
import java.util.Collections;
public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();
	private String[] suits={"hearts", "spades", "diamonds", "clubs"};
	public Deck()
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<13;j++)
			{
				deck.add(new Card(String.valueOf(suits[i].charAt(0)),(j+1), "img/"+(j+1)+"_of_"+suits[i]+".png"));
			}
		}
		
		Collections.shuffle(deck);//shuffles the cards in to random order
	}
	
	public ArrayList<Card> getDeck()//gets the deck
	{
		return this.deck;
	}
	
	public Card getCard()//gets the top card from the deck
	{
		return deck.remove(deck.size()-1);
	}
}
