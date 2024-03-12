package com.HMS.genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImpClass implements IRetryAnalyzer
{

	int count=0;
	int Retrycount=1;
	public boolean retry(ITestResult result) {
		
		if(count<Retrycount)
		{
			count++;
			return true;
		}
		return false;
	}


}
