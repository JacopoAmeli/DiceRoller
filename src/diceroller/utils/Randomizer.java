package diceroller.utils;

import java.util.ArrayList;
import java.util.Random;

public class Randomizer {
	
	private static Random rnd;

	public static int getRandomNumber(ArrayList<Integer> rangeofvalues)
	{
		if (rnd==null)
		{
			rnd=new Random(System.currentTimeMillis()); //TODO fix
		}
		int max=rangeofvalues.size();
		return rangeofvalues.get(rnd.nextInt(max));
	}
	public static String getRandomString(ArrayList<String> rangeofvalues)
	{
		if (rnd==null)
		{
			rnd=new Random(System.currentTimeMillis()); //TODO fix
		}
		int max=rangeofvalues.size();
		return rangeofvalues.get(rnd.nextInt(max));
	}
	public static Object getRandomObject(ArrayList<Object> rangeofvalues) {
		if (rnd==null)
		{
			rnd=new Random(System.currentTimeMillis()); //TODO fix
		}
		int max=rangeofvalues.size();
		return rangeofvalues.get(rnd.nextInt(max));
	}
	public static int randomNum(int max)
	{
		if (rnd==null)
		{
			rnd=new Random(System.currentTimeMillis()); //TODO fix
		}
		return rnd.nextInt(max);
	}
}
