package diceroller.userinterface;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import diceroller.mainpackage.Card;
import diceroller.mainpackage.DeckOfCards;
import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.RPGDice;
import diceroller.mainpackage.StarWarsDice;
import diceroller.utils.SWRolledDice;

public class DicePictureGetter {

	
	//TODO finds dice image related to a result.
	public static ImageIcon getImageFor(DiceSet set)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/30;
		ImageIcon result;
		if(set.getDice() instanceof StarWarsDice)
		{
			String path="/assets/starWarsAssets/";
			path+=""+set.getDice().getName().toLowerCase()+".png";
			URL url = DicePictureGetter.class.getResource(path);
			result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		}
		else if (set.getDice() instanceof RPGDice)
		{
			String path="/assets/RPGAssets/";
			path+=""+set.getDice().getName().toLowerCase()+".png";
			URL url = DicePictureGetter.class.getResource(path);
			result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width)); 
		}
		else if (set.getDice() instanceof DeckOfCards)
		{
			String path="/assets/deckAssets/cardBack.png";
			URL url = DicePictureGetter.class.getResource(path);
			result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),(width*13/10),(width*9/7)*13/10));
		}
		else
		{
			String path="/assets/mainassets/";
			path+="Purple_d20.png";
			URL url = DicePictureGetter.class.getResource(path);
			result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		}
			
		return result;
	}
	
	public static ImageIcon getImageForRoll(DiceSet set, int roll)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		 width=width/60;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/Purple_d20.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getSettingsIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/35;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/cog.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getAbilityIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/55;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/starWarsAssets/ability.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getProficiencyIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/55;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/starWarsAssets/proficiency.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getDeleteIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/70;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/delete.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getRenameIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/70;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/rename.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getResetIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/35;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/reset.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getCheckedIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/35;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/checked.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getUncheckedIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/35;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/unchecked.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getShuffleIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/35;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/shuffle.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getSkillIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/30;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/starWarsAssets/skill.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getImportIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/50;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/import.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ImageIcon getSaveIcon()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		width=width/60;
		ImageIcon result;
		URL url = DicePictureGetter.class.getResource("/assets/mainassets/save.png");
		result= new ImageIcon(getScaledImage(new ImageIcon(url).getImage(),width,width));
		return result;
	}
	public static ArrayList<String> getAllPathsForSWRoll(SWRolledDice dice)
	{
		ArrayList<String> result=new ArrayList<String>();
		int i;
		String path="/assets/starWarsAssets/";
		for (i=0; i<dice.getAdvantages();i++)
			result.add(path+"advantage.png");
		for (i=0; i<dice.getDarkforcepoints();i++)
			result.add(path+"darkside.png");
		for (i=0; i<dice.getDespairs();i++)
			result.add(path+"despair.png");
		for (i=0; i<dice.getDisadvantages();i++)
			result.add(path+"disadvantage.png");
		for (i=0; i<dice.getFailures();i++)
			result.add(path+"failure.png");
		for (i=0; i<dice.getLightforcepoints();i++)
			result.add(path+"lightside.png");
		for (i=0; i<dice.getSuccesses();i++)
			result.add(path+"success.png");
		for (i=0; i<dice.getTriumphs();i++)
			result.add(path+"triumph.png");
		
		return result;
	}
	
	private static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

	public static String getSuitImage(Card drawed) 
	{
		String path="/assets/deckAssets/";
		
		if(drawed.getSuit().equals("Red"))
		{
			path+="RedJoker.png";
		}
		else if(drawed.getSuit().equals("Black"))
		{
			path+="BlackJoker.png";
		}
		else
		{
			path+=drawed.getSuit()+".png";
		}
		return path;
	}
}
