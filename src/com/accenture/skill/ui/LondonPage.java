package com.accenture.skill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LondonPage {
	
	WebDriver driver;
	
	public LondonPage(WebDriver driver){
		this.driver = driver;
	}
	
	/*public void waitForLondonPageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("site-content")));
	}*/
	public void waitForSearchDropDownToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("GeocompleteController-via-SearchBarV2-SearchBarV2__listbox")));
	}
	public WebElement getSearchBox(){
		return driver.findElement(By.id("GeocompleteController-via-SearchBarV2-SearchBarV2"));
	}
	public WebElement getHomeButton(){
		return driver.findElement(By.cssSelector("#GeocompleteController-via-SearchBarV2-SearchBarV2__option-homes"));
	}

}
