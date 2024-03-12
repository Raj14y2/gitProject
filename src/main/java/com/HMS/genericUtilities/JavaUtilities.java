package com.HMS.genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtilities
{


	public static String dateValue()
	{
		Date d=new Date();
		String date = d.toString();
		return date;
	}

	public static int randomNum()
	{
		Random r=new Random();
		int ranum = r.nextInt(500);
		return ranum;
	}

	public static String dateFormat(String Date)
	{
		SimpleDateFormat f=new SimpleDateFormat(Date);
		Date d=new Date();
		String date = f.format(d);
		return date;
	}
}
