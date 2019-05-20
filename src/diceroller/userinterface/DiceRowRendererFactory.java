package diceroller.userinterface;

import diceroller.mainpackage.DeckOfCards;
import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.GenericDice;
import diceroller.mainpackage.RPGDice;
import diceroller.mainpackage.SWSkillDice;
import diceroller.mainpackage.StarWarsDice;

public class DiceRowRendererFactory {

	public static DiceRowRenderer createRenderer(DiceSet set) throws Exception
	{
		if(set.getDice() instanceof StarWarsDice && !(set.getDice() instanceof SWSkillDice))
			return new DiceRowSW(set);
		if(set.getDice() instanceof SWSkillDice)
			return new DiceRowSWSkill(set);
		if(set.getDice() instanceof RPGDice)
			return new DiceRowRPG(set);
		if(set.getDice() instanceof GenericDice)
			return new DiceRowGeneric(set);
		if(set.getDice() instanceof DeckOfCards)
			return new DiceRowDeckRenderer(set);
		return null;
	}
}
