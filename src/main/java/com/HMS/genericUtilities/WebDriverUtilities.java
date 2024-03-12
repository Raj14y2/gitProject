package com.HMS.genericUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilities
{

	/**
	 * this method used to maximize the window
	 * @param driver
	 */
	public void maxBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	/**
	 * this method is used to minimize the window
	 * @param driver
	 */
	public void minBrowser(WebDriver driver)
	{
		driver.manage().window().minimize();
	}

	/**
	 * This method is used to give implicit wait
	 * @param driver
	 * @param sec
	 */
	public void waitImplicitly(WebDriver driver,int sec)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
	/**
	 * Generic of select class object
	 * @param element
	 * @return
	 */
	public Select selectObj(WebElement element)
	{
		Select s=new Select(element);
		return s;
	}
	/**
	 *  This method is used for select the element by index in dropdown
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element,int index)
	{
		selectObj(element).selectByIndex(index);
	}

	/**
	 * select the element in Dropdown by value
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element,String  value)
	{
		selectObj(element).selectByValue(value);
	}

	/**
	 * This method is used for select the elements in dropdown by visible Text
	 * @param value
	 * @param element
	 */
	public void handleDropDown(String  value,WebElement element)
	{
		selectObj(element).selectByVisibleText(value);
	}

	/**
	 * This method is used to get all the options present in the Dropdown
	 * @param element
	 * @return
	 */
	public List<WebElement> fetchOptions(WebElement element)
	{
		List<WebElement> opt= selectObj(element).getOptions();
		return opt;
	}
	/**
	 * This method is used to create the object for Actions Class
	 * @param driver
	 * @return
	 */
	public Actions actionObj(WebDriver driver)
	{
		Actions act=new Actions(driver);
		return act;
	}
	public void mouseHover(WebElement ele,WebDriver driver)
	{
		actionObj(driver).moveToElement(ele);
	}
	/**
	 * This method is used to mousehover to particular element
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver,WebElement element)
	{
		actionObj(driver).click(element).perform();
	}
	/**
	 * this method is used to drag and drop actions
	 * @param driver
	 * @param src
	 * @param dst
	 */
	public void dragAndDropToele(WebDriver driver,WebElement src,WebElement dst)
	{
		actionObj(driver).dragAndDrop(src, dst);
	}

	/**
	 * This method used to perform the double click action on element
	 * @param driver
	 * @param element
	 */
	public void doubleClickOnEle(WebDriver driver,WebElement element)
	{
		actionObj(driver).doubleClick(element).perform();
	}
	/**
	 * this method is used to perform rightClick Action
	 * @param driver
	 * @param element
	 */
	public void rightClickOnEle(WebDriver driver,WebElement element)
	{
		actionObj(driver).contextClick(element).perform();
	}

	/**
	 * This method used to scroll to particular element
	 * @param driver
	 * @param element
	 */
	public void scrollToEle(WebDriver driver,WebElement element)
	{
		actionObj(driver).scrollToElement(element).perform();
	}

	/**
	 * This method is used to scroll by amount or value
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void scrollByVal(WebDriver driver,int x,int y)
	{
		actionObj(driver).scrollByAmount(x, y).perform();
	}
	/**
	 * Method is used to scroll for one time
	 * @param driver
	 */
	public void scrollByKeys(WebDriver driver)
	{
		actionObj(driver).sendKeys(Keys.PAGE_DOWN).perform();
	}
	/**
	 * Method is used to click on Enter
	 * @param driver
	 */
	public void pressEnter(WebDriver driver)
	{
		actionObj(driver).sendKeys(Keys.ENTER).perform();
	}
	/**
	 * created object foe webdriver wait
	 * @param driver
	 * @param sec
	 * @return
	 */
	public WebDriverWait explicitWaitObj(WebDriver driver,int sec)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(sec));
		return wait;
	}
	/**
	 * method is used to verifiy element is clickable
	 * @param driver
	 * @param sec
	 * @param element
	 */
	public void eleClickAbleCheck(WebDriver driver,int sec,WebElement element)
	{
		explicitWaitObj(driver,sec).until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * Method is used to verifiy whether the element is visible
	 * @param driver
	 * @param sec
	 * @param element
	 */
	public void eleVisibilityCheck(WebDriver driver,int sec,WebElement element)
	{
		explicitWaitObj(driver, sec).until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * The method is used to swithTo particular window and verify
	 * @param driver
	 * @param expData
	 */
	public void switchToWindow(WebDriver driver,String expData)
	{
		Set<String> wh = driver.getWindowHandles();
		Iterator<String> it=wh.iterator();
		while(it.hasNext())
		{
			String window = it.next();
			String actualData = driver.switchTo().window(window).getTitle();
			if(actualData.contains(expData))
			{
				break;
			}
		}
	}
	/**
	 * This Method is used to take Screenshots
	 * @param driver
	 * @param name
	 * @return 
	 * @throws IOException
	 */
	public String screenShot(WebDriver driver,String name) throws IOException
	{
		String Date = JavaUtilities.dateValue();
		String d1 = Date.toString();
		String d2= d1.replace(":", "-");
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(ipathConstants.ScreenShot_Path+" "+d2+" "+name+".jpeg");
		String path = dst.getAbsolutePath();
		FileUtils.copyFile(src, dst);
		return path;
	}

	/**
	 * common method for handling Alert popup
	 * @param driver
	 * @return
	 */
	public Alert alertShift(WebDriver driver)
	{

		Alert alert = driver.switchTo().alert();
		return alert;
	}
	/**
	 * Method used to accept or click on ok  in alert
	 * @param driver
	 */
	public void alertAccept(WebDriver driver)
	{
		alertShift(driver).accept();
	}

	/**
	 * Method is used to click on cancel in Alert
	 * @param driver
	 */
	public void alertDismiss(WebDriver driver)
	{
		alertShift(driver).dismiss();
	}
	/**
	 * Method is used to type casting from driver to javascript Executor
	 * @param driver
	 * @return
	 */
	public JavascriptExecutor jsTypeCast(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		return js;
	}
	/**
	 * Method is used to scroll to particular Element
	 * @param driver
	 * @param ele
	 */
	public void scrollToEleJs(WebDriver driver,WebElement ele)
	{
		jsTypeCast(driver).executeScript("arguments[0].scrollIntoView()",ele);
	}

	/**
	 * Method is used to scroll till the Bottom of the Page
	 * @param driver
	 */
	public void scrollToBottomJs(WebDriver driver)
	{
		jsTypeCast(driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 *  Method is used to scroll till the Top of the Page
	 * @param driver
	 */
	public void scrollToTopJs(WebDriver driver)
	{
		jsTypeCast(driver).executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	}

	/**
	 * scroll to element based on location
	 * @param x
	 * @param y
	 * @param driver
	 */
	public void scrollByLocJs(int x,int y,WebDriver driver)
	{
		jsTypeCast(driver).executeScript("window.scrollBy("+x+" "+y+")");
	}
	/**
	 * send Data into testField using javaScript
	 * @param driver
	 * @param element
	 * @param value
	 */
	public void enterDataToTextJs(WebDriver driver,WebElement element,String value)
	{
		jsTypeCast(driver).executeScript("arguments[0].value=arguments[1]",element,value);
	}
   /**
    * perform click action using javascript executor
    * @param driver
    * @param element
    */
	public void clickEleJs(WebDriver driver,WebElement element)
	{
		jsTypeCast(driver).executeScript("arguments[0].click()", element);
	}

	/**
	 * perform click action By ID Using javaScript Executor
	 * @param driver
	 * @param id
	 */
	public void clickEleByIdJs(WebDriver driver,String id)
	{
		jsTypeCast(driver).executeScript("document.getElementById("+id+").click()");
	}
	/**
	 * pass the data into disable textfield Using js
	 * @param driver
	 * @param id
	 * @param data
	 */
	public void passDataInDisTextJs(WebDriver driver,String id,String data)
	{
		jsTypeCast(driver).executeScript("document.getElementById("+id+").value="+data+"");
	}
	/**
	 * in this method we create an object for robot class
	 * @return
	 * @throws AWTException
	 */
	public Robot robobj() throws AWTException
	{
		Robot r=new Robot();
		return r;
	}
	/**
	 * this method used to press the enterkey
	 * @throws AWTException
	 */
	public void pressEnterKey() throws AWTException
	{
		robobj().keyPress(KeyEvent.VK_ENTER);
	}
	/**
	 * this method is used to release the enterkey
	 * @throws AWTException
	 */
	public void releaseEnterKey() throws AWTException
	{
		robobj().keyRelease(KeyEvent.VK_ENTER);
	}
	/**
	 * This method is used to switch the frame using index
	 * @param driver
	 * @param index
	 */
	public void switchFrameByIndex(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to switch the frame using element address
	 * @param driver
	 * @param element
	 */
	public void switchFrameByEle(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	/**
	 * In this method is used to switch the frame using id
	 * @param driver
	 * @param id
	 */
	public void switchFrameById(WebDriver driver,int id)
	{
		driver.switchTo().frame(id);
	}
	/**
	 * this method is used to switch the frame to default page
	 * @param driver
	 */
	public void switchFrameToDefault(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	/**
	 * This method is used to switch the frame to parent frame
	 * @param driver
	 * @param id
	 */
	public void switchFrameToParent(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}

}
