package diceroller.mainpackage;

import java.util.ArrayList;

import diceroller.utils.Randomizer;

public class GenericDice implements DiceInterface {

	private  ArrayList<String> possibleValues;
	private String name;
	public GenericDice(ArrayList<String> possibleValues, String name)
	{
		this.possibleValues=possibleValues;
		this.name=name;
	}
	public ArrayList<String> getPossibleValues()
	{
		return possibleValues;
	}
	@Override
	public int roll()
	{
		String result= Randomizer.getRandomString(possibleValues);
		int resultInt;
		try
		{
			resultInt= Integer.parseInt(result);
		}
		catch(NumberFormatException e)
		{
			resultInt=0;
		}
		return resultInt;
	}
	public String rollString()
	{
		return Randomizer.getRandomString(possibleValues);
	}
	public String getName()
	{
		return name;
	}
	

}
