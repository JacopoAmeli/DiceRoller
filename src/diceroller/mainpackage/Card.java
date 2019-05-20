package diceroller.mainpackage;

public class Card {
	
	private String Suit;
	private String num;
	
	public Card(String suit, String num)
	{
		this.num=num;
		this.Suit=suit;
	}
	
	public String getSuit()
	{
		return Suit;
	}
	
	public String getNum()
	{
		return num;
	}

}
