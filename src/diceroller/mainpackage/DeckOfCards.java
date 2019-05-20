package diceroller.mainpackage;

import java.util.ArrayList;

import diceroller.utils.DefaultSetsFactory;
import diceroller.utils.Randomizer;

public class DeckOfCards implements DiceInterface {

	private boolean containsJokers=false;
	private ArrayList<Card> cards;
	
	public DeckOfCards(boolean containsJokers)
	{
		this.containsJokers=containsJokers;
		reset();
	}
	@Override
	public int roll() {
		return 0;
	}
	public int cardsLeft()
	{
		return cards.size();
	}
	public Card draw()
	{
		Card result;
		if (cards.size()>0)
			result = cards.remove(Randomizer.randomNum(cards.size()));
		else
			result=null;
		return result;
	}
	public void shuffle()
	{
		int i;
		ArrayList<Card> temp = new ArrayList<Card>();
		for ( i=0; i<cards.size();i++)
		{
			temp.add(cards.get(Randomizer.randomNum(cards.size())));
		}
		cards=temp;
	}
	public void reset()
	{
		cards=DefaultSetsFactory.createDefaultDeck();
		if(containsJokers)
		{
			cards.add(new Card("Red","Joker"));
			cards.add(new Card("Black","Joker"));
		}
		shuffle();
	}
	public void setJokers(boolean containsJokers)
	{
		if(this.containsJokers!=containsJokers)
		{
			this.containsJokers=containsJokers;
			this.reset();
		}
		this.containsJokers=containsJokers;
		
	}
	public boolean hasJokers()
	{
		return containsJokers;
	}
	@Override
	public String getName() {
		return null;
	}

	@Override
	public String rollString() {
		return null;
	}

}
