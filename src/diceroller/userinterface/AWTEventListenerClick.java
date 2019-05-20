package diceroller.userinterface;

import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class AWTEventListenerClick implements AWTEventListener {

	private JComponent unFocus;
	private JComponent focus;
	private double boundLeft;
	private double boundRight;
	private double boundTop;
	private double boundBottom;
	private boolean testFlag=false;
	public AWTEventListenerClick(JComponent unFocusOnClick,JComponent focusOnClick)
	{
		unFocus=unFocusOnClick;
		focus=focusOnClick;
	}
	@Override
	public void eventDispatched(AWTEvent arg0) 
	{
		if(arg0 instanceof MouseEvent)
		{
			MouseEvent me= (MouseEvent) arg0;
			if (MouseEvent.MOUSE_CLICKED==me.getID() && unFocus.isVisible() && unFocus.hasFocus() )
			{
				if(!testFlag)
				{
					testFlag=true;
				}
				else
				{
					Point clickLocation=MouseInfo.getPointerInfo().getLocation();
					
					boundLeft=unFocus.getLocationOnScreen().getX();
					boundRight=boundLeft+unFocus.getWidth();
					boundTop=unFocus.getLocationOnScreen().getY();
					boundBottom=boundTop+unFocus.getHeight();
					
					//if clickLocation is outside of JComponent, I need to unfocus it
					if(boundLeft>clickLocation.getX()|| clickLocation.getX()>boundRight 
							|| boundTop>clickLocation.getY() || clickLocation.getY()>boundBottom)
					{
						focus.requestFocusInWindow();
					}
					testFlag=false;
				}
				
					

			}
		}
	
	}

}
