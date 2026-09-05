package com.automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    // Common utility methods using WebElement
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public void hover(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).perform();
    }

    public void waitForVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void manageTable(WebElement element) {
    	List<WebElement> rows = element.findElements(By.tagName("tr"));
    	for(int i=0;i<rows.size();i++) {
    		String cellTage = (i==0)?"th":"td";
    		List<WebElement> cellValues = rows.get(i).findElements(By.tagName(cellTage));
    		for(WebElement cellVal : cellValues) {
    			System.out.print("| "+cellVal.getText());
    		}
    		System.out.println("|");   		
    	}
    }
    
    public void staticDropDown(WebElement element) {
    	Select dd = new Select(element);
    	List<WebElement> countries = dd.getOptions();
    	for(WebElement country : countries) {
    		if(country.getText().equals("India")) {
    			
    			dd.selectByContainsVisibleText("India");
    			break;
    		}
    	}
    }
    
}