package diceroller.utils;


public class SWRolledDice {

	private int advantages=0;
	private int successes=0;
	private int disadvantages=0;
	private int failures=0;
	private int triumphs=0;
	private int despairs=0;
	private int lightforcepoints=0;
	private int darkforcepoints=0;
	
	public SWRolledDice()
	{
		
	}
	
	@Override
	public String toString()
	{
		String result="";
		if(advantages>0)
			result+="ADV: "+advantages+" ";
		if(successes>0)
			result+="SUCC: "+successes+" ";
		if(failures>0)
			result+="FAIL: "+failures+" ";
		if(disadvantages>0)
			result+="THREAT: "+disadvantages+" ";
		if(triumphs>0)
			result+="TRI: "+triumphs+" ";
		if(despairs>0)
			result+="DESP: "+despairs+" ";
		if(lightforcepoints>0)
			result+="LIGHT: "+lightforcepoints+" ";
		if(darkforcepoints>0)
			result+="DARK: "+darkforcepoints+" ";
		return result;
	}

	public int getAdvantages() {
		return advantages;
	}

	public void setAdvantages(int advantages) {
		this.advantages = advantages;
	}

	public int getSuccesses() {
		return successes;
	}

	public void setSuccesses(int successes) {
		this.successes = successes;
	}

	public int getDisadvantages() {
		return disadvantages;
	}

	public void setDisadvantages(int disadvantages) {
		this.disadvantages = disadvantages;
	}

	public int getFailures() {
		return failures;
	}

	public void setFailures(int failures) {
		this.failures = failures;
	}

	public int getDespairs() {
		return despairs;
	}

	public void setDespairs(int despairs) {
		this.despairs = despairs;
	}

	public int getTriumphs() {
		return triumphs;
	}

	public void setTriumphs(int triumphs) {
		this.triumphs = triumphs;
	}

	public int getLightforcepoints() {
		return lightforcepoints;
	}

	public void setLightforcepoints(int lightforcepoints) {
		this.lightforcepoints = lightforcepoints;
	}

	public int getDarkforcepoints() {
		return darkforcepoints;
	}

	public void setDarkforcepoints(int darkforcepoints) {
		this.darkforcepoints = darkforcepoints;
	}

}
