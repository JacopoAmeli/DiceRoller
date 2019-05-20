package diceroller.mainpackage;

import java.util.ArrayList;

public class DiceSetList {
	
	private ArrayList<DiceSet> diceSets;
	private String name;
	
	public DiceSetList()
	{
		diceSets= new ArrayList<DiceSet>();
		name="";
	}
	public DiceSetList(String name)
	{
		diceSets= new ArrayList<DiceSet>();
		this.name=name;
	}
	public void addDiceSet(DiceSet set)
	{
		diceSets.add(set);
	}
	public DiceSet getDiceSet(int i)
	{
		return diceSets.get(i);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize()
	{
		return diceSets.size();
	}
	public void removeDiceSet(DiceSet diceSet) {
		diceSets.remove(diceSet);
		
	}

}
