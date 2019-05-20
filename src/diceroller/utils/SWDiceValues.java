package diceroller.utils;

import java.util.List;

import diceroller.mainpackage.StarWarsDice;

public class SWDiceValues {
	
	public static SWRolledDice translate(int i, StarWarsDice dice)
	{
		SWRolledDice result= new SWRolledDice();
		switch (dice.getName()){
		case "Boost": {
			switch(i){
			case 3: result.setAdvantages(2);
			break;
			case 4: result.setAdvantages(1);
			break;
			case 5: {
				result.setSuccesses(1);
				result.setAdvantages(1);
				break;
			}
			case 6: result.setSuccesses(1);
			break;
			}
		}
		break;
		case "Setback": {
			switch(i){
			case 3: result.setFailures(1);
			break;
			case 4:result.setFailures(1);
			break;
			case 5: result.setDisadvantages(1);
			break;
			case 6:  result.setDisadvantages(1);
			break;
			}
		}
		break;
		case "Proficiency": {
			switch(i){
			case 2: result.setSuccesses(1);
			break;
			case 3: result.setSuccesses(1);
			break;
			case 4:result.setSuccesses(2);
			break;
			case 5:result.setSuccesses(2);
			break;
			case 6:  result.setAdvantages(1);
			break;
			case 7:{
				result.setSuccesses(1);
				result.setAdvantages(1);
				break;
			}
			case 8: {
				result.setSuccesses(1);
				result.setAdvantages(1);
				break;
			}
			case 9: {
				result.setSuccesses(1);
				result.setAdvantages(1);
				break;
			}
			case 10: result.setAdvantages(2);
			break;
			case 11: result.setAdvantages(2);
			break;
			case 12: result.setTriumphs(1);
			break;
			}
		}
		break;
		case "Challenge": {
			switch(i){
			case 2: result.setFailures(1);
			break;
			case 3:result.setFailures(1);
			break;
			case 4:result.setFailures(2);
			break;
			case 5:result.setFailures(2);
			break;
			case 6:  result.setDisadvantages(1);
			break;
			case 7:result.setDisadvantages(1);
			break;
			case 8: {
				result.setFailures(1);
				result.setDisadvantages(1);
				break;
			}
			case 9: {
				result.setFailures(1);
				result.setDisadvantages(1);
				break;
			}
			case 10: result.setDisadvantages(2);
			break;
			case 11: result.setDisadvantages(2);
			break;
			case 12: result.setDespairs(1);
			break;
			}
		}
		break;
		case "Force": {
			switch(i){
			case 1:result.setDarkforcepoints(1);
			break;
			case 2:result.setDarkforcepoints(1);
			break;
			case 3: result.setDarkforcepoints(1);
			break;
			case 4:result.setDarkforcepoints(1);
			break;
			case 5: result.setDarkforcepoints(1);
			break;
			case 6:  result.setDarkforcepoints(1);
			break;
			case 7: result.setDarkforcepoints(2);
			break;
			case 8: result.setLightforcepoints(1);
			break;
			case 9:result.setLightforcepoints(1);
			break;
			case 10: result.setLightforcepoints(2);
			break;
			case 11: result.setLightforcepoints(2);
			break;
			case 12: result.setLightforcepoints(2);
			break;
			}
		}
		break;
		case "Ability": {
			switch(i){
			case 2: result.setSuccesses(1);
			break;
			case 3: result.setSuccesses(1);
			break;
			case 4: result.setSuccesses(2);
			break;
			case 5: result.setAdvantages(1);
			break;
			case 6:  result.setAdvantages(1);
			break;
			case 7: {
				result.setAdvantages(1);
				result.setSuccesses(1);
				break;
			}
			case 8: result.setAdvantages(2);
			break;
			}
		}
		break;
		case "Difficulty": {
			switch(i){
			case 2: result.setFailures(1);
			break;
			case 3: result.setFailures(2);
			break;
			case 4: result.setDisadvantages(1);
			break;
			case 5: result.setDisadvantages(1);
			break;
			case 6:  result.setDisadvantages(1);
			break;
			case 7: result.setDisadvantages(2);
			break;
			case 8: {
				result.setDisadvantages(1);
				result.setFailures(1);
				break;
			}
			}
		}
		break;
		}
		return result;
	}
	
	public static SWRolledDice total(List<Integer> rolls, List<StarWarsDice> dice)
	{
		SWRolledDice result= new SWRolledDice();
		SWRolledDice temp;
		int i;
		for (i=0; i<rolls.size();i++)
		{
			temp= translate(rolls.get(i), dice.get(i));
			result.setAdvantages(result.getAdvantages()+temp.getAdvantages());
			result.setDisadvantages(result.getDisadvantages()+temp.getDisadvantages());
			result.setSuccesses(result.getSuccesses()+temp.getSuccesses());
			result.setFailures(result.getFailures()+temp.getFailures());
			result.setDarkforcepoints(result.getDarkforcepoints()+temp.getDarkforcepoints());
			result.setDespairs(result.getDespairs()+temp.getDespairs());
			result.setLightforcepoints(result.getLightforcepoints()+temp.getLightforcepoints());
			result.setTriumphs(result.getTriumphs()+temp.getTriumphs());
		}
		int adv=result.getAdvantages()-result.getDisadvantages();
		int succ=result.getSuccesses()-result.getFailures();
		if(adv>=0)
		{
			result.setAdvantages(adv);
			result.setDisadvantages(0);
		}
		else
		{
			result.setAdvantages(0);
			result.setDisadvantages(-adv);
		}
		if(succ>=0)
		{
			result.setSuccesses(succ);
			result.setFailures(0);
		}
		else
		{
			result.setSuccesses(0);
			result.setFailures(-succ);
		}
		
		return result;
	}

}
