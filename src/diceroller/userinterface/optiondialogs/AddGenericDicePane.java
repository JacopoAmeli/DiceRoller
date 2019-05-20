package diceroller.userinterface.optiondialogs;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.GenericDice;
import diceroller.userinterface.GridLayout2;

public class AddGenericDicePane {

	private JPanel panel;
	private DiceSet selected;
	private ArrayList<String> possibleValues;
	private String name;
	private JTextField textField;
	private JTextField possibleTextField;
	
	public AddGenericDicePane()
	{
		panel = new JPanel();
        panel.setLayout(new GridLayout2(1,2));
        //panel contains a text area for the name, and a text area with a list of possible values
       textField= new JTextField("Name");
       textField.getDocument().addDocumentListener(new DocumentListener() {
    	   @Override
           public void insertUpdate(DocumentEvent de) {
        	   changed(de);
           }
           @Override
           public void removeUpdate(DocumentEvent de) {
        	   changed(de);
           }
           @Override
           public void changedUpdate(DocumentEvent de) {
        	   
           }
           private void changed(DocumentEvent de) {
        	   name=textField.getText();			
		}
       });
       textField.addFocusListener(new FocusListener(){
           @Override
           public void focusGained(FocusEvent arg0) {
               textField.setText("");
           }

		@Override
		public void focusLost(FocusEvent arg0) {
			
		}
       });
       possibleTextField = new JTextField("Possible Values");
       possibleTextField.getDocument().addDocumentListener(new DocumentListener() {
    	   @Override
           public void insertUpdate(DocumentEvent de) {
        	   changed(de);
           }
           @Override
           public void removeUpdate(DocumentEvent de) {
        	   changed(de);
           }
           @Override
           public void changedUpdate(DocumentEvent de) {
        	  
           }
           private void changed(DocumentEvent de) {
        	  possibleValues=new ArrayList<String>();
			String toTokenize=possibleTextField.getText();
			StringTokenizer tokenizer= new StringTokenizer(toTokenize, ",");
			while (tokenizer.hasMoreTokens())
			{
				String token = tokenizer.nextToken();
				if(token.contains("-"))
				{
					StringTokenizer tokenizer2 = new StringTokenizer(token,"-");
					String start;
					String end;
					if(tokenizer2.hasMoreTokens())
					{
						start=tokenizer2.nextToken();
						if(tokenizer2.hasMoreTokens())
						{
							end=tokenizer2.nextToken();
							try
							{
								int startInt = Integer.parseInt(start);
								int endInt= Integer.parseInt(end);
								if(startInt>=endInt)
									throw new Exception ("start>int range");
								for(;startInt<=endInt;startInt++)
								{
									possibleValues.add(""+startInt);
								}
							
							}
							catch(Exception e)
							{
								e.printStackTrace();
								possibleValues.add(token);
							}
						}
						else
							possibleValues.add(token);
					}
					else
						possibleValues.add(token);
				}
				else
					possibleValues.add(token);
			}			
		}
       });
       possibleTextField.addFocusListener(new FocusListener(){
           @Override
           public void focusGained(FocusEvent arg0) {
               possibleTextField.setText("");
           }

		@Override
		public void focusLost(FocusEvent arg0) {
		}
       });
       panel.add(textField);
       panel.add(possibleTextField);
       
	}
    public int displayGUI() 
    {
        return JOptionPane.showConfirmDialog(null,
                        getPanel(),
                        "Add Generic Dice",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel getPanel() 
    {
        return panel;
    }
    public String getName()
    {
    	return name;
    }
    public DiceSet getSelected()
    {
    	//Create selected
    	selected = new DiceSet(new GenericDice(possibleValues,name));
    	selected.setName(name);
    	selected.setNum(0);
    	return selected;
    }
	
	
}