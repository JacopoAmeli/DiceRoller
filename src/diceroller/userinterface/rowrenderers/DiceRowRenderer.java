package diceroller.userinterface.rowrenderers;


import diceroller.mainpackage.DiceSet;
import diceroller.userinterface.Logger;
import diceroller.userinterface.RightPanel;

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
