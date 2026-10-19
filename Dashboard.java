package MIS_frontend;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;



public class Dashboard {
	

	private static final boolean flase = false;
	static WebDriver driver=new ChromeDriver();

	//check  if  person who had logged in. validate for name  ---get it from cookiee/local storage/Db=Mongo db

	
	//Tc#1  and TC#2 steps
	
	
	// 1.right click on web page and inspect  
	// 2.get all cookies
	// 3. within cokkies decode role and name attricute
	
	@Test(priority=1,enabled=flase)
	public void MIS_Dashboard_TC1() throws InterruptedException
	{

		driver.get("http://visualivrmis.simpragma.com/");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//1.login with valid credentials
		driver.findElement(By.id("email")).sendKeys("naveenr@simpragaam.com");
		driver.findElement(By.id("password")).sendKeys("Password@123");
		driver.findElement(By.tagName("button")).click();
		
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2. get token from cookie
		Cookie c =driver.manage().getCookieNamed("token");
		System.out.println(c.getValue());
		String  encodedToken=c.getValue();
		
		//3. decode the token
		String base64Payload = encodedToken.split("\\.")[1];
		String decodedPayload = new String(Base64.getDecoder().decode(base64Payload));
		System.out.println("Decoded payload: \n" + decodedPayload);
		
		//4.extract firstaname , lastname and role values
		LinkedHashMap<String,String> data =new LinkedHashMap<>();
		String str="";
		str+=decodedPayload;
		String[] keyVal=str.split(",");
		String firstname,lastname,role;
		
	
		String ar[]=keyVal[2].split(":");
		role=ar[1].substring(1).replace('"',' ');
		System.out.println(role);
			
			String ar1[]=keyVal[3].split(":");
			//System.out.println(ar[1].substring(1));
			firstname=ar1[1].substring(1).replace('"',' ');
			System.out.println(firstname);
			firstname=ar1[1].substring(1).replace('"',' ');	
		
			String ar2[]=keyVal[4].split(":");
			//System.out.println(ar[1].substring(1));
			lastname=ar2[1].substring(1).replace('"',' ');
			System.out.println(lastname);
			lastname=ar2[1].substring(1).replace('"',' ');	
			
			
			//5.click on logout avatar
			driver.findElement(By.xpath("//span[@class='sc-gqjmRU hNubjF']")).click();
			
			//6.verify name
			System.out.println(	driver.findElement(By.xpath("//div[@class='rainbow-m-left_x-small nameWrap']/span[@class='name']")).getText());
			String name[]=driver.findElement(By.xpath("//div[@class='rainbow-m-left_x-small nameWrap']/span[@class='name']")).getText().split(" ");
			String fn=name[0]+' ';
			String ln=name[1]+' ';
			System.out.println(name[0]+"=="+firstname+".");
			Assert.assertTrue(firstname.equals(fn));
			Assert.assertTrue(lastname.equals(ln));
			
			//7.verify role
			
			System.out.println(	driver.findElement(By.xpath("//div[@class='rainbow-m-left_x-small nameWrap']/span[@class='designation']")).getText());
			String roleName[]=driver.findElement(By.xpath("//div[@class='rainbow-m-left_x-small nameWrap']/span[@class='designation']")).getText().split(" ");
			String r1=roleName[0]+' ';
			String r2=roleName[1]+' ';
			String ur1=r1.toUpperCase();
			String ur2=r2.toUpperCase();
			System.out.println(ur1+"."+"--"+ur2+".");
		
			String roles[]=driver.findElement(By.xpath("//div[@class='rainbow-m-left_x-small nameWrap']/span[@class='designation']")).getText().split("_");
			Assert.assertTrue(ur1.contains("APP"));
			Assert.assertTrue(ur2.contains("ADMIN"));
			logout();
	}
	
	public  static void logout()
	{
			//click on logout button
			driver.findElement(By.xpath("(//li[@role='menuitem'])[2]")).click();
	}	
	
	
	//TC_3  :check if role is admin the billing  information and  usernmanagement option is present or not in vertical sidebar/menu
	//expected result:must not be present
	
	
	@Test(priority =3)
	public void Dashboard_MIS_TC_3()
	{
			driver.get("https://visualivrmis.simpragma.com");
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.manage().window().maximize();

			//1.login with valid credentials
			driver.findElement(By.id("email")).sendKeys("naveenr@simpragma.com");
			driver.findElement(By.id("password")).sendKeys("Password@123");
			driver.findElement(By.tagName("button")).click();


			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			//2.check role of user
			
			driver.findElement(By.xpath("//span[@class='sc-gqjmRU hNubjF']")).click();
			System.out.println(	driver.findElement(By.xpath("//div[@class='rainbow-m-left_x-small nameWrap']/span[@class='designation']")).getText());
			String roleName[]=driver.findElement(By.xpath("//div[@class='rainbow-m-left_x-small nameWrap']/span[@class='designation']")).getText().split(" ");
			String r1=roleName[0]+" ";
			String r2=roleName[1];
			r2+=" ";
			String ur1=r1.toUpperCase();
			String ur2=r2.toUpperCase();
			System.out.println(r1+"."+"--"+r2+".");
		
			String roles[]=driver.findElement(By.xpath("//div[@class='rainbow-m-left_x-small nameWrap']/span[@class='designation']")).getText().split("_");
			List<WebElement>menuItems=null;
			//3. if APP_ADMIN check if Billing information  and user management info is present or not
			if(ur2.contains("ADMIN_SUPER"))
			{
				// find menu side bar
			menuItems=driver.findElements(By.xpath("//div[@class='menuContainer']/ul/li[@class='menuItem']"));
				
			}
			for(WebElement e:menuItems)
				System.out.println(e.getText());
				int i=0;
			
				//Traverse till we reach billing option
				while(i<menuItems.size())
				{
					if(!menuItems.get(i).getText().equalsIgnoreCase("billing"))
					{
						//verify if billinhg oprtion is present or not
						Assert.assertFalse(menuItems.get(i).getText().equalsIgnoreCase("billing"));
					}
					
					if(menuItems.get(i).getText().equalsIgnoreCase("user management"))
					{
						//verify if billinhg oprtion is present or not
						Assert.assertFalse(menuItems.get(i).getText().equalsIgnoreCase("user management"));
					}
					i++;
				}
			
		
	}
		
	
//	for(int i=0;i<menuItems.size();i++)
//	{
//		System.out.println(menuItems.get(i).getText());
//		if(menuItems.get(i).getText().equals("Dashboard"))
//		{
//			//4.click on dashbord in sidebar and verify if we are at dashboard page by getting title/ heading zas dashboard
//			menuItems.get(i).click();	
//			Assert.assertTrue(driver.findElement(By.tagName("h4")).getText().equalsIgnoreCase("Dashboard"));
//		}
//	}
	
	
	
	
}
