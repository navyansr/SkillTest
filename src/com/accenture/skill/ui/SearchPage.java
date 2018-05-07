package com.accenture.skill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	WebDriver driver;
	
	public SearchPage(WebDriver driver){
		this.driver = driver;
	}

	public void waitForSearchPageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("site-content")));
	}
	
	public WebElement getSearchTextbox(){
		return driver.findElement(By.id("GeocompleteController-via-SearchBarV2-SearchBarV2"));
	}
}
