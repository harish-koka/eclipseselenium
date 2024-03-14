package SeleniumDemo.Proj;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CodecPolicy {
    String website = "http://20.55.216.158";
    String user = "admin";
    String pwd = "Abcd1234";
    String xpathUser = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/oj-module[1]/div[1]/div[2]/oj-module[1]/div[1]/div[3]/oj-input-text[1]/div[1]/div[1]/input[1]";
    String xpathpwd = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/oj-module[1]/div[1]/div[2]/oj-module[1]/div[1]/div[3]/oj-input-password[1]/div[1]/div[1]/input[1]";
    String xpathSignin ="//oj-button[@id='loginbtn']//div[@class='oj-button-label']";
    String xpathconfigaration = "//span[@class='oj-navigationlist-item-label'][normalize-space()='Configuration']";
    String xpathcodecpolicy = "//a[contains(text(),'codec-policy')]";
    String xpathintialadd = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/oj-module[1]/div[1]/div[7]/oj-module[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/oj-module[1]/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/div[2]/oj-button[1]/button[1]/div[1]/span[1]";
    String idDeleteAll = "deleteAll_codec-policy_buttonText";
    String xpathconfirmDelete = "//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]";

    public void signIN(WebDriver driver) throws InterruptedException {
        driver.get(website);
        Thread.sleep(3000);
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='inputusername|input']")));
        WebElement userName = driver.findElement(By.xpath(xpathUser));
        WebElement password = driver.findElement(By.xpath(xpathpwd));
//      login into application.
        userName.sendKeys(user);
        password.sendKeys(pwd);
        Thread.sleep(10000);
        driver.findElement(By.xpath(xpathSignin)).click();
        Thread.sleep(5000);
    }
//    Goto configuration and CodecPolicy
    public void configuration(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath(xpathconfigaration)).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath(xpathcodecpolicy)).click();
        Thread.sleep(3000);
    }
//    Add Codec Policy
    public boolean addPolicy(WebDriver driver, String policyname, String codec1, String codec2) throws InterruptedException {
        //           Add new codec policy.
        driver.findElement(By.xpath(xpathintialadd)).click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
        name.sendKeys(policyname);
        WebElement codecs = driver.findElement(By.xpath("//input[@id='allow-codecs|input']"));
        codecs.sendKeys(codec1);
        codecs.sendKeys(Keys.ENTER);
        codecs.sendKeys(codec2);
        codecs.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
//                Checking if codec policy added successfully.
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + policyname + "')]"));
        return element != null;
   }

    public boolean editPolicy(WebDriver driver, String name, String New_Name) throws InterruptedException {
        driver.findElement(By.xpath("//oj-selector[@id='codec-policy1_table_selector_" + name + "']//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//span[@id='codec-policy1_editAction']")).click();
        Thread.sleep(3000);
        WebElement nameField = driver.findElement(By.xpath("//input[@id='name|input']"));
        nameField.clear();
        nameField.sendKeys(New_Name);
        driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
//                Checking if codec policy is edited successfully.
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(), '" + New_Name + "')]"));
        return element1 != null;
    }
//    Delete Codec Policy
    public boolean deletePolicy(WebDriver driver, String policyname) throws InterruptedException {
        WebElement checkBox = driver.findElement(By.xpath("//oj-selector[@id='codec-policy1_table_selector_" + policyname + "']//input[@type='checkbox']"));
        checkBox.click();
        Thread.sleep(3000);
        WebElement delete = driver.findElement(By.id("codec-policy1_deleteAction"));
        delete.click();
        WebElement confirm = driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]"));
        Thread.sleep(3000);
        confirm.click();
        Thread.sleep(3000);
        return true;
    }
//Add Multiple codec policies
    public void addMultiplePolicies(WebDriver driver, String policyName, String codec1, String codec2,int n) throws InterruptedException {
        //String PolicyName;
        for (int i = 0; i <=n; i++) {
            if (i == 0) {
                driver.findElement(By.xpath(xpathintialadd)).click();
            }
            else {
                driver.findElement(By.id("add_codec-policy")).click();
                }
            WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
            name.sendKeys(policyName+i);
            WebElement codecs = driver.findElement(By.xpath("//input[@id='allow-codecs|input']"));
            codecs.sendKeys(codec1);
            codecs.sendKeys(Keys.ENTER);
            codecs.sendKeys(codec2);
            codecs.sendKeys(Keys.ENTER);
            driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
        }
    }
//    Delete all codec policies
        public boolean deleteAllCodecPolicies(WebDriver driver) throws InterruptedException {
            WebElement delete = driver.findElement(By.id(idDeleteAll ));
            if(delete!=null) {
                delete.click();
                Thread.sleep(2000);
                driver.findElement(By.xpath(xpathconfirmDelete)).click();
                Thread.sleep(3000);
                return true;
            }
            else{
                return false;
            }
        }

//Copy codec policy and add it with a different name.
            public void copySaveWithNewName(WebDriver driver,String policyname) throws InterruptedException {
                WebElement button = driver.findElement(By.xpath("//oj-selector[@id='codec-policy1_table_selector_policy1']//input[@type='checkbox']"));
                button.click();
                Thread.sleep(3000);
                WebElement copy = driver.findElement(By.id("codec-policy1_copyAction"));
                copy.click();
                Thread.sleep(3000);
                WebElement name1 = driver.findElement(By.xpath("//input[@id='name|input']"));
                name1.clear();
                String copiedpolicy = "policy6";
                Thread.sleep(2000);
                name1.sendKeys(copiedpolicy);
                Thread.sleep(3000);
                driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
            }
            public void cleanUp(WebDriver driver) throws InterruptedException {
            try {
                driver.findElement(By.xpath("//span[normalize-space()='media-manager']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//a[contains(text(),'codec-policy')]")).click();
                Thread.sleep(2000);
                boolean Delete = deleteAllCodecPolicies(driver);
                if (Delete) {
                    System.out.println("Cleanup Done.");
                } else {
                    System.out.println("Error deleting codec policies.");
                }
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
            }
            public void logOut(WebDriver driver) throws InterruptedException {
                driver.findElement(By.xpath("//button[@aria-label='admin menu']//span[@class='oj-button-icon oj-end oj-component-icon oj-button-menu-dropdown-icon']")).click();
                Thread.sleep(3000);
                driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/oj-menu[1]/oj-option[3]/a[1]/span[1]")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[@class='oj-button-text']")).click();
                Thread.sleep(2000);
                driver.quit();
            }
            public void deleteSelective(WebDriver driver, String nameOfPolicy, int n) throws InterruptedException {
                for(int i=0;i<=n;i++){
                    String xpathRadio = "//oj-selector[@id='codec-policy1_table_selector_" +nameOfPolicy+i+"']//input[@type='checkbox']";
                    driver.findElement(By.xpath(xpathRadio)).click();
                    Thread.sleep(3000);
                    driver.findElement(By.xpath("//button[@aria-label='Codec Policy Delete']//div[@class='oj-button-label']")).click();
                    Thread.sleep(3000);
                    driver.findElement(By.xpath("//oj-button[@id='dialogConfirmBtn']//span[contains(text(),'Confirm')]")).click();
                    Thread.sleep(3000);
                    System.out.println("one Codec policy successfully deleted");
                }

            }

            public boolean searchPolicy(WebDriver driver,String policyname){
                WebElement search = driver.findElement(By.xpath("//input[@id='searchid|input']"));
                search.sendKeys(policyname);
                driver.findElement(By.id("tableSearchButton")).click();
                WebElement emptyDataIcons = driver.findElement(By.xpath("//div[@id='codec-policy1_emptyDataIcons']"));
                if (emptyDataIcons.isDisplayed()) {
                    System.out.println("Policy '" + policyname + "' is Not Available");
                    return false;
                }
                else{
                    System.out.println("Policy '" + policyname + "' is Available");
                    return true;
                }

            }

            public void enterPolicyAndPressBack(WebDriver driver,String policyname) throws InterruptedException {
                driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/oj-module[1]/div[1]/div[7]/oj-module[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/oj-module[1]/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/div[2]/oj-button[1]/button[1]/div[1]/span[1]")).click();
                WebElement name = driver.findElement(By.xpath("//input[@id='name|input']"));
                name.sendKeys("policyname");
                driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
                Thread.sleep(3000);
                driver.findElement(By.xpath("//oj-button[@id='dialogYesBtn']//span[contains(text(),'Yes')]")).click();
                Thread.sleep(3000);
                WebElement add = driver.findElement(By.xpath(xpathintialadd));
                if(add!=null){
                    System.out.println("Policy Not Added");
                }
                else {
                    System.out.println("Policy is Added");
                }


            }
            public void policyWithEmptyName(WebDriver driver) throws InterruptedException {
                driver.findElement(By.xpath(xpathintialadd)).click();
                Thread.sleep(3000);
                driver.findElement(By.xpath("//span[@data-bind='text: label'][normalize-space()='OK']")).click();
                Thread.sleep(3000);
                WebElement error = driver.findElement(By.xpath("//span[@id='error_name']"));
                if(error!=null){
                    System.out.println("Red Button Displayed");
                }
                else {
                    System.out.println("Red Button Not Displayed");
                }
            }



}
