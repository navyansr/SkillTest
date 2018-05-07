package com.accenture.skill.test;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.accenture.skill.ui.KitchenHomesPage;
import com.accenture.skill.ui.LondonHomesPage;
import com.accenture.skill.ui.LondonPage;
import com.accenture.skill.ui.MoreFilterPage;
import com.accenture.skill.ui.PricePage;
import com.accenture.skill.ui.PriceVerifyPage;
import com.accenture.skill.ui.SearchPage;
import com.accenture.skill.utils.CaptureScreenshot;
import com.accenture.skill.utils.GetData;
/*
 * Execution is very fast so added Thread.sleep() in few places for user to verify actions
 */
public class TestAccenture01 {
	
	private WebDriver driver;
	String URL = GetData.fromProperties("config", "URL");
		
	SearchPage searchPage;
	LondonPage londonPage;
	LondonHomesPage homePage;
	MoreFilterPage filterPage;
	KitchenHomesPage kitchenPage;
	PricePage pricePage;
	PriceVerifyPage pVerifyPage;
	
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./browser-drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	@BeforeMethod
	public void setPages(){
		searchPage  = new SearchPage(driver);
		londonPage  = new LondonPage(driver);
		homePage    = new LondonHomesPage(driver);
		filterPage  = new MoreFilterPage(driver);
		kitchenPage = new KitchenHomesPage(driver);
		pricePage   = new PricePage(driver);
		pVerifyPage = new PriceVerifyPage(driver);
	}
	@Test(priority=1)
	public void launchURL() {
		try{
			driver.get(URL);
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("launchURL exception "+e);
		}
	}
	
	@Test(priority=2)
	public void searchBox() {
		try {
			searchPage.waitForSearchPageToLoad();
			Thread.sleep(10000);
			WebElement searchText = searchPage.getSearchTextbox();
			searchText.sendKeys("London");
			Thread.sleep(2000);
			searchText.sendKeys(Keys.ENTER);
			Thread.sleep(12000);
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("searchBox exception "+e);
		}
	}
	@Test(priority=3)
	public void selectHome() {
		try {
			londonPage.getSearchBox().click();
			londonPage.waitForSearchDropDownToLoad();
			Thread.sleep(5000);
			londonPage.getHomeButton().click();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("selectHome exception "+e);
		}
	}
	
	@Test(priority=4)
	public void clickMoreFilters() {
		try {
			homePage.waitForMenuToLoad();
			Thread.sleep(4000);
			homePage.getMoreFilterButton().click();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("clickMoreFilters exception "+e);
		}
	}
	
	@Test(priority=5)
	public void kitchenCheckbox() throws InterruptedException{
		try {
			filterPage.waitForFilterPageToLoad();
			WebElement element    = filterPage.getKitchenCheckBox();
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(3000);
			element.click();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("kitchenCheckbox exception "+e);
		}
	}
	@Test(priority=6)
	public void showHomes(){
		try {
			filterPage.getShowHomeBtn().click();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("showHomes exception "+e);
		}
	}
	@Test(priority=7)
	public void clickPrice(){
		try {
			kitchenPage.waitForShowHomesPageToLoad();
			kitchenPage.getPriceButton().click();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("clickPrice exception "+e);
		}
	}
	@Test(priority=8)
	public void setLowerPrice() throws InterruptedException{
		try{
			Thread.sleep(5000);
			WebElement minBtn = pricePage.getMinPriceBtn();
			Actions move = new Actions(driver);
			move.dragAndDropBy(minBtn, 137, 0).build().perform();
			WebElement applyBtn = pricePage.getApplyBtn();
			applyBtn.click(); 
			pricePage.waitForPricePageToLoad();
			String expectedPrice = GetData.fromExcel("config", "test", 0, 1);
		
			String actualPrice = pricePage.getSelectedPrice().getText();
			Assert.assertEquals(actualPrice,expectedPrice);
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("setLowerPrice exception "+e);
		}
	}
	@Test(priority=9)
	public void verifyTopHomes() throws InterruptedException{
		Thread.sleep(10000);
		pVerifyPage.waitForVerifyPricePageToLoad();
		List<WebElement> elements = pVerifyPage.getHomeElements();
		Iterator<WebElement> itr  = elements.iterator();
		int num = 0;
		int total = Integer.parseInt(GetData.fromProperties("config", "number"));
		while (itr.hasNext()) {
			if (num < total){
				WebElement item = itr.next();
				String label = item.getText();
				int actualprice = Integer.parseInt(label.replace('£', ' ').trim());
				String price = GetData.fromExcel("config", "test", 0, 1).replace('+', ' ');
				int expectedPrice = Integer.parseInt(price.replace('£', ' ').trim());
				
				if (actualprice >= expectedPrice){
					System.out.println("Price of home is >=101 pounds, House = "+num+", "+label);
				}
				num++;
			} else {
				break;
			}
		}
	}
	
	@AfterMethod
	public void tearDown(ITestResult result){
		if(ITestResult.FAILURE == result.getStatus()){
			try{
				CaptureScreenshot.takeSnapShot(driver, "/screenshots/", result);
			} catch(Exception e){
				e.printStackTrace();
				System.out.println("Error in taking screenshot"+e.getMessage());
			}
		}
	}
	@AfterClass
	public void shutDown() {
		driver.quit();
	}
}
