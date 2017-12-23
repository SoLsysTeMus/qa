package ru.stga.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stga.pft.addressbook.appmanager.ApplicationManager;


public class TestBase {

   public static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite
   public void tearDown() {
      app.stop();
   }

}
