package diceroller.mainpackage;

public interface DiceInterface {

	/* TODO 
	 * Every dice is composed of:
	 * 1- A list of possible values. This can be described as a range, like 0-100, 1-10, or a list, like 1,3,5,7. 
	 * 2- A roll function that returns a random value
	 */
	public int roll();
	public String getName();
	public String rollString();
}
