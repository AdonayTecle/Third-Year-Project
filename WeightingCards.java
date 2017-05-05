import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WeightingCards {

	public int evaluate(int n, ArrayList<Card>list)
	{
		int num=0;
		  switch (n) {
          case 1:  num= pair(list);
                   break;
          case 2:  num= twoPairs(list);
                   break;
          case 3:  num= threeOfAKind(list);
                   break;
          case 4:  num= straight(list);
                   break;
          case 5:  num= flush(list);
                   break;
          case 6:  num= fullHouse(list);
                   break;
          case 7:  num= fourOfAKind(list);
                   break;
          case 8:  num= straightFlush(list);
                   break;
          case 9:  num= royalFlush(list);
                   break;
          default: System.out.println(" ");
                   break;
      }
		  return num;

	}

	public int royalFlush(ArrayList<Card>community)//checks if hand is a royal flush or not
	{
		ArrayList<Card> cards= community;
		HashMap<String, ArrayList<Card>> check = new HashMap<String, ArrayList<Card>>();
		for(Card card: cards)
		{
			if(check.containsKey(card.getSuit()))
			{
				ArrayList<Card> temp = check.get(card.getSuit());
				temp.add(card);
				check.put(card.getSuit(), temp);
			}else{
				ArrayList<Card> temp = new ArrayList<Card>();
				temp.add(card);
				check.put(card.getSuit(), temp);
				continue;
			}
		}
		boolean found=false;
		for (Map.Entry<String, ArrayList<Card>> entry : check.entrySet()) {
		    ArrayList<Card> value = entry.getValue();
		    if(value.size()>=3){
		    	found=true;
		    	int count=0;
		    	for(Card card : value){
		    		if(value.size()-count<5)
		    			return 0;
		        	if(card.getNumber()!=10 && card.getNumber()!=11 && card.getNumber()!=12 && card.getNumber()!=13 && card.getNumber()!=1)
		        	{
		        		count++;
		        	}
		    	}
		    	break;
		    }
		}
		if(found)
			return 9;
		return 0;
	}
	
	public int straightFlush(ArrayList<Card>community)//checks if hand is a straight flush or not
	{
		ArrayList<Card> cards= community;
		HashMap<String, ArrayList<Card>> check = new HashMap<String, ArrayList<Card>>();
		for(Card card: cards)
		{
			if(check.containsKey(card.getSuit()))
			{
				ArrayList<Card> temp = check.get(card.getSuit());
				temp.add(card);
				check.put(card.getSuit(), temp);
			}else{
				ArrayList<Card> temp = new ArrayList<Card>();
				temp.add(card);
				check.put(card.getSuit(), temp);
				continue;
			}
		}
		
		int[] nums = new int[7];
		for (Map.Entry<String, ArrayList<Card>> entry : check.entrySet()) {
		    ArrayList<Card> value = entry.getValue();
		    if(value.size()>=3){
		    	for(int i=0; i<value.size();i++)
		    	{
		    		nums[i]=value.get(i).getNumber();
		    		}
		    }
		}
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int a:nums)
		{
			if(a!=0)
				temp.add(a);
		}
		if(temp.size()>=3){
			Arrays.sort(nums);
			int count=0;
			for(int i=0;i<nums.length-1;i++)
			{
				if(nums[i]+1==nums[i+1])
				{
					count++;
				}else{
					count=0;
					}
				
				if(count==4)
				{return 8;}
			}
		}
		return 0;
	}
	
	public int fourOfAKind(ArrayList<Card>community)//checks if hand is a four of a kind or not
	{
		ArrayList<Card> cards= community;
		HashMap<Integer, Integer> check = new HashMap<Integer, Integer>();
		for(Card card: cards)
		{
			if(check.containsKey(card.getNumber()))
			{
				check.put(card.getNumber(), check.get(card.getNumber())+1);
			}else{
				check.put(card.getNumber(), 1);
				continue;
			}
		}
		if(check.containsValue(2))
		{
				return 7;
		}
		return 0;
	}
	
	public int fullHouse(ArrayList<Card>community)//checks if hand is a full house or not
	{
		ArrayList<Card> cards= community;
		HashMap<Integer, Integer> check = new HashMap<Integer, Integer>();
		for(Card card: cards)
		{
			if(check.containsKey(card.getNumber()))
			{
				check.put(card.getNumber(), check.get(card.getNumber())+1);
			}else{
				check.put(card.getNumber(), 1);
				continue;
			}
		}
		if(check.containsValue(3))
		{
			
				return 6;
			
		}
		return 0;
		
	}
	
	public int flush(ArrayList<Card>community)//checks if hand is a flush or not
	{
		ArrayList<Card> cards= community;
		HashMap<String, Integer> check = new HashMap<String, Integer>();
		for(Card card: cards)
		{
			if(check.containsKey(card.getSuit()))
			{
				check.put(card.getSuit(), check.get(card.getSuit())+1);
			}else{
				check.put(card.getSuit(), 1);
				continue;
			}
		}
		if(check.containsValue(3))
		{
				return 5;
		}
		return 0;
	}
	
	public int straight(ArrayList<Card>community)//checks if hand is a straight or not	
	{
		ArrayList<Card> cards= community;
		int[] nums = new int[7];
		for(int i=0; i<cards.size();i++)
		{
			nums[i]=cards.get(i).getNumber();
		}
		Arrays.sort(nums);
		int count=0;
		for(int i=0;i<nums.length-1;i++)
		{
			if(nums[i]+1==nums[i+1])
			{
				count++;
			}else if(nums[i]==nums[i+1])
			{
			}
			else{
				count=0;}
			
			if(count==2)
			{return 4;}
		}
		
		return 0;
		
	}
	
	public int threeOfAKind(ArrayList<Card>community)//checks if hand is a three of kind
	{
		ArrayList<Card> cards= community;
		HashMap<Integer, Integer> check = new HashMap<Integer, Integer>();
		for(Card card: cards)
		{
			if(check.containsKey(card.getNumber()))
			{
				check.put(card.getNumber(), check.get(card.getNumber())+1);
			}else{
				check.put(card.getNumber(), 1);
				continue;
			}
		}
		if(check.containsValue(3))
		{
				return 3;
		}
		return 0;
	}
	
	public int twoPairs(ArrayList<Card>community)//checks if hand is a two pairs or not
	{
		ArrayList<Card> cards= community;
		HashMap<Integer, Integer> check = new HashMap<Integer, Integer>();
		for(Card card: cards)
		{
			if(check.containsKey(card.getNumber()))
			{
				check.put(card.getNumber(), check.get(card.getNumber())+1);
			}else{
				check.put(card.getNumber(), 1);
				continue;
			}
		}
		if(check.containsValue(2))
		{
			for(int k:check.keySet())
			{
				if(check.get(k)==2)
				{
					check.put(k, 0);
					break;
				}
			}
			
			if(check.containsValue(2))
			{
				return 2;
			}
		}
		return 0;
	}
	
	public int pair(ArrayList<Card>community)//checks if hand is a pair or not
	{
		ArrayList<Card> cards= community;
		HashMap<String, Integer> check = new HashMap<String, Integer>();
		for(Card card: cards)
		{
			if(check.containsKey(card.getSuit()))
			{
				check.put(card.getSuit(), check.get(card.getSuit())+1);
			}else{
				check.put(card.getSuit(), 1);
				continue;
			}
		}
		if(check.containsValue(2))
		{
				return 1;
		}
		return 0;
	}
	
	public boolean pairCheck(ArrayList<Card> pair)//checks if hole cards are a pair or not
	{
		if(pair.get(0).getNumber()==pair.get(1).getNumber())
		{
			return true;
		}
		return false;
	}
	
	public void finalHand(int n)
	{
		switch (n) {
		case 0:  System.out.print("high card");
        		break;
        case 1:  System.out.print("pair");
                 break;
        case 2:  System.out.print("Two pair");
                 break;
        case 3:  System.out.print("Three of a kind");
                 break;
        case 4:  System.out.print("straight");
                 break;
        case 5:  System.out.print("flush");
                 break;
        case 6:  System.out.print("full house");
                 break;
        case 7:  System.out.print("four of a kind");
                 break;
        case 8:  System.out.println("straight flush");
                 break;
        case 9:  System.out.println("royal flush");
                 break;
        default: System.out.println(" ");
                 break;
		}
	}
}
