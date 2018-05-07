package com.accenture.skill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LondonHomesPage {
	
	WebDriver driver;
	
	public LondonHomesPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void waitForMenuToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='menubar']")));
	}
	
	public WebElement getMoreFilterButton(){
		return driver.findElement(By.xpath("//span[contains(text(),'More filters')]"));
	}

}
