package diceroller.userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import diceroller.mainpackage.Card;
import diceroller.mainpackage.DeckOfCards;
import diceroller.mainpackage.DiceSet;

public class DiceRowDeckRenderer extends JPanel implements ActionListener,DiceRowRenderer {

	private DiceSet set;
	private JButton drawButton;
	private PanelEditName panelName;
	private JLabel CardsLeft;
	private JCheckBox containsJokers;
	private String resultString;
	private JButton resetButton;
	private Logger logger;
	private RightPanel rightPanel;
	private JButton removeButton;
	private JButton shuffleButton;
	private Card drawed;
	
	public DiceRowDeckRenderer(DiceSet set) throws Exception 
	{
		super();
		this.set=set;
		if(!(set.getDice() instanceof DeckOfCards))
			throw new Exception("Wrong argument for GenericRenderer");
		initialize();
	}
	private void initialize()
	{
		//loads icon, dice, default modifier
		this.setLayout(new GridLayout2(1, 7, 0, 0));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		//Icon:
		JPanel panelIcon = new JPanel();
		panelIcon.setLayout(new GridBagLayout());
		drawButton = new JButton();
		drawButton.setIcon(DicePictureGetter.getImageFor(set));
		drawButton.addActionListener(this);
		drawButton.setHorizontalAlignment(JButton.CENTER);
		drawButton.setVerticalAlignment(JButton.CENTER);
		panelIcon.add(drawButton);
		this.add(panelIcon);
				
		//Name
		panelName = new PanelEditName(set,this);
		this.add(panelName);
		
		//Cards Left
		JPanel panelCardsLeft = new JPanel();
		CardsLeft = new JLabel();
		panelCardsLeft.setLayout(new GridLayout2());
		CardsLeft.setPreferredSize(new Dimension(screenSize.width/30,screenSize.width/50));
		CardsLeft.setHorizontalAlignment(SwingConstants.CENTER);
		CardsLeft.setText("Left:54");
		panelCardsLeft.add(CardsLeft);
		this.add(panelCardsLeft);
		
		//Jokers
		JPanel panelJokers = new JPanel();
		panelJokers.setLayout(new GridBagLayout());
		containsJokers = new JCheckBox();
		containsJokers.setSelected(((DeckOfCards)set.getDice()).hasJokers());
		containsJokers.addActionListener(this);
		panelJokers.add(new JLabel("Jokers: "));
		panelJokers.add(containsJokers);
		this.add(panelJokers);
		
		//Shuffle button for single row
		JPanel panelShuffle = new JPanel();
		panelShuffle.setLayout(new GridBagLayout());
		shuffleButton=new JButton(); 
		shuffleButton.setText("Shuffle");
		shuffleButton.setHorizontalAlignment(JButton.CENTER);
		shuffleButton.setVerticalAlignment(JButton.CENTER);
		shuffleButton.addActionListener(this);
		panelShuffle.add(shuffleButton);
		this.add(panelShuffle);				
					
		//Reset button for single row
		JPanel panelReset = new JPanel();
		panelReset.setLayout(new GridBagLayout());
		resetButton=new JButton(); 
		resetButton.setText("Reset");
		resetButton.setHorizontalAlignment(JButton.CENTER);
		resetButton.setVerticalAlignment(JButton.CENTER);
		resetButton.addActionListener(this);
		panelReset.add(resetButton);
		this.add(panelReset);
		
		//Remove button for single row
		JPanel panelRemove = new JPanel();
		panelRemove.setLayout(new GridBagLayout());
		removeButton = new JButton("X"); 
		removeButton.setHorizontalAlignment(JButton.CENTER);
		removeButton.setVerticalAlignment(JButton.CENTER);
		removeButton.addActionListener(this);
		removeButton.setForeground(Color.RED);
		panelRemove.add(removeButton);
		this.add(panelRemove);
	}
	public void save()
	{
		
	}
	@Override
	public void resetAll()
	{
		((DeckOfCards)set.getDice()).reset();
		this.CardsLeft.setText("Left:"+((DeckOfCards)set.getDice()).cardsLeft());
		this.resultString="";
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource().equals(drawButton) )
		{
			roll();
			if (logger!=null)
			{
				if(drawed==null)
				{
					this.logger.appendScroll("No more cards in deck!");
					this.drawButton.setEnabled(false);
				}
				else
				{
					String path=DicePictureGetter.getSuitImage(drawed);
					this.logger.appendImageAndString(drawed.getNum(),path );
					this.CardsLeft.setText("Left:"+((DeckOfCards)set.getDice()).cardsLeft());
				}
			}
		}
		if (arg0.getSource().equals(resetButton))
		{
			resetAll();
			if(!this.drawButton.isEnabled())
				drawButton.setEnabled(true);
		}
		else if(arg0.getSource().equals(removeButton))
		{
			this.rightPanel.removeRow(this);
			DicePanel.saveFlag=true;
		}
		else if (arg0.getSource().equals(containsJokers))
		{
			((DeckOfCards)set.getDice()).setJokers(containsJokers.isSelected());
			this.CardsLeft.setText("Left:"+((DeckOfCards)set.getDice()).cardsLeft());
			DicePanel.saveFlag=true;
		}
	}
	public void roll()
	{
		//this means "draw"
		drawed =((DeckOfCards)set.getDice()).draw();
		
	}
	public Card getDrawed()
	{
		return drawed;
	}
	public DiceSet getDiceSet()
	{
		return set;
	}
	public String getResultString()
	{
		return resultString;
	}
	public void setLogger(Logger logger)
	{
		this.logger=logger;
	}
	@Override
	public int getNum() {
		return 0;
	}
	@Override
	public void setMainPanel(RightPanel mainPanel) {
		this.rightPanel=mainPanel;
	}
	@Override
	public void setEditSize(int width) {
		this.panelName.setNameSizes(width);
	}
	public int getEditSize()
	{
		return this.panelName.getNameSizes();
	}
	public void editSizeChanged()
	{
		rightPanel.resetColumns();
	}
	

}
