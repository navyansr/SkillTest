package com.accenture.skill.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PriceVerifyPage {
	WebDriver driver;
	
	public PriceVerifyPage(WebDriver driver){
		this.driver = driver;
	}

	public void waitForVerifyPricePageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='site-content']/div/div/div[2]/div/div/div/div[2]/div[1]")));
	}
	
	public List<WebElement> getHomeElements(){
		return driver.findElements(By.xpath("//span[text()='Price']/../../span[2]"));
	}
}
