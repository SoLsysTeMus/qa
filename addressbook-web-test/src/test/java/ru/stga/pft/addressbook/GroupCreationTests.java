package ru.stga.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class GroupCreationTests {
   FirefoxDriver wd;

   @BeforeMethod
   public void setUp() throws Exception {
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/solsystem/firefox-esr/firefox"));
      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      wd.get("http://192.168.1.9/addressbook/index.php");
      login("admin", "secret");
   }

   private void login(String username, String password) {
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
   }

   @Test
   public void testGroupCreation() {
      gotoGroupPage();
      initGroupCreation();
      fillGroupForm(new GroupData("test1", "test2", "test3"));
      submintGroupCreation("submit");
      returnGroupPage("group page");
   }

   private void returnGroupPage(String s) {
      wd.findElement(By.linkText(s)).click();
   }

   private void submintGroupCreation(String submit) {
      wd.findElement(By.name(submit)).click();
   }

   private void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).click();
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
   }

   private void initGroupCreation() {
      wd.findElement(By.name("new")).click();
   }

   private void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
   }

   @AfterMethod
   public void tearDown() {
      wd.quit();
   }

   public static boolean isAlertPresent(FirefoxDriver wd) {
      try {
         wd.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }
}
