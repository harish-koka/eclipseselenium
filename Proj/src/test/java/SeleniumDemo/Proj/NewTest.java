package SeleniumDemo.Proj;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {

 //   WebDriver driver = new ChromeDriver();
 //  CodecPolicy cp = new CodecPolicy();
    
  @Test(priority=2,description="This tests add edit delete multiple entries of codecpolicy")

  public void addEditDelMulti() throws InterruptedException {
	  WebDriver driver = new ChromeDriver();
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      CodecPolicy cp = new CodecPolicy();
      try {
    	  
          cp.signIN(driver);
          cp.configuration(driver);
          cp.addMultiplePolicies(driver, "policy name", "AMR", "G729", 5);
          boolean deleteAll = cp.deleteAllCodecPolicies(driver);
          assert deleteAll;
      }
      catch (InterruptedException e) {
          System.out.println(e);
      }finally {
    	  //cp.cleanUp(driver);
		  cp.logOut(driver);
	  }

  }
  
  @Test(priority=1, description="This tests add edit delete of codecpolicy")
  public void addEditDel() throws InterruptedException {
	  WebDriver driver = new ChromeDriver();
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      CodecPolicy cp = new CodecPolicy();
      cp.signIN(driver);

	  try {
	     cp.signIN(driver);
	      cp.configuration(driver);
	      Thread.sleep(3000);
	      boolean success = cp.addPolicy(driver,"policy1","AMR","G729");
	      assert success;
	      boolean edit= cp.editPolicy(driver,"policy1","policy2");
	      assert edit;
	      boolean delete =cp.deletePolicy(driver,"policy2");
	      assert delete;
	      }
	  catch (InterruptedException e) {
	      System.out.println(e);
	  } finally {
		 //cp.cleanUp(driver);
		  cp.logOut(driver);
	  }

  }
  
  
  @BeforeTest
  public void beforeTest() {

  }

  @AfterTest
  public void afterTest() throws InterruptedException {
	 // cp.cleanUp(driver);
  }

  @BeforeSuite
  public void beforeSuite() throws InterruptedException {

  }
  

  @AfterSuite
  public void afterSuite() throws InterruptedException {
	 // cp.logOut(driver);
  }

  }
  
  
  

