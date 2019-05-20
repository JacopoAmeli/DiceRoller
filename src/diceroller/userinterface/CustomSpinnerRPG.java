package diceroller.userinterface;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class CustomSpinnerRPG extends JComponent implements ActionListener {

	private JLabel value;
	private JButton add;
	private JButton remove;
	private int max;
	private int min;
	private int startvalue;
	private int step;
	private int intValue;
	private String type;
	
	public CustomSpinnerRPG(int max, int min, int startvalue,int step,String type)
	{
		super();
		this.max=max;
		this.min=min;
		this.startvalue=startvalue;
		this.intValue=startvalue;
		this.type=type+" ";
		this.step=step;
		initialize();
		this.setFocusable(true);
	}
	private void initialize()
	{	
		this.setLayout(new GridLayout2(1,3));
		this.value=new JLabel();
		this.value.setText(""+startvalue+type);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		value.setPreferredSize(new Dimension(width/18, height/65));
		value.setHorizontalAlignment(JLabel.CENTER);
		value.setVerticalAlignment(JLabel.CENTER);
		this.add(value);
		
		this.add= new JButton();
		add.setText("+");
		add.addActionListener(this);
		add.setPreferredSize(new Dimension(width/30, width/30));
		add.setHorizontalAlignment(JButton.CENTER);
		add.setVerticalAlignment(JButton.CENTER);
		this.add(add);
		
		this.remove=new JButton();
		remove.setText("-");
		remove.addActionListener(this);
		remove.setPreferredSize(new Dimension(width/30, width/30));
		remove.setHorizontalAlignment(JButton.CENTER);
		remove.setVerticalAlignment(JButton.CENTER);
		this.add(remove);
	}
	public int getValue()
	{
		return intValue;
	}
	public void setValue(int valueInt)
	{
		if(valueInt>=min && valueInt<=max)
		{
			this.intValue=valueInt;
			this.value.setText(""+valueInt+type);
		}
		repaint();
	}
	public void reset()
	{
		this.value.setText(""+startvalue+type);
		this.intValue=startvalue;
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource().equals(add))
		{
			if (intValue+step<=max)
			{
				intValue+=step;
				this.value.setText(""+intValue+type);
				DicePanel.saveFlag=true;
			}
		}
		else if (arg0.getSource().equals(remove))
		{
			if (intValue-step>=min)
			{
				intValue-=step;
				this.value.setText(""+intValue+type);
				DicePanel.saveFlag=true;
			}
		}
		repaint();
	}
	
}
