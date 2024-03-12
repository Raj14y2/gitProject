package com.HMS.genericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyFileUtilities
{

	/**
	 *
	 * @param key
	 * @return value
	 * @throws IOException
	 */
	public static String readPropertyFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(ipathConstants.PropertyPath);
		Properties p=new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;


	}
	
	public static String createPropertyFile(String key,String val ) throws IOException
	{
		FileInputStream fi=new FileInputStream(ipathConstants.PropertyPath);
		Properties p=new Properties();
		
		p.setProperty(key, val);
		
		FileOutputStream fio=new FileOutputStream(ipathConstants.PropertyPath);
		p.store(fio, "o/p");
		return null;
		
	}
	public static void createOneProperty(String key,String val) throws IOException
	{
		FileInputStream fi=new FileInputStream(ipathConstants.PropertyPath);
		Properties p=new Properties();
		p.setProperty(key,val);
		FileOutputStream fio=new FileOutputStream(ipathConstants.PropertyPath);
		p.store(fio, val);
		
	}

}
