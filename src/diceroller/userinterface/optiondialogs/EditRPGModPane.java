package diceroller.userinterface.optiondialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import diceroller.userinterface.CustomSpinner;
import diceroller.userinterface.GridLayout2;

public class EditRPGModPane {

	private JPanel panel;
	CustomSpinner ModTotalSpinner;
	CustomSpinner ModMultiplySpinner;
	JComboBox rollMode;
	
	public EditRPGModPane(int ModTotal, int ModMultiply, String rollModeString)
	{
		panel = new JPanel();
        panel.setLayout(new GridLayout2(5,1));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
        int height=screenSize.height;
    	int width=screenSize.width;
    	if (screenSize.height>screenSize.width)
    	{
    		height=width;
    		width=screenSize.height;
    	}
        
        JPanel panelTotal= new JPanel();
        panelTotal.setLayout(new GridBagLayout());
        JLabel totalName = new JLabel("Mod total:");
        JPanel totalNamePanel = new JPanel();
        totalName.setHorizontalAlignment(SwingConstants.CENTER);
        totalName.setPreferredSize(new Dimension(width/12,height/30));
        totalNamePanel.add(totalName);
        panelTotal.add(totalNamePanel);
        ModTotalSpinner=new CustomSpinner(99,-99,ModTotal,1);
        panelTotal.add(ModTotalSpinner);
        

        JPanel panelMultiply= new JPanel();
        panelMultiply.setLayout(new GridBagLayout());
        JLabel mulName = new JLabel("Mod per roll:");
        JPanel mulNamePanel = new JPanel();
        mulName.setHorizontalAlignment(SwingConstants.CENTER);
        mulName.setPreferredSize(new Dimension(width/12,height/30));
        mulNamePanel.add(mulName);
        panelMultiply.add(mulNamePanel);
        ModMultiplySpinner=new CustomSpinner(99,-99,ModMultiply,1);
        panelMultiply.add(ModMultiplySpinner);
        
        JPanel panelRollMode=new JPanel();
        String[] modes = new String[3];
        modes[0]="  Advantage";
        modes[1]="  Disadvantage";
        modes[2]="  Total";
        rollMode= new JComboBox(modes);
        rollMode.setSelectedItem((Object)"  "+rollModeString);
        rollMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelRollMode.add(rollMode);
        
        
        panel.add(panelTotal);
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(panelMultiply);
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(panelRollMode);
       
	}
    public int displayGUI() 
    {
        return JOptionPane.showConfirmDialog(null,
                        getPanel(),
                        "Edit Mods",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel getPanel() 
    {
        return panel;
    }
    public int getModTotal()
    {
    	return ModTotalSpinner.getValue();
    }
    public int getModMultiply()
    {
    	return ModMultiplySpinner.getValue();
    }
	public String getRollMode()
	{
		return rollMode.getSelectedItem().toString().trim();
	}
}
