package com.accenture.skill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricePage {
	
	WebDriver driver;
	
	public PricePage(WebDriver driver){
		this.driver = driver;
	}
	public void waitForPricePageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[text()='£101+']")));
	}
	public WebElement getMinPriceBtn(){
		return driver.findElement(By.xpath("//button[@aria-label='Minimum Price']"));
	}
	public WebElement getSelectedPrice(){
		return driver.findElement(By.xpath("//div[text()='£101+']"));
	}
	public WebElement getApplyBtn(){
		return driver.findElement(By.xpath("//span[text()='Apply']/.."));
	}
}
