package pages;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class TestFacebook {

	private AndroidDriver<WebElement> driver = null;
	private List<WebElement> buttons;
	private List<WebElement> edit;
	private List<WebElement> views;
	private Actions action;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "......................");

		capabilities.setCapability("browserName", "Appium");

		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("platformVersion", "5.1.1");

		capabilities.setCapability("appPackage", "com.facebook.katana");

		capabilities.setCapability("appActivity", "com.facebook.katana.LoginActivity");

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		buttons = driver.findElements(By.xpath("//android.widget.Button"));
		edit = driver.findElements(By.xpath("//android.widget.EditText"));

	}

	@Test
	public void Explore() throws Exception {
		 String user = "--------------"; //precisa settar aqui um loggin
		 String password = "------------"; //e a senha
		 edit.get(0).click();
		 edit.get(0).sendKeys(user);
		 edit.get(1).click();
		 edit.get(1).sendKeys(password);

		 buttons.get(0).click();

		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		 driver.findElement(ByName.name("What's on your mind?")).sendKeys("test3 !");
		 driver.findElement(ByName.name("POST")).click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.id("com.facebook.katana:id/friend_requests_tab")).click();
				// driver.navigate().back();
		driver.findElement(By.id("com.facebook.katana:id/notifications_tab")).click();
		
		// driver.navigate().back();
		driver.findElement(By.id("com.facebook.katana:id/bookmarks_tab")).click();

		List <WebElement>contents = driver.findElements(By.xpath("//com.facebook.fbui.widget.contentview.ContentView"));
		
		System.out.println(contents.get(3).getText());
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		do{
			scroll();
			scroll();
			scroll();
			scroll();
			scroll();
		}while(!checkElement(By.name("Log Out")));
		
		driver.findElement(By.name("Log Out")).click();
		
		
	}

	
	
	private boolean checkElement(By name) {
		
		try{
			driver.findElement(name);
			return true;
		}catch (NoSuchElementException e) {
			    
			return false;
		}				
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	public void scroll() throws IOException {
		Dimension dim = driver.manage().window().getSize();
		int start = (int) (dim.getHeight()*0.8);
		System.out.println("h "+ start);
		int end = (int) (dim.getHeight()*0.1);
		driver.swipe(0, start, 0, end, 500);
	}
	
	
	

}
