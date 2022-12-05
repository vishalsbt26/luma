package com.luma.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.luma.base.TestBase;

public class HomePage extends TestBase {
	private static final Logger logger = LogManager.getLogger(HomePage.class);

	// PageFactory
	@FindBy(xpath = "//span[contains(text(),'Shop New Yoga')]")
	WebElement btn1;

//	@FindBy(id = "ui-id-6")
//	WebElement hoverMenuBar;

	@FindBy(id = "//div[@id='store.menu']")
	WebElement menuBar;

	@FindBy(id = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/nav[1]/ul[1]/li")
	WebElement hoverMenuBar;

	@FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/nav[1]/ul[1]/li[4]/ul[1]/li")
	WebElement menuBarDd;

	// initialize PageFactory
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void homePageVerify() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (btn1.isDisplayed()) {
			js.executeScript("scroll(0,1680)");

			List<WebElement> products = driver.findElements(By.xpath("//div[@class='products-grid grid']/ol/li"));
			System.out.println("There are " + products.size() + " products");

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			js.executeScript("scroll(0,-1680)");

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("homepage verify");
	}

	public void productList() {
		Actions action = new Actions(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// MenuBar
		List<WebElement> listBar = driver
				.findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/nav[1]/ul[1]/li"));

		for (int i = 0; i < listBar.size(); i++) {
			String text = listBar.get(i).getText();

			if (text.equals("Gear")) {
				WebElement x = listBar.get(i);
				action.moveToElement(x).build().perform();
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// MenuBar Inside Options
				List<WebElement> list = driver
						.findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/nav[1]/ul[1]/li[4]/ul[1]/li"));

		for (int i = 0; i < list.size(); i++) {
			String txt = list.get(i).getText();

			if (txt.contains("Watches")) {
				list.get(i).click();
			}
		}
		logger.info("navigate to product list page");
	}
}