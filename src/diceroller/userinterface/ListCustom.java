package diceroller.userinterface;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicListUI;

import diceroller.mainpackage.DiceSetList;

public class ListCustom<E> extends JList<E> {

	public ListCustom(DicePanel mainPanel)
	{
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		BasicListUI listUI= new BasicListUI();
		this.addMouseListener(new ListMouseListener(mainPanel,this));
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setFixedCellHeight(height/15);
		this.setFixedCellWidth(width/8);
		this.setModel(new AbstractListModel() {
			ArrayList<DiceSetList> values = mainPanel.getMainList();
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		this.setCellRenderer(new DiceSetListCellRenderer());
		this.addListSelectionListener(mainPanel);
	}
	@Override
    public int locationToIndex(Point location) 
    {
        int index = super.locationToIndex(location);
       
        if (index != -1 && !getCellBounds(index, index).contains(location))
        {
            return -1;
        }
        else 
        {
            return index;
        }
    }
	
	@Override
	protected void processMouseMotionEvent(MouseEvent e) {
	    if(MouseEvent.MOUSE_DRAGGED != e.getID()) {
	        super.processMouseMotionEvent(e);
	    }
	}
	
}
