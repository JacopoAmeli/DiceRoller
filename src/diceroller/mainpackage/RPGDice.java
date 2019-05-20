package diceroller.mainpackage;

import java.util.ArrayList;

import diceroller.utils.Randomizer;

public class RPGDice implements DiceInterface {

	private ArrayList<Integer> possibleValues;
	private String name;
	private int maxValue;
	public RPGDice(int dicetype)
	{
		int i;
		//2 for d2, 4 for d4, 6 for d6..... 90 for %.
		if(dicetype==90)
		{
			name="d%";
			maxValue=90;
			possibleValues= new ArrayList<Integer>(10);
			for(i=0;i<9;i++)
			{
				//filled with 00,10,20,30,40,50,60,70,80,90.
				possibleValues.add(i*10);
			}
		}
		else
		{
			name="d"+dicetype;
			possibleValues=new ArrayList<Integer>(dicetype);
			maxValue=dicetype;
			for(i=0;i<dicetype;i++)
			{
				possibleValues.add(i+1);
			}
		}
	}
	public int getMaxValue()
	{
		return maxValue;
	}
	@Override
	public int roll() {
		return Randomizer.getRandomNumber(possibleValues);
		}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	// RPGDice are d2, d4, d6, d8, d10, d12, d20, d100, d%. 
	@Override
	public String rollString() {
		return ""+Randomizer.getRandomNumber(possibleValues);
	}
}
