package diceroller.utils;

import java.util.ArrayList;
import java.util.List;

import diceroller.mainpackage.Card;
import diceroller.mainpackage.DeckOfCards;
import diceroller.mainpackage.DiceInterface;
import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.DiceSetList;
import diceroller.mainpackage.RPGDice;
import diceroller.mainpackage.SWSkillDice;
import diceroller.mainpackage.StarWarsDice;

public class DefaultSetsFactory {

	public static String RPGSet=" Standard RPG set ";
	public static String SWSet=" Star Wars FFG set ";
	public static String GenericSet=" New Set ";
	public static String DeckOfCardSet=" Deck Of Cards  ";
	public static List<String> defaultSets()
	{
		ArrayList<String> result = new ArrayList<String>();
		result.add(RPGSet);
		result.add(SWSet);
		result.add(GenericSet);
		result.add(DeckOfCardSet);
		return result;
	}
	public static DiceSetList createFromString(String str)
	{
		if(str.equals(RPGSet))
			return createRPGDefaultList();
		if (str.equals(SWSet))
			return createSWDefaultList();
		if (str.equals(GenericSet))
			return createGenericList();
		if (str.equals(DeckOfCardSet))
			return createCthulhuDefaultList();
		return null;
	}
	public static DiceSetList createCthulhuDefaultList()
	{
		DeckOfCards deck= new DeckOfCards(true);
		DiceSetList result = new DiceSetList();
		result.setName(DeckOfCardSet);
		DiceSet set = new DiceSet(deck);
		set.setName("Deck");
		result.addDiceSet(set);
		return result;
	}
	public static ArrayList<Card> createDefaultDeck()
	{
		ArrayList<Card> result= new ArrayList<Card>();
		int i;
		for (i=2;i<11;i++)
		{
			result.add(new Card("Clubs",""+i));
			result.add(new Card("Diamonds",""+i));
			result.add(new Card("Hearts",""+i));
			result.add(new Card("Spades",""+i));
		}
		result.add(new Card("Clubs","Ace"));
		result.add(new Card("Diamonds","Ace"));
		result.add(new Card("Hearts","Ace"));
		result.add(new Card("Spades","Ace"));
		
		result.add(new Card("Clubs","King"));
		result.add(new Card("Diamonds","King"));
		result.add(new Card("Hearts","King"));
		result.add(new Card("Spades","King"));
		
		result.add(new Card("Clubs","Queen"));
		result.add(new Card("Diamonds","Queen"));
		result.add(new Card("Hearts","Queen"));
		result.add(new Card("Spades","Queen"));
		
		result.add(new Card("Clubs","Jack"));
		result.add(new Card("Diamonds","Jack"));
		result.add(new Card("Hearts","Jack"));
		result.add(new Card("Spades","Jack"));
		return result;
	}
	public static DiceSetList createFateDefaultList()
	{
		return null;
	}
	public static DiceSetList createGenericList()
	{
		DiceSetList genericList= new DiceSetList();

		
		genericList.setName(" Generic ");
		return genericList;
	}
	public static DiceSetList createRPGDefaultList()
	{
		
			DiceSetList RPGList = new DiceSetList();
			
			DiceInterface tempDice= new RPGDice(2);
			DiceSet tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
			
			tempDice= new RPGDice(4);
			tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
			
			tempDice= new RPGDice(6);
			tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
			
			tempDice= new RPGDice(8);
			tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
			
			tempDice= new RPGDice(10);
			tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
			
			tempDice= new RPGDice(12);
			tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
			
			tempDice= new RPGDice(20);
			tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
			
			tempDice= new RPGDice(100);
			tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
			
			tempDice= new RPGDice(90);
			tempSet= new DiceSet(tempDice);
			tempSet.setRollMode("Total");
			RPGList.addDiceSet(tempSet);
		
		RPGList.setName("   RPG Set   ");
		return RPGList;
	}
	public static DiceSetList createSWDefaultList()
	{
		
			DiceSetList SWList = new DiceSetList();
			
			DiceInterface tempDice= new StarWarsDice("Boost");
			DiceSet tempSet= new DiceSet(tempDice);
			SWList.addDiceSet(tempSet);
			
			tempDice= new StarWarsDice("Setback");
			tempSet= new DiceSet(tempDice);
			SWList.addDiceSet(tempSet);
			
			tempDice= new StarWarsDice("Proficiency");
			tempSet= new DiceSet(tempDice);
			SWList.addDiceSet(tempSet);
			
			tempDice= new StarWarsDice("Challenge");
			tempSet= new DiceSet(tempDice);
			SWList.addDiceSet(tempSet);
			
			tempDice= new StarWarsDice("Force");
			tempSet= new DiceSet(tempDice);
			SWList.addDiceSet(tempSet);
			
			tempDice= new StarWarsDice("Ability");
			tempSet= new DiceSet(tempDice);
			SWList.addDiceSet(tempSet);
			
			tempDice= new StarWarsDice("Difficulty");
			tempSet= new DiceSet(tempDice);
			SWList.addDiceSet(tempSet);
			
			tempDice=new SWSkillDice("Skill");
			tempSet=new DiceSet(tempDice);
			SWList.addDiceSet(tempSet);
		
		SWList.setName("   SW Set   ");
		return SWList;
	}
	public static ArrayList<DiceSetList> getAllDefaults()
	{
		ArrayList<DiceSetList> result= new ArrayList<DiceSetList>();
		result.add(createRPGDefaultList());
		result.add(createSWDefaultList());
		result.add(createGenericList());
		result.add(createCthulhuDefaultList());
		return result;
	}
	public static ArrayList<DiceSetList> getAllDefaultsInit()
	{
		ArrayList<DiceSetList> result= new ArrayList<DiceSetList>();
		result.add(createRPGDefaultList());
		result.add(createSWDefaultList());
		result.add(createCthulhuDefaultList());
		return result;
	}
}
