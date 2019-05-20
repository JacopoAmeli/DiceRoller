package diceroller.userinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import diceroller.mainpackage.Card;
import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.DiceSetList;
import diceroller.mainpackage.GenericDice;
import diceroller.mainpackage.StarWarsDice;
import diceroller.userinterface.optiondialogs.AddDiceSetPane;
import diceroller.userinterface.optiondialogs.AddGenericDicePane;
import diceroller.userinterface.rowrenderers.DiceRowDeckRenderer;
import diceroller.userinterface.rowrenderers.DiceRowGeneric;
import diceroller.userinterface.rowrenderers.DiceRowRPG;
import diceroller.userinterface.rowrenderers.DiceRowRenderer;
import diceroller.userinterface.rowrenderers.DiceRowRendererFactory;
import diceroller.userinterface.rowrenderers.DiceRowSW;
import diceroller.userinterface.rowrenderers.DiceRowSWSkill;
import diceroller.utils.DefaultSetsFactory;
import diceroller.utils.SWDiceValues;
import diceroller.utils.SWRolledDice;

public class RightPanel extends JPanel implements ActionListener{

	private DicePanel mainPanel;
	private DiceSetList setList;
	
	private ArrayList<DiceRowRenderer> rowList;
	
	private Logger logger;
	private JButton rollAll;
	private JButton resetAll;
	private JButton addDice;
	private JPanel gridPanel;
	private JPanel loggerPanel;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	private int nameWidthSW=0;
	private int nameWidthSWSkill=0;
	private int nameWidthRPG=0;
	private int nameWidthDeck=0;
	private int nameWidthGeneric=0;
	/*
	 * An array of RightPAnels handles the rows that render dicesets.
	 */
	public RightPanel(DicePanel mainPanel,DiceSetList setList)
	{
		this.mainPanel=mainPanel;
		this.setList=setList;
		this.setLayout(new BorderLayout());
		initialize();
		
	}
	private void initialize()
	{
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		rowList=new ArrayList<DiceRowRenderer>();
		//Logger
		this.setFocusable(true);
		
		logger = new Logger();
		logger.setPreferredSize(new Dimension(width/5,height/9));
		//rowList
		int i;
		gridPanel= new JPanel();
		gridPanel.setLayout(new GridLayout2(setList.getSize()*2, 1, 0, 0)); //fix cell height automatic
		DiceRowRenderer temp=null;
		for (i=0; i<setList.getSize();i++)
		{
			try 
			{
				temp=DiceRowRendererFactory.createRenderer(setList.getDiceSet(i));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			//retrieve last logger added
			temp.setLogger(logger);
			temp.setMainPanel(this);
			this.rowList.add(temp);
			gridPanel.add((JPanel)temp);
			if(i<(setList.getSize()-1))
				gridPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		}
		
		
		rollAll= new JButton();
		rollAll.addActionListener(this);
		rollAll.setText("Roll All");
		resetAll= new JButton();
		resetAll.addActionListener(this);
		resetAll.setText("Reset All");
		addDice= new JButton("Add Dice");
		addDice.addActionListener(this);
		rollAll.setPreferredSize(new Dimension(width/12,height/15));
		resetAll.setPreferredSize(new Dimension(width/12,height/15));
		addDice.setPreferredSize(new Dimension(width/12,height/15));
		loggerPanel = new JPanel();
		JScrollPane scroller = new JScrollPane(logger);
		scroller.setPreferredSize(new Dimension(width/5,height/8));
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		loggerPanel.add(scroller);
		loggerPanel.add(rollAll);
		loggerPanel.add(resetAll);
		loggerPanel.add(addDice);
		
		this.add(loggerPanel,BorderLayout.SOUTH);
		//Add JScrollPane(gridPanel);
		JScrollPane gridScroll= new JScrollPane(gridPanel);
		gridScroll.setPreferredSize(gridPanel.getSize());
		this.add(gridScroll,BorderLayout.CENTER);
		revalidate();
		repaint();
		this.resetColumns();
		
	}
	public void resetColumns()
	{
		for(DiceRowRenderer i: rowList)
		{
			if(i instanceof DiceRowDeckRenderer)
			{
				if(i.getEditSize()>nameWidthDeck)
				{
					nameWidthDeck=i.getEditSize();
					//extend to all row
					for(DiceRowRenderer j:rowList)
					{
						if(j instanceof DiceRowDeckRenderer && j.getEditSize()<nameWidthDeck)
							j.setEditSize(nameWidthDeck);
					}
				}
				else
					i.setEditSize(nameWidthDeck);
			}
			else if(i instanceof DiceRowGeneric)
			{
				if(i.getEditSize()>nameWidthGeneric)
				{
					nameWidthGeneric=i.getEditSize();
					for(DiceRowRenderer j:rowList)
					{
						if(j instanceof DiceRowGeneric && j.getEditSize()<nameWidthGeneric)
							j.setEditSize(nameWidthGeneric);
					}
				}
				else
					i.setEditSize(nameWidthGeneric);
			}
			else if(i instanceof DiceRowRPG)
			{
				if(i.getEditSize()>nameWidthRPG)
				{
					nameWidthRPG=i.getEditSize();
					for(DiceRowRenderer j:rowList)
					{
						if(j instanceof DiceRowRPG && j.getEditSize()<nameWidthRPG)
							j.setEditSize(nameWidthRPG);
					}
				}
				else
					i.setEditSize(nameWidthRPG);
			}
			else if(i instanceof DiceRowSW)
			{
				if(i.getEditSize()>nameWidthSW)
				{
					nameWidthSW=i.getEditSize();
					for(DiceRowRenderer j:rowList)
					{
						if(j instanceof DiceRowSW && j.getEditSize()<nameWidthSW)
							j.setEditSize(nameWidthSW);
					}
				}
				else
					i.setEditSize(nameWidthSW);
			}
			else if(i instanceof DiceRowSWSkill)
			{
				if(i.getEditSize()>nameWidthSWSkill)
				{
					nameWidthSWSkill=i.getEditSize();
					for(DiceRowRenderer j:rowList)
					{
						if(j instanceof DiceRowSWSkill && j.getEditSize()<nameWidthSWSkill)
							j.setEditSize(nameWidthSWSkill);
					}
				}
				else
					i.setEditSize(nameWidthSWSkill);
			}
		}
		
	}
	public DiceSetList getSetList()
	{
		return setList;
	}
	public void save()
	{
		for (DiceRowRenderer i:rowList)
		{
			i.save();			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//roll all dice
		if(e.getSource().equals(rollAll))
			rollAll();
		else if(e.getSource().equals(resetAll))
			resetAll();
		else if(e.getSource().equals(addDice))
			addDice();
	}
	private void addDice()
	{
		AddDiceSetPane pane= new AddDiceSetPane(DefaultSetsFactory.getAllDefaults());
		if(pane.displayGUI()==JOptionPane.YES_OPTION)
		{
			DiceSet selectedDiceSet = pane.getSelected();
			if(selectedDiceSet!=null)
			{
				if (!(selectedDiceSet.getDice() instanceof GenericDice))
				{
					DiceRowRenderer temp=null;
					try 
					{
						temp=DiceRowRendererFactory.createRenderer(pane.getSelected());
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					temp.setLogger(logger);
					temp.setMainPanel(this);
					rowList.add(temp);

					int index= mainPanel.getMainList().indexOf(setList);
					mainPanel.getMainList().get(index).addDiceSet(temp.getDiceSet());
				}
				else
				{
					//new dialog window to create a generic dice
					AddGenericDicePane dicePane= new AddGenericDicePane();
					if(dicePane.displayGUI()==JOptionPane.YES_OPTION)
					{
						DiceRowRenderer temp=null;
						try 
						{
							temp=DiceRowRendererFactory.createRenderer(dicePane.getSelected());
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
						temp.setLogger(logger);
						temp.setMainPanel(this);
						rowList.add(temp);
						
						int index= mainPanel.getMainList().indexOf(setList);
						mainPanel.getMainList().get(index).addDiceSet(temp.getDiceSet());
					}
				}
			}
			
			rowListUpdated();
		}
	}
	private void rowListUpdated()
	{
		removeAll();
		gridPanel= new JPanel();
		int i;
		gridPanel.setLayout(new GridLayout2(rowList.size()*2, 1, 0, 0)); //fix cell height automatic
		for (i=0; i<rowList.size();i++)
		{
			gridPanel.add((JPanel)rowList.get(i));
			
			if(i<rowList.size()-1)
				gridPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		}	
		JScrollPane gridScroll= new JScrollPane(gridPanel);
		gridScroll.setPreferredSize(gridPanel.getSize());
		this.add(gridScroll,BorderLayout.CENTER);
		this.add(loggerPanel,BorderLayout.SOUTH);
		DicePanel.saveFlag=true;
		revalidate();
		repaint();
		this.resetColumns();
	}
	private void rollRPG(DiceRowRenderer i)
	{
		if(!i.getResultString().equals(""))
		{
			this.logger.appendScroll("\n"+i.getResultString());
		}
	}
	private void resetAll()
	{
		for (DiceRowRenderer i: rowList)
			i.resetAll();
		//should log be erased?
		logger.reset();
	}
	private void rollAll()
	{
		//roll all dice
		ArrayList<StarWarsDice> list = new ArrayList<StarWarsDice>();
		ArrayList<Integer> rollsSW = new ArrayList<Integer>();
		int j,k;
		k=0;
		for (DiceRowRenderer i: rowList)
		{
			i.roll();
			if(i instanceof DiceRowSWSkill)
			{
				list.addAll(((DiceRowSWSkill)i).getRolledList());
				rollsSW.addAll(((DiceRowSWSkill) i).getAllRolls());
				k+=rollsSW.size();
			}
			else if (i instanceof DiceRowSW && !((i instanceof DiceRowSWSkill)))
			{
				for(j=0;j<((DiceRowSW)i).getAllRolls().size();j++)
					list.add((StarWarsDice) i.getDiceSet().getDice());
				
				rollsSW.addAll(((DiceRowSW)i).getAllRolls());
				k+=rollsSW.size();
			}
			else if (i instanceof DiceRowDeckRenderer)
			{
				Card drawed =((DiceRowDeckRenderer)i).getDrawed();
				String path=DicePictureGetter.getSuitImage(drawed);
				logger.appendImageAndString(drawed.getNum(), path);
				k++;
			}
			else if(i instanceof DiceRowRPG)
			{
				if (( (DiceRowRPG) i).getNum()>0)
				{
					rollRPG(i);
					k++;
				}
			}
			
		}
		SWRolledDice total = SWDiceValues.total(rollsSW,list);
		if (list.size()>0)
		{
			String toAppend = total.toString();
			if (toAppend.equals(""))
				this.logger.appendScroll("What a let down... It's a tie");
			else
				this.logger.appendScroll(total.toString());
			
			this.logger.appendImages(DicePictureGetter.getAllPathsForSWRoll(total));
		}
		else if(k==0)
		{
			this.logger.appendScroll(" No Dice Selected!");
		}
	}
	public void removeRow(DiceRowRenderer diceRow) {
		this.rowList.remove(diceRow);
		this.mainPanel.removeFromSelected(diceRow.getDiceSet());
		rowListUpdated();
		
	}
	
}
