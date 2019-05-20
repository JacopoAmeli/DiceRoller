package diceroller.userinterface;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class ListMouseListener extends MouseAdapter implements ActionListener {

	private DicePanel mainPanel;
	private JList list;
	public ListMouseListener(DicePanel mainPanel,JList list)
	{
		this.mainPanel=mainPanel;
		this.list=list;
	}
	@Override
    public void mouseClicked(MouseEvent e) {
        JList list = (JList) e.getSource();
       
        if (list.getSelectedIndex()==-1 && list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown()
                && !isMenuShortcutKeyDown(e)) {
              			list.clearSelection();
        }
        else if (list.locationToIndex(e.getPoint())!=-1)
        {
        	
        	list.setSelectedIndex(list.locationToIndex(e.getPoint()));
        	if(e.isPopupTrigger() || e.getButton()==MouseEvent.BUTTON2)
            {
            	JPopupMenu contextMenu =new JPopupMenu();
            	JMenuItem renameItem= new JMenuItem("Rename Dice Group");
            	renameItem.addActionListener(this);
            	renameItem.setIcon(DicePictureGetter.getRenameIcon());
            	JMenuItem removeItem = new JMenuItem("Remove Dice Group");
            	removeItem.addActionListener(this);
            	removeItem.setIcon(DicePictureGetter.getDeleteIcon());
            	contextMenu.add(renameItem);
            	contextMenu.add(removeItem);
            	contextMenu.show(e.getComponent(), e.getX(), e.getY());
            }        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        JList list = (JList) e.getSource();
        if (list.getSelectedIndex()==-1 && list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown()
                && !isMenuShortcutKeyDown(e)) {
              			list.clearSelection();
        }
        else if (list.locationToIndex(e.getPoint())!=-1)
        {
        	
        	list.setSelectedIndex(list.locationToIndex(e.getPoint()));
        	if(e.isPopupTrigger() || e.getButton()==MouseEvent.BUTTON2)
            {
            	JPopupMenu contextMenu =new JPopupMenu();
            	JMenuItem renameItem= new JMenuItem("Rename Dice Group");
            	renameItem.addActionListener(this);
            	renameItem.setIcon(DicePictureGetter.getRenameIcon());
            	JMenuItem removeItem = new JMenuItem("Remove Dice Group");
            	removeItem.addActionListener(this);
            	removeItem.setIcon(DicePictureGetter.getDeleteIcon());
            	contextMenu.add(renameItem);
            	contextMenu.add(removeItem);
            	contextMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        JList list = (JList) e.getSource();
        if (list.getSelectedIndex()==-1 && list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown()
                && !isMenuShortcutKeyDown(e)) {
              			list.clearSelection();
        }
        else if (list.locationToIndex(e.getPoint())!=-1)
        {
        	
        	list.setSelectedIndex(list.locationToIndex(e.getPoint()));
        	 if(e.isPopupTrigger() || e.getButton()==MouseEvent.BUTTON2)
             {
             	JPopupMenu contextMenu =new JPopupMenu();
             	JMenuItem renameItem= new JMenuItem("Rename Dice Group");
             	renameItem.addActionListener(this);
             	renameItem.setIcon(DicePictureGetter.getRenameIcon());
             	JMenuItem removeItem = new JMenuItem("Remove Dice Group");
             	removeItem.addActionListener(this);
             	removeItem.setIcon(DicePictureGetter.getDeleteIcon());
             	contextMenu.add(renameItem);
             	contextMenu.add(removeItem);
             	contextMenu.show(e.getComponent(), e.getX(), e.getY());
             }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    	 JList list = (JList) e.getSource();
         if (list.getSelectedIndex()==-1 && list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown()
                 && !isMenuShortcutKeyDown(e)) {
               			list.clearSelection();
         }
    }
    
    private boolean isMenuShortcutKeyDown(InputEvent event) {
        return (event.getModifiers() & Toolkit.getDefaultToolkit()
                .getMenuShortcutKeyMask()) != 0;
    }
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (((JMenuItem)e.getSource()).getText().startsWith("Rename"))
		{
			String newName = JOptionPane.showInputDialog(list,"New name");
			if(newName!=null || !newName.isEmpty())
			{
				mainPanel.getMainList().get(list.getSelectedIndex()).setName(newName);
				DicePanel.saveFlag=true;
				list.revalidate();
				list.repaint();
			}
		}
		else
		{
			System.out.println("Remove");
			mainPanel.removeSetList(list.getSelectedIndex());
			list.clearSelection();
			list.repaint();
		}
	}
}
