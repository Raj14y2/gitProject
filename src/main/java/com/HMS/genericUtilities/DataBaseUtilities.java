package com.HMS.genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtilities
{
	Connection con=null;
	/**
	 * this method is used to connect the dataBase
	 * @throws SQLException
	 */
	public void  connectToDb() throws SQLException
	{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(ipathConstants.DBURL,ipathConstants.Username,ipathConstants.PWD);

	}
	/**
	 * This method is used to execute the select queries
	 * @param Query
	 * @param index
	 * @param expData
	 * @throws SQLException
	 */
	public void fetchDataInDb(String Query,int index,String expData) throws SQLException
	{
		Statement stat = con.createStatement();
		ResultSet set = stat.executeQuery(Query);

		boolean flag=false;
		while(set.next())
		{
			String actualData = set.getString(index);
			if(actualData.equalsIgnoreCase(expData))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("---data is present");
		}
		else
		{
			System.out.println("---data is not present");
		}
	}

	/**
	 * This method is used to close the DataBase connection
	 * @throws SQLException
	 */
	public void closeConnectionDb() throws SQLException
	{
		con.close();
	}

	/**
	 * This method is used to update or create the Data in Database
	 * @param Query
	 * @throws SQLException
	 */
	public void updateDataInDb(String Query) throws SQLException
	{
		Statement stat = con.createStatement();
		stat.executeUpdate(Query);
	}

}
