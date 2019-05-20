package diceroller.userinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import diceroller.mainpackage.DiceSetList;
import diceroller.userinterface.optiondialogs.AddDiceSetListPane;

public class LeftPanel extends JPanel implements ActionListener {

	private DicePanel mainPanel;
	private JList list;
	private JButton addSetList;
	private JButton removeSetList;
	private JButton save;
	private JButton importButton;
	/*
	This is on the left side of dice panel. it handles the main list, cascades load and save, adds and removes dicesetlists.
	*/
	public LeftPanel(DicePanel mainPanel)
	{
		super();
		this.mainPanel=mainPanel;
		initialize();
	}
	private void initialize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		this.setLayout(new BorderLayout());
		
		list = new ListCustom(mainPanel);
		JPanel scrollPanel= new JPanel();
		scrollPanel.setLayout(new BorderLayout());
		scrollPanel.add(list,BorderLayout.CENTER);
		JScrollPane scroller = new JScrollPane(scrollPanel);
		scroller.setPreferredSize(scrollPanel.getPreferredSize());
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroller,BorderLayout.CENTER);

		
		JPanel menuPanel= new JPanel();
		menuPanel.setLayout(new GridLayout2(2,2));
		addSetList=new JButton("+");
		removeSetList=new JButton("-");
		save= new JButton();
		importButton= new JButton();
		importButton.setIcon(DicePictureGetter.getImportIcon());
		importButton.addActionListener(this);
		save.setIcon(DicePictureGetter.getSaveIcon());
		save.addActionListener(this);
		save.setPreferredSize(new Dimension(width/30,height/30));
		addSetList.addActionListener(this);
		addSetList.setPreferredSize(new Dimension(width/30,height/30));
		removeSetList.addActionListener(this);
		removeSetList.setPreferredSize(new Dimension(width/30,height/30));
		menuPanel.setPreferredSize(new Dimension(width/15,height/15));
		menuPanel.setMinimumSize(new Dimension(width/15,height/15));
		menuPanel.add(addSetList);
		menuPanel.add(removeSetList);
		menuPanel.add(save);
		menuPanel.add(importButton);
		
		this.add(menuPanel,BorderLayout.SOUTH);
	}
	public JList getList()
	{
		return list;
	}
	public DiceSetList getSelected()
	{
		return (DiceSetList)list.getSelectedValue();
	}
	public int getSelectedIndex()
	{
		return list.getSelectedIndex();
	}
	public void save()
	{
		mainPanel.save();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(addSetList))
		{
			// A new window opens asking from a drop down list if the user wants a default dice set or a generic one. if he selects the generic one,
			// a text field appears asking for a name. the window then closes and returns the chosen value of the combo box and a string with the display name
			AddDiceSetListPane pane= new AddDiceSetListPane();
			if(pane.displayGUI()==JOptionPane.YES_OPTION)
			{
				int k=0;
				DiceSetList selected = pane.getSelected();
				String newName=selected.getName().trim();
				for (DiceSetList i : mainPanel.getMainList())
				{
					if(i.getName().trim().startsWith(newName))
					{
						if(newName.endsWith(""+k))
						{
							newName=" "+newName.substring(0, newName.length()-2).trim()+" ";
						}
						k++;
						newName=newName+" "+k;
						
					}
					
				}
				selected.setName(newName);
				mainPanel.addDiceSetList(selected);
				list.setModel(new AbstractListModel() {
					ArrayList<DiceSetList> values = mainPanel.getMainList();
					public int getSize() {
						return values.size();
					}
					public Object getElementAt(int index) {
						return values.get(index);
					}
				});
				repaint();
				revalidate();
			}
		}
		else if (e.getSource().equals(removeSetList))
		{
			try{
				if(JOptionPane.showConfirmDialog(null,
		                   new JLabel("Delete "+mainPanel.getMainList().get(getSelectedIndex()).getName().trim()+" ?"),
		                    "Remove Dice Set ",
		                    JOptionPane.OK_CANCEL_OPTION,
		                    JOptionPane.PLAIN_MESSAGE)==JOptionPane.YES_OPTION)
				{
							mainPanel.removeSetList(getSelectedIndex());
				list.clearSelection();
					repaint();
				}
			}
			catch(Exception er)
			{
				er.printStackTrace();
			}
			
		}
		else if (e.getSource().equals(save))
		{
			save();
			DicePanel.saveFlag=false;
		}
		else if (e.getSource().equals(importButton))
		{
			FileDialog fd = new FileDialog((Frame)null, "Choose a file", FileDialog.LOAD);
			fd.setFile("*.xml");
			fd.setVisible(true);
			String filename=fd.getFile();
			if(filename!=null)
			{
				String path=fd.getDirectory()+filename;
				this.mainPanel.load(path);
			}
		}
	}
	
}
