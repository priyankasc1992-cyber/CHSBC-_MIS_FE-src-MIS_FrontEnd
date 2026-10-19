package MIS_frontend;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MIS_Login {
	
	 		WebDriver driver=new ChromeDriver();
	//Tc_1
	//steps: naviagte to visualivrmis.simpragma.com 
	// expected result:  should open login  page with welcome message  with email id and password textfield and login button
	@Test(priority=1)
	public  void TC_1()
	{

		//1. navigate to page
		driver.get("http://visualivrmis.simpragma.com/");
		//2.check if email , password   text field and login btn are present with Welcome text
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebElement email=driver.findElement(By.id("email"));
		WebElement password=driver.findElement(By.id("password"));
		WebElement loginBtn=driver.findElement(By.tagName("button"));
		Assert.assertTrue(email.isDisplayed());
		Assert.assertTrue(password.isDisplayed());
		Assert.assertTrue(loginBtn.isDisplayed());
		
	}
	//Tc_2
	// steps leave email id empty and enter pwd click on login btn
	//1. navigate to page
	@Test(priority=2)
	public void TC_2()
	{

		
		
		//2.enter password
		driver.findElement(By.id("password")).sendKeys("Password@123");
		//3.click on login
		driver.findElement(By.tagName("button")).click();	
		//4.check if it error text msg is displayed
		if(driver.findElement(By.id("email")).getAttribute("value").isEmpty())
		{
			Assert.assertTrue(driver.findElement(By.tagName("ui-error")).getText().equals("Please enter your email"));
		}
	
		
	}
	//Tc3
	//leave pwd textfield blank and eneter email id click on login btn
	@Test(priority=3)
	public void TC_3()
	{

		//clear  previous  data eneterd
		driver.navigate().refresh();
		//2.enter email
		driver.findElement(By.id("email")).sendKeys("naveenr@gamil.com");
		//3.click on login
		driver.findElement(By.tagName("button")).click();
		//4.check if it error text msg is displayed
		boolean res=driver.findElement(By.id("password")).getAttribute("value").isEmpty();
		System.out.println(res);
		if(res)
			Assert.assertTrue(driver.findElement(By.tagName("ui-error")).getText().equals("Please enter your password"));
		
	}

	//  tc4
	//leave both empty
	// expected: both error messages for email and pwd are dispalyed
	@Test(priority=4)
	public void TC_4() throws InterruptedException
	{
				//clear  previous  data eneterd
				driver.navigate().refresh();
				driver.findElement(By.id("email")).sendKeys("");
				driver.findElement(By.id("password")).sendKeys("");
				//3.click on login
				driver.findElement(By.tagName("button")).click();
				if(driver.findElement(By.id("email")).getAttribute("value").isEmpty())
				{
					Assert.assertTrue(driver.findElement(By.xpath("(//ui-error)[1]")).getText().equals("Please enter your email"));
				}
				if(driver.findElement(By.id("password")).getAttribute("value").isEmpty())
				{
					Thread.sleep(1000);
					Assert.assertTrue(driver.findElement(By.xpath("(//ui-error)[2]")).getText().equals("Please enter your password"));
				}
	
	}
	
	//tc5
	//check wether email id text field place holder value is 'Email'
	@Test(priority=5)
	public void TC_5()
	{
				//clear  previous  data eneterd
				driver.navigate().refresh();
			
				Assert.assertTrue(driver.findElement(By.id("email")).getAttribute("placeholder").equals("Email"));
	}
	//tc6
	//check wether password id text field place holder value is 'Password'
	@Test(priority=6)
	public void TC6() 
	{
		//clear  previous  data eneterd
		driver.navigate().refresh();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				System.out.println(driver.findElement(By.id("password")).getAttribute("placeholder"));
			Assert.assertTrue(driver.findElement(By.id("password")).getAttribute("placeholder").equals("Password"));
	}
	
	//Tc8
	// enter invalid email id and  pwd
	
	@Test(priority=8)
	public  void   TC_8() 
	{
		//clear  previous  data eneterd
		driver.navigate().refresh();
		
		driver.findElement(By.id("email")).sendKeys("priyanka@simpragma.com");
		
		driver.findElement(By.id("password")).sendKeys("132324");
		
		driver.findElement(By.tagName("button")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(driver.findElement(By.cssSelector("div.go3958317564")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='ERROR_INVALID_CREDENTIALS']")).getText().equals("ERROR_INVALID_CREDENTIALS"));
		
	}
	
	//Tc_9
	// enter only aphabets--email
	@Test(priority=9)
	public void tc_9()
	{
	driver.navigate().refresh();
		//1.enter only alphabets
		driver.findElement(By.id("email")).sendKeys("hfjkshfhjshdfjhjhfjhjkhfjkhdjkhjkjsdkjhkjsjdjkfhjfjhsdjkdhfjsdhfjkhjkdhjh");
		
		//click on password text field to get error msg for invalid  email
		driver.findElement(By.id("password")).click();
		
		// validate for error message "Please enter a valid email"
		Assert.assertTrue(driver.findElement(By.xpath("(//ui-error)[1]")).getText().equals("Please enter a valid email"));
		
	}
	
	//Tc_10
	//enter digits only
	@Test(priority=10)
	public void tc_10()
	{
	driver.navigate().refresh();
		//1.enter only alphabets
		driver.findElement(By.id("email")).sendKeys("12345678");
		
		//click on password text field to get error msg for invalid  email
		driver.findElement(By.id("password")).click();
		
		// validate for error message "Please enter a valid email"
		Assert.assertTrue(driver.findElement(By.xpath("(//ui-error)[1]")).getText().equals("Please enter a valid email"));
		
	}
	
	//Tc_11
	// eneter invalid email id without @
	@Test(priority=11)
	public void tc_11()
	{
	driver.navigate().refresh();
		//1.enter only alphabets
		driver.findElement(By.id("email")).sendKeys("Priyanka1234");
		
		//click on password text field to get error msg for invalid  email
		driver.findElement(By.id("password")).click();
		
		// validate for error message "Please enter a valid email"
		Assert.assertTrue(driver.findElement(By.xpath("(//ui-error)[1]")).getText().equals("Please enter a valid email"));
		
	}
	
	//Tc_12:enter invalid email id and valid password
	
	@Test(priority=12)
	public void  Tc_12()
	{
		//clear  previous  data eneterd
				driver.navigate().refresh();
				
				driver.findElement(By.id("email")).sendKeys("priyanka@simpragma.com");
				
				driver.findElement(By.id("password")).sendKeys("Passwod@123");
				
				driver.findElement(By.tagName("button")).click();
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(driver.findElement(By.cssSelector("div.go3958317564")).getText());
				Assert.assertTrue(driver.findElement(By.xpath("//div[text()='ERROR_INVALID_CREDENTIALS']")).getText().equals("ERROR_INVALID_CREDENTIALS"));

	}
	
	//TC_13 enter other special chars excluding @
	@Test(priority=13)
	public void Tc_13()
	{
		driver.navigate().refresh();
		//1.enter only alphabets
		driver.findElement(By.id("email")).sendKeys("!#$%^&*();:\"'|\\\\?<>,.");
		
		//click on password text field to get error msg for invalid  email
		driver.findElement(By.id("password")).click();
		
		// validate for error message "Please enter a valid email"
		Assert.assertTrue(driver.findElement(By.xpath("(//ui-error)[1]")).getText().equals("Please enter a valid email"));
	}
	//Tc_14:once you are logged in and on dashboard page navigate back 
		//Expected result:we should be naviagting to same dashoard page 
		@Test(priority=14)
		public void Tc_14()
		{

			driver.get("http://visualivrmis.simpragma.com/");
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			//1.login with valid credentials
			driver.findElement(By.id("email")).sendKeys("krishna@rao.com");
			driver.findElement(By.id("password")).sendKeys("Password@123");
			driver.findElement(By.tagName("button")).click();
			
			
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//2.navigate back
			driver.navigate().back();
			
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//3. verify if your are on dashboard page
			Assert.assertTrue(driver.findElement(By.tagName("h4")).getText().equalsIgnoreCase("Dashboard"));
			//5.click on logout avatar
			driver.findElement(By.xpath("//span[@class='sc-gqjmRU hNubjF']")).click();
			//click on logout button
			driver.findElement(By.xpath("(//li[@role='menuitem'])[2]")).click();
		}
		
		//Tc_15-->Fail
		
		//Tc_16-->Fail
		
		//Tc_17:once you are logged in and on dashboard page open in new tab login page
		//expected resultshould automatically navigate to dashboard page
		@Test(priority=17)
		public void TC_17()
		{

			driver.get("http://visualivrmis.simpragma.com/");
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			//1.login with valid credentials
			driver.findElement(By.id("email")).sendKeys("krishna@rao.com");
			driver.findElement(By.id("password")).sendKeys("Password@123");
			driver.findElement(By.className("btnLogin")).click();
			
			
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//2.open new tab by holding control+t keys in keyboard for chrome browser
					driver.switchTo().newWindow(WindowType.TAB);
			//3. enter url of MIS login page
					driver.get("https://visualivrmis.simpragma.com/");
					
					try {
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			//4.check if we are navigating to dashboard
		
			Assert.assertTrue(driver.findElement(By.tagName("h4")).getText().equalsIgnoreCase("Dashboard"));
			
			//5.click on logout avatar
			driver.findElement(By.xpath("//span[@class='sc-gqjmRU hNubjF']")).click();
			//click on logout button
			driver.findElement(By.xpath("(//li[@role='menuitem'])[2]")).click();
		}

	//Tc#18 enter data given test data
	@Test(priority=18)
	public void TC_18()
	{
		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("<script>@123.com");
		driver.findElement(By.className("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("(//ui-error)[1]")).getText().equals("Please enter a valid email"));
		
	}
	//Tc 20:enter alphabets only in password text field
	@Test(priority=20)
	public void TC_20()
	{
		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("krishna@rao.com");
		driver.findElement(By.id("password")).sendKeys("hfjkshfhjshdfjhjhfjhjkhfjkhdjkhjkjsdkjhkjsjdjkfhjfjhsdjkdhfjsdhfjkhjkdhjh");
		driver.findElement(By.className("btnLogin")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='ERROR_INVALID_CREDENTIALS']")).getText().equals("ERROR_INVALID_CREDENTIALS"));

		
	}
	
	//TC_21:enter digits only in password text field
	@Test(priority=21)
	public void TC_21()
	{
		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("krishna@rao.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.className("btnLogin")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='ERROR_INVALID_CREDENTIALS']")).getText().equals("ERROR_INVALID_CREDENTIALS"));

	}

	
}