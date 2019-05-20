package diceroller.userinterface;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class EditRPGModPane {

	private JPanel panel;
	CustomSpinner ModTotalSpinner;
	CustomSpinner ModMultiplySpinner;
	
	public EditRPGModPane(int ModTotal, int ModMultiply)
	{
		panel = new JPanel();
        panel.setLayout(new GridLayout2());
        
        JPanel panelTotal= new JPanel();
        panelTotal.setLayout(new GridBagLayout());
        panelTotal.add(new JLabel("Mod total: "));
        ModTotalSpinner=new CustomSpinner(99,-99,ModTotal,1);
        panelTotal.add(ModTotalSpinner);
        panelTotal.add(new JLabel("   "));
        

        JPanel panelMultiply= new JPanel();
        panelMultiply.setLayout(new GridBagLayout());
        panelMultiply.add(new JLabel("   Mod per roll: "));
        ModMultiplySpinner=new CustomSpinner(99,-99,ModMultiply,1);
        panelMultiply.add(ModMultiplySpinner);
        
        panel.add(panelTotal);
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(panelMultiply);
       
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
	
}
