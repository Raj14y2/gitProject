package com.HMS.genericUtilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class IannotationListen implements IAnnotationTransformer
{

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		annotation.setRetryAnalyzer(com.HMS.genericUtilities.RetryImpClass.class);
	}

}
