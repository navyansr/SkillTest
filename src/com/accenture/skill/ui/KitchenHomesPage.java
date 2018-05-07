package com.accenture.skill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KitchenHomesPage {
	
	WebDriver driver;
	
	public KitchenHomesPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void waitForShowHomesPageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@aria-label='1 filter applied']")));
	}
	public void waitForAlert(){
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	public WebElement getPriceButton(){
		return driver.findElement(By.xpath("//button[@aria-controls='menuItemComponent-price_range']"));
	}
}
