package diceroller.userinterface;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import diceroller.mainpackage.DiceSetList;
import diceroller.utils.DefaultSetsFactory;
import persistence.Loader;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Set;

public class MainFrame {

	private JFrame frmDiceRoller;
	private static ArrayList<DiceSetList> mainList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Look and feel
		try {
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height=screenSize.height;
			int width=screenSize.width;
			if (screenSize.height>screenSize.width)
			{
				height=width;
				width=screenSize.height;
			}
				
			
			UIManager.setLookAndFeel( 
					UIManager.getSystemLookAndFeelClassName());
			
			setDefaultSize(height/50);
        } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
	
		//My Stuff
		
		try {
			mainList=Loader.LoadXML();
		} catch (Exception e)
		{
			System.out.println("Error: file not found");
			//JOptionPane.showMessageDialog(null, new JLabel("Could not load file"),"Error", JOptionPane.INFORMATION_MESSAGE);
		}
		/*
		 * Guide to adding new dice type/dice set:
		 * 1.Create Dice Interface
		 * 2.Create DiceRowRenderer /references in DicePictureGetter
		 * 3.Adjust dependencies in RightPanel
		 * 4.Adjust dependencies in DefaultSetsFactory
		 * 5.Adjust dependencies in Loader/Saver
		 * 
		 */
		if(mainList==null || mainList.isEmpty())
			mainList = DefaultSetsFactory.getAllDefaultsInit();
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmDiceRoller.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public MainFrame() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		
		frmDiceRoller = new JFrame();
		frmDiceRoller.setTitle("  Dice Roller");
		frmDiceRoller.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/assets/mainassets/Purple_d20.png")));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
	   // String OSName=System.getProperty("os.name");
	    	
	    frmDiceRoller.setBounds(100, 100, width*16/25, height*19/25);
		frmDiceRoller.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		DicePanel mainPanel = new DicePanel(mainList);
		frmDiceRoller.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	int option=0;
            	if(DicePanel.saveFlag)
            	try{
            		option = JOptionPane.showConfirmDialog(null,
 		                   new JLabel("Save Before Closing?"),
 		                    "Save",
 		                    JOptionPane.YES_NO_OPTION,
 		                    JOptionPane.PLAIN_MESSAGE);
    				if(option==JOptionPane.YES_OPTION)
    							mainPanel.save();
    			}
    			catch(Exception er)
    			{
    				er.printStackTrace();
    			}
            	if(option!=JOptionPane.CLOSED_OPTION)
            		e.getWindow().dispose();
            }
        });
		frmDiceRoller.add(mainPanel);
		frmDiceRoller.setFocusable(true);
		
				
	}
	public static void setDefaultSize(int size) {

        Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
        Object[] keys = keySet.toArray(new Object[keySet.size()]);

        for (Object key : keys) {

            if (key != null && key.toString().toLowerCase().contains("font")) {

                Font font = UIManager.getDefaults().getFont(key);
                if (font != null) {
                    font = font.deriveFont((float)size);
                    UIManager.put(key, font);
                }

            }

        }

    }

}

