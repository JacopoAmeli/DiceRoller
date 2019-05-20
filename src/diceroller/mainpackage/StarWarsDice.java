package diceroller.mainpackage;

import java.util.ArrayList;

import diceroller.utils.Randomizer;

public class StarWarsDice implements DiceInterface {

	private  ArrayList<Integer> possibleValues;
	private String type;
	public StarWarsDice(String type)
	{
		this.type=type;
		int i,sizeDice=0;
		switch (type){
		case "Boost":
			possibleValues= new ArrayList<Integer>(6);
			sizeDice=6;
			break;
		case "Setback":
			possibleValues= new ArrayList<Integer>(6);
			sizeDice=6;
			break;
		case "Proficiency":
			possibleValues= new ArrayList<Integer>(12);
			sizeDice=12;
			break;
		case "Challenge":
			possibleValues= new ArrayList<Integer>(12);
			sizeDice=12;
			break;
		case "Force":
			possibleValues= new ArrayList<Integer>(12);
			sizeDice=12;
			break;
		case "Ability": 
			possibleValues= new ArrayList<Integer>(8);
			sizeDice=8;
			break;
		case "Difficulty":
			possibleValues= new ArrayList<Integer>(8);
			sizeDice=8;
			break;
		}
		for (i=0;i<sizeDice;i++)
		{
			possibleValues.add(i+1);
		}
	}
	@Override
	public int roll()
	{
		return Randomizer.getRandomNumber(possibleValues);
	}
	public String getName()
	{
		return type; //better if actual names are used....
	}
	@Override
	public String rollString() {
		return ""+Randomizer.getRandomNumber(possibleValues);
	}

	//Star Wars dice are green and purple d8, blue and black d6, yellow and red and white d12
}
