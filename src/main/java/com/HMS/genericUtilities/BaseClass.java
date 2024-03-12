package com.HMS.genericUtilities;

import java.io.IOException;
import java.sql.SQLException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseClass
{
	public static WebDriver driver;
	DataBaseUtilities db=new DataBaseUtilities();
	ExcelUtilities e=new ExcelUtilities();
	JavaUtilities j=new JavaUtilities();
	PropertyFileUtilities pf=new PropertyFileUtilities();
	WebDriverUtilities w=new WebDriverUtilities();
	@BeforeSuite
	public void dBconnection() throws SQLException
	{
		db.connectToDb(); 
		System.out.println("connected to Db");
	}
//	@Parameters("Browser")
	@BeforeClass
	public void launchBrowser() throws IOException
	{
		
		String URL = pf.readPropertyFile("url");
	    String Browser = pf.readPropertyFile("browser");
		if(Browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(Browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(URL);
	}

	@BeforeMethod
	public void login()
	{
		System.out.println("logged in");
	}
	@AfterMethod
	public void logout()
	{
		System.out.println("logged out");
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
		System.out.println("close browser");
	}
	@AfterSuite
	public void closeDb() throws SQLException
	{
		db.closeConnectionDb();
		System.out.println("db closed");
	}
}
