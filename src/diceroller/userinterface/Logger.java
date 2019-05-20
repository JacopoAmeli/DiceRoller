package diceroller.userinterface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class Logger extends JTextPane {

	private int loggerLines=0;
	private int heightLine;
	
	public boolean getScrollableTracksViewportWidth() {
		 return getUI().getPreferredSize(this).width 
		            <= getParent().getSize().width;
    }
	public Logger()
	{
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		int fontSize= height/50;
		heightLine=height/45;
		DefaultCaret caret = (DefaultCaret)this.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		this.setEditable(false);
		this.setText("<html>\n" +
	            "<body>\n" +
	            "</body>\n" +
	            "</html>");
		HTMLEditorKit hek = new HTMLEditorKit();
		this.setEditorKit(hek);
		Font font =new Font("Segoe UI",0,fontSize);
		String bodyRule = "body { font-family: " + font.getFamily() + "; " +
	            "font-size: " + font.getSize() + "pt;}";
		String divRule = "div {text-align:center;}";
		String imgRule = "img {display:block; }";
	    ((HTMLDocument)this.getDocument()).getStyleSheet().addRule(bodyRule);
	    ((HTMLDocument)this.getDocument()).getStyleSheet().addRule(divRule);
	    ((HTMLDocument)this.getDocument()).getStyleSheet().addRule(imgRule);
	    
	}
	public void appendLogger(String str)
	{
		//custom append		
		HTMLDocument doc = (HTMLDocument)getDocument();
		try
		{
			Element[] roots = doc.getRootElements();
			Element body = null;
			for( int i = 0; i < roots[0].getElementCount(); i++ ) 
			{
				Element element = roots[0].getElement( i );
				if( element.getAttributes().getAttribute( StyleConstants.NameAttribute ) == HTML.Tag.BODY ) 
				{
					body = element;
					break;
				}
			}
			doc.insertBeforeEnd(body,str);
			}
		catch(BadLocationException e)
		{
			e.printStackTrace();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
		revalidate();
	}
	
	public void appendScroll(String str)
	{
		int lengthMax=34;
		if(str.length()>lengthMax)
		{
			ArrayList<String> strings= new ArrayList<String>();
			strings.add(str.substring(0, lengthMax-1));
			int j;
			for (j=lengthMax;j<str.length();j+=lengthMax)
			{
				if(str.length()<=j+lengthMax-1)
				{
					strings.add(str.substring(j,str.length()));
					break;
				}
				strings.add(str.substring(j,j+lengthMax-1));
				
			}
			for (String toBr:strings)
			{
				appendLogger(toBr+"</br>");
				loggerLines++;
			}
			
		}
		else
		{
			appendLogger(str+"</br>");
			loggerLines++;
		}
		if(loggerLines>4)
			this.setPreferredSize(new Dimension(this.getPreferredSize().width,this.getPreferredSize().height+1*heightLine));
		this.setCaretPosition(this.getDocument().getLength());
		revalidate();
	}
	public void appendScrollDiv(String str)
	{
		appendLogger(str+"</br>");
		loggerLines++;
		if(loggerLines>4)
			this.setPreferredSize(new Dimension(this.getPreferredSize().width,this.getPreferredSize().height+1*heightLine));
		this.setCaretPosition(this.getDocument().getLength());
		revalidate();
	}
	public void reset()
	{
		loggerLines=0;

		this.setDocument(new HTMLDocument());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		int fontSize= height/50;
		heightLine=height/45;
		DefaultCaret caret = (DefaultCaret)this.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		this.setEditable(false);
		this.setText("<html>\n" +
	            "<body>\n" +
	            "</body>\n" +
	            "</html>");
		HTMLEditorKit hek = new HTMLEditorKit();
		this.setEditorKit(hek);
		Font font =new Font("Segoe UI",0,fontSize);
		String bodyRule = "body { font-family: " + font.getFamily() + "; " +
	            "font-size: " + font.getSize() + "pt;}";
		String divRule = "div {text-align:center;}";
		String imgRule = "img {display:block; }";
	    ((HTMLDocument)this.getDocument()).getStyleSheet().addRule(bodyRule);
	    ((HTMLDocument)this.getDocument()).getStyleSheet().addRule(divRule);
	    ((HTMLDocument)this.getDocument()).getStyleSheet().addRule(imgRule);
	    repaint();
		
	}
	public void appendImages(List<String> imagePaths)
	{
		int i;
		int margin=40;
		if(imagePaths.size()>30)
		{
			//have to split
		}
		String div=("<div style='width:"+imagePaths.size()*heightLine+"px; background-color:white; height:"+heightLine+"px; overflow:auto; margin-right:"+margin+"px; margin-left:"+margin+"px;'>\n");
		for(i=0;i<imagePaths.size();i++)
		{
			URL url = getClass().getResource(imagePaths.get(i));
			String path="<img src='"+url.toString()+"' width="+heightLine+" height="+heightLine+"></img>\n";
			//System.out.println(path);
			div=div.concat(path);
		}
		div=div.concat("</div>\n");

		appendScrollDiv(div);
	}
	public void appendImageAndString(String str, String pathToImage)
	{
		
		int margin=40;
		String div=("<div style=' width:"+1*heightLine+"px; background-color:white; height:"+heightLine+"px; overflow:auto; margin-right:"+margin+"px; margin-left:"+margin+"px;'>\n");
		URL url = getClass().getResource(pathToImage);
		String text = str+" ";
		String path="<img style='float: left;' src='"+url.toString()+"' width="+heightLine+" height="+heightLine+"></img>\n";
		//System.out.println(path);
		div=div.concat(text);
		div=div.concat(path);
		div=div.concat("</div>\n");

		appendScrollDiv(div);
	}
	
}
