package persistence;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import diceroller.mainpackage.DeckOfCards;
import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.DiceSetList;
import diceroller.mainpackage.GenericDice;
import diceroller.mainpackage.RPGDice;
import diceroller.mainpackage.SWSkillDice;
import diceroller.mainpackage.StarWarsDice;

public class Saver {
	public static DocumentBuilder builder;
	public static DocumentBuilderFactory factory;
	public static URI xmlFilePath;
	public static String xmlFileName="DiceRollerSaveFile.xml";
	
	public static void SaveXML(ArrayList<DiceSetList> mainList) throws ParserConfigurationException, URISyntaxException, TransformerException
	{
		if(xmlFilePath==null)
			xmlFilePath= new File(xmlFileName).toURI();
		if(factory==null)
			factory = DocumentBuilderFactory.newInstance();
		if(builder==null)
			builder = factory.newDocumentBuilder();
		
		Document document= builder.newDocument();
		Element mainElement = document.createElement("mainElement");
		document.appendChild(mainElement);
		int i;
		for (i=0; i<mainList.size();i++)
		{
			DiceSetList currDiceSetList = mainList.get(i);
			Element tempDSLElement = document.createElement("DiceSetList");
			tempDSLElement.setAttribute("name", currDiceSetList.getName());
			int k;
			for (k=0;k<currDiceSetList.getSize();k++)
			{
				DiceSet currDiceSet = currDiceSetList.getDiceSet(k);
				Element tempDSElement = document.createElement("DiceSet");
				tempDSElement.setAttribute("name",currDiceSet.getName());
				tempDSElement.setAttribute("num",""+ currDiceSet.getNum());
				tempDSElement.setAttribute("modTotal",""+ currDiceSet.getModTotal());
				tempDSElement.setAttribute("modMultiply",""+ currDiceSet.getModMultiply());
				tempDSElement.setAttribute("rollMode",currDiceSet.getRollMode());
				if(currDiceSet.getDice() instanceof SWSkillDice)
				{
					Element swSkillDice = document.createElement("SWDice");
					swSkillDice.setAttribute("type", "Skill");
					tempDSElement.appendChild(swSkillDice);
				}
				else if(currDiceSet.getDice() instanceof StarWarsDice)
				{
					Element swDice = document.createElement("SWDice");
					swDice.setAttribute("type", ((StarWarsDice)currDiceSet.getDice()).getName());
					tempDSElement.appendChild(swDice);
				}
				else if(currDiceSet.getDice() instanceof RPGDice)
				{
					Element rpgDice = document.createElement("RPGDice");
					String type=((RPGDice)currDiceSet.getDice()).getName().substring(1);
					if(type.equalsIgnoreCase("%"))
						type="90";
					rpgDice.setAttribute("type", type);
					tempDSElement.appendChild(rpgDice);
				}
				else if(currDiceSet.getDice() instanceof GenericDice)
				{
					Element genericDiceElement = document.createElement("GenericDice");
					GenericDice genericDice = ((GenericDice)currDiceSet.getDice());
					genericDiceElement.setAttribute("name", genericDice.getName());
					String possibleValues=genericDice.getPossibleValues().get(0);
					int j;
					for(j=1;j<genericDice.getPossibleValues().size();j++)
					{
						possibleValues+=","+genericDice.getPossibleValues().get(j);
					}
					genericDiceElement.setTextContent(possibleValues);
					tempDSElement.appendChild(genericDiceElement);
				}
				else if(currDiceSet.getDice() instanceof DeckOfCards)
				{
					Element deck = document.createElement("Deck");
					tempDSElement.appendChild(deck);
				}
				tempDSLElement.appendChild(tempDSElement);
			}
			mainElement.appendChild(tempDSLElement);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(document);
		//StreamResult result = new StreamResult(System.out);
		StreamResult result = new StreamResult(new File(xmlFilePath));
		transformer.transform(source,result);
	}
	
}
