package SeleniumDemo.Proj;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEditDelete {
        public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://google.com");
        //CodecPolicy cp = new CodecPolicy();
//        try {
//           cp.signIN(driver);
//            //Configuration and click Codec Policy.
//            cp.configuration(driver);
//            Thread.sleep(3000);
//            boolean success = cp.addPolicy(driver,"policy1","AMR","G729");
//            assert success;
////              Editing the codec policy.
//            boolean edit= cp.editPolicy(driver,"policy1","policy2");
//            assert edit;
////                Deleting the codec policy.
//            boolean delete =cp.deletePolicy(driver,"policy2");
//            assert delete;
//            }
//            catch (StaleElementReferenceException e){
//                System.out.println(e);
//            }
//        catch (InterruptedException e) {
//            System.out.println(e);
//        }
//        finally {
//            cp.logOut(driver);
//        }
//

        }

    }






