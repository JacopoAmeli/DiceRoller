package diceroller.userinterface;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.DiceSetList;
import persistence.Loader;
import persistence.Saver;

public class DicePanel extends JScrollPane implements ListSelectionListener  {
	//container for the list, it automatically updates his right grid based on the current selected Dice Set on the list on his left 
	
	private ArrayList<DiceSetList> mainList;
	private ArrayList<RightPanel> rightPanels;
	private RightPanel currentRightPanel;
	private LeftPanel leftPanel;
	
	public static boolean saveFlag=false;
	
	public DicePanel(ArrayList<DiceSetList> mainList)
	{
		
		super();
		this.setFocusable(true);
		this.mainList=mainList;
		initialize();
	}
	private void initialize()
	{
		leftPanel = new LeftPanel(this);
		this.setRowHeaderView(leftPanel);
		
		//one panel per set
		rightPanels = new ArrayList<RightPanel>();
		for(DiceSetList i : mainList)
		{
			rightPanels.add(new RightPanel(this,i));
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{
		if(leftPanel.getSelected()!=null)
		{
			for(RightPanel i: rightPanels)
			{
				if(i.getSetList().equals(leftPanel.getSelected()))
				{
					currentRightPanel=i;
					this.setViewportView(currentRightPanel);
					repaint();
					revalidate();
					currentRightPanel.resetColumns();
					repaint();
					//revalidate();
					break;
				}

			}
		}
			
	}
	public ArrayList<DiceSetList> getMainList()
	{
		return mainList;
	}
	public void save()
	{
		for(RightPanel k: rightPanels)
		{
			k.save();
		}
		try {
			Saver.SaveXML(mainList);
			DicePanel.saveFlag=false;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	public void removeSetList(int i)
	{
		DiceSetList removed=mainList.remove(i);
		for(RightPanel k: rightPanels)
		{
			if(k.getSetList().equals(removed))
			{
				if(k.equals(currentRightPanel))
				{
					this.setViewportView(new JPanel());
					repaint();
				}
				rightPanels.remove(k);
				DicePanel.saveFlag=true;
				break;				
			}
		}
	}
	public void addDiceSetList(DiceSetList i)
	{
		mainList.add(i);
		rightPanels.add(new RightPanel(this,i));
		DicePanel.saveFlag=true;
	}
	public void load(String path)
	{
		try {
			mainList=Loader.LoadFrom(path);
			initialize();
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (URISyntaxException e) {
		
			e.printStackTrace();
		}		
	}
	public void removeFromSelected(DiceSet diceSet) 
	{
		leftPanel.getSelected().removeDiceSet(diceSet);
	}
	
	
}