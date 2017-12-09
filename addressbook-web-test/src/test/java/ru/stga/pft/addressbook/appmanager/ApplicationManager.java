package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

   WebDriver wd;


   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private SessionHelper sessionHelper;
   private ContactHelper contactHelper;
   private String browser;


   public ApplicationManager(String browser) {
      this.browser = browser;
   }

   public void init() {

      if (browser.equals(BrowserType.FIREFOX)) {
         System.setProperty("webdriver.gecko.driver", "geckodriver");
         wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
         System.setProperty("webdriver.chrome.driver", "chromedriver");
         wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
         System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
         wd = new InternetExplorerDriver();
      }


      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/index.php");

      groupHelper = new GroupHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.login("admin", "secret");
      contactHelper = new ContactHelper(wd);
   }


   public void stop() {
      wd.quit();
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }

   public ContactHelper getContactHelper() {
      return contactHelper;
   }
}
