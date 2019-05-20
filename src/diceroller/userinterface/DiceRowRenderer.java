package diceroller.userinterface;


import diceroller.mainpackage.DiceSet;

public interface DiceRowRenderer {

	public void roll();
	public DiceSet getDiceSet();
	public String getResultString();
	public void resetAll();
	public int getNum();
	public void setLogger(Logger logger);
	public void setMainPanel(RightPanel mainPanel);
	public void save();
	public void setEditSize(int width);
	public int getEditSize();
	public void editSizeChanged();
	}
