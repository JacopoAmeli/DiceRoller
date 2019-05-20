package diceroller.userinterface;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import diceroller.mainpackage.DiceSet;

public class DiceSetCellRenderer extends JLabel implements ListCellRenderer 
{
	  private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

	  public DiceSetCellRenderer() {
	    setOpaque(true);
	    setIconTextGap(12);
	    setHorizontalAlignment(SwingConstants.CENTER);
	  }

	  public Component getListCellRendererComponent(JList list, Object value,
	      int index, boolean isSelected, boolean cellHasFocus) {
	    DiceSet entry = (DiceSet) value;
	    setText(entry.getName());
	    if (isSelected) {
	      setBackground(HIGHLIGHT_COLOR);
	      setForeground(Color.white);
	    } else {
	      setBackground(Color.white);
	      setForeground(Color.black);
	    }
	    return this;
	  }
}