package diceroller.userinterface;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import diceroller.mainpackage.DiceSet;
import diceroller.userinterface.rowrenderers.DiceRowRenderer;

public class PanelEditName extends JPanel implements FocusListener,KeyListener,MouseListener {

	JTextField editName;
	DiceSet set;
	DiceRowRenderer row;
	boolean exited=false;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height=screenSize.width/50;
	public PanelEditName(DiceSet set,DiceRowRenderer row)
	{
		this.set=set;
		this.row=row;
		this.setFocusable(true);
		if (screenSize.height>screenSize.width)
		{
			height=screenSize.height/50;;
		}
		editName= new JTextField(set.getName());
		this.setLayout(new GridBagLayout());
		editName.setHorizontalAlignment(SwingConstants.CENTER);
		editName.setEditable(false);
		editName.addFocusListener(this);
		editName.addKeyListener(this);
		editName.addMouseListener(this);
		this.add(editName);
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListenerClick(this.editName,this),AWTEvent.MOUSE_EVENT_MASK);
	}
	public String getName()
	{
		return editName.getText();
	}
	@Override
	public void focusGained(FocusEvent arg0) {}
	@Override
	public void focusLost(FocusEvent e) {	
		if(e.getSource().equals(editName))
		{
			editName.setEditable(false);
			FontMetrics fontMetrics = getFontMetrics(getFont());
			int width = fontMetrics.charsWidth(editName.getText().toCharArray(), 0, editName.getText().length()) + 5;
			editName.setPreferredSize(new Dimension(width,height));
			this.set.setName(editName.getText());
			DicePanel.saveFlag=true;
			row.editSizeChanged();
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().equals(editName))
		{
			editName.setEditable(true);
			editName.requestFocusInWindow();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getSource().equals(editName) && KeyEvent.VK_ENTER==arg0.getKeyCode())
		{
			editName.setEditable(false);
			FontMetrics fontMetrics = getFontMetrics(getFont());
			int width = fontMetrics.charsWidth(editName.getText().toCharArray(), 0, editName.getText().length()) + 5;
			editName.setPreferredSize(new Dimension(width,height));
			this.set.setName(editName.getText());
			DicePanel.saveFlag=true;
			row.editSizeChanged();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public int getNameSizes() 
	{
		return editName.getPreferredSize().width;
	}
	public void setNameSizes(int width) {
		
		editName.setPreferredSize(new Dimension(width,height));
		this.repaint();
		this.revalidate();
		
	}
}
