package persistence;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import diceroller.mainpackage.DeckOfCards;
import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.DiceSetList;
import diceroller.mainpackage.GenericDice;
import diceroller.mainpackage.RPGDice;
import diceroller.mainpackage.SWSkillDice;
import diceroller.mainpackage.StarWarsDice;

public class Loader {
	public static DocumentBuilder builder;
	public static DocumentBuilderFactory factory;
	public static String xmlFileName="DiceRollerSaveFile.xml";
	public static URI xmlFilePath;
	public static ArrayList<DiceSetList> LoadFrom(String path) throws ParserConfigurationException, SAXException, IOException, URISyntaxException
	{
		xmlFileName=path;
		return LoadXML();
	}
	public static ArrayList<DiceSetList> LoadXML() throws ParserConfigurationException, SAXException, IOException, URISyntaxException 
	{
		
		xmlFilePath= new File(xmlFileName).toURI();
		ArrayList<DiceSetList> result = new ArrayList<DiceSetList>();
		if(factory==null)
			factory = DocumentBuilderFactory.newInstance();
		if(builder==null)
			builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(xmlFilePath));
		NodeList DiceSetListNodes= document.getElementsByTagName("DiceSetList");
		int i;
		for (i=0; i<DiceSetListNodes.getLength();i++)
		{
			Node DiceSetListNode = DiceSetListNodes.item(i);
			DiceSetList tempDiceSetList = new DiceSetList();
			if (DiceSetListNode.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element DSLElement = (Element) DiceSetListNode;
				tempDiceSetList.setName(DSLElement.getAttribute("name"));
				NodeList DiceSetNodes= DSLElement.getElementsByTagName("DiceSet");
				int k;
				for (k=0;k<DiceSetNodes.getLength();k++)
				{
					DiceSet tempDiceSet = null;
					Node DiceSetNode = DiceSetNodes.item(k);
					if (DiceSetNode.getNodeType() == Node.ELEMENT_NODE) 
					{
						Element DSElement = (Element) DiceSetNode;
						NodeList DeckNodes = DSElement.getElementsByTagName("Deck");
						NodeList RPGNodes = DSElement.getElementsByTagName("RPGDice");
						NodeList SWNodes = DSElement.getElementsByTagName("SWDice");
						NodeList GenericNodes = DSElement.getElementsByTagName("GenericDice");
						if(DeckNodes.getLength()==1)
						{
							tempDiceSet=new DiceSet(new DeckOfCards(true));
							tempDiceSet.setName(DSElement.getAttribute("name"));
						}
						if(RPGNodes.getLength()==1)
						{
							Node RPGNode = RPGNodes.item(0);
							if(RPGNode.getNodeType()==Node.ELEMENT_NODE)
							{
								Element RPGElement =(Element) RPGNode;
								String type = RPGElement.getAttribute("type");
								if(type!=null && !type.isEmpty())
								{
									tempDiceSet= new DiceSet(new RPGDice(Integer.parseInt(type)));
									String num = DSElement.getAttribute("num");
									String modTotal = DSElement.getAttribute("modTotal");
									String modMultiply = DSElement.getAttribute("modMultiply");
									String rollMode = DSElement.getAttribute("rollMode");
									if(num!=null && !num.isEmpty())
									{
										tempDiceSet.setNum(Integer.parseInt(num));
									}
									if(modTotal!=null && !modTotal.isEmpty())
									{
										tempDiceSet.setModTotal(Integer.parseInt(modTotal));
									}
									if(modMultiply!=null && !modMultiply.isEmpty())
									{
										tempDiceSet.setModMultiply(Integer.parseInt(modMultiply));
									}
									if(rollMode!=null && !rollMode.isEmpty())
									{
										tempDiceSet.setRollMode(rollMode);
									}
									tempDiceSet.setName(DSElement.getAttribute("name"));
								}
								
							}
						}
						if(SWNodes.getLength()==1)
						{
							Node SWNode = SWNodes.item(0);
							if(SWNode.getNodeType()==Node.ELEMENT_NODE)
							{
								Element SWElement =(Element) SWNode;
								String type = SWElement.getAttribute("type");
								String mod = DSElement.getAttribute("modTotal");
								if(type!=null && !type.isEmpty())
								{
									if( type.equals("Skill"))
									{
										tempDiceSet= new DiceSet(new SWSkillDice(type));
										if(mod!=null && !mod.isEmpty())
											tempDiceSet.setModTotal(Integer.parseInt(mod));
									}
									else
										tempDiceSet= new DiceSet(new StarWarsDice(type));
									String num = DSElement.getAttribute("num");
									
									if(num!=null && !num.isEmpty())
									{
										tempDiceSet.setNum(Integer.parseInt(num));
									}
								}
								tempDiceSet.setName(DSElement.getAttribute("name"));
							}
						}
						if(GenericNodes.getLength()==1)
						{
							Node GenericNode = GenericNodes.item(0);
							if(GenericNode.getNodeType()==Node.ELEMENT_NODE)
							{
								Element GenericElement =(Element) GenericNode;
								String name = GenericElement.getAttribute("name");
								if(name!=null && !name.isEmpty())
								{
									ArrayList<String> possibleValues= new ArrayList<String>();
									StringTokenizer tkr= new StringTokenizer(GenericElement.getTextContent(), ",");
									while (tkr.hasMoreTokens())
									{
										possibleValues.add(tkr.nextToken());
									}
									tempDiceSet = new DiceSet(new GenericDice(possibleValues,name));
								}
								tempDiceSet.setName(DSElement.getAttribute("name"));
							}
						}
					}
					tempDiceSetList.addDiceSet(tempDiceSet);
				}
				
			}
			result.add(tempDiceSetList);
		}
		
		return result;
	}
}
