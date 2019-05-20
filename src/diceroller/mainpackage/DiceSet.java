package diceroller.mainpackage;

public class DiceSet {

	//this is an entry used in a diceSetList
	private DiceInterface dice;
	private String optionalName;
	private int num=0;
	private int ModTotal=0;
	private int ModMultiply=0;
	private String rollMode="";
	
	public DiceSet(DiceInterface dice, String name, int modifierTotal, int modifierMultiply, int num)
	{
		this.dice=dice;
		this.optionalName=name;
		this.ModMultiply=modifierMultiply;
		this.ModTotal=modifierTotal;
		this.num=num;
	}
	public DiceSet(DiceInterface dice, String name, int modifier)
	{
		this.dice=dice;
		this.optionalName=name;
		this.ModMultiply=modifier;
	}
	public DiceSet(DiceInterface dice, String name)
	{
		this.dice=dice;
		this.optionalName=name;
		this.ModMultiply=0;
	}
	public DiceSet(DiceInterface dice, int modifier)
	{
		this.dice=dice;
		this.optionalName=dice.getName();
		this.ModMultiply=modifier;
	}
	public DiceSet(DiceInterface dice)
	{
		this.dice=dice;
		this.optionalName=dice.getName();
		this.ModMultiply=0;
	}
	public DiceInterface getDice()
	{
		return dice;
	}
	public String getName()
	{
		return optionalName;
	}
	public void setName(String str)
	{
		this.optionalName=str;
	}
	public int getModTotal()
	{
		return ModTotal;
	}
	public int getModMultiply()
	{
		return ModMultiply;
	}
	public void setModTotal(int i)
	{
		this.ModTotal=i;
	}
	public void setModMultiply(int i)
	{
		this.ModMultiply=i;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String toString()
	{
		return optionalName;
	}
	public String getRollMode() {
		return rollMode;
	}
	public void setRollMode(String rollMode) 
	{
		this.rollMode=rollMode;
		
	}
}
