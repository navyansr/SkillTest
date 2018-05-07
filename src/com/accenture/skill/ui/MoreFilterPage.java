package com.accenture.skill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoreFilterPage {
	
	WebDriver driver;
	
	public MoreFilterPage(WebDriver driver){
		this.driver = driver;
	}
	public void waitForFilterPageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='menu']")));
	}
	public WebElement getKitchenCheckBox(){
		return driver.findElement(By.xpath("//input[@name='amenities-8']/../span"));
	}
	
	public WebElement getShowHomeBtn(){
		return driver.findElement(By.xpath("//span[contains(text(),'Show homes')]"));
	}

}
