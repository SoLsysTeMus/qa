package ru.stga.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stga.pft.addressbook.appmanager.ApplicationManager;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class TestBase {

   public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
   Logger logger = LoggerFactory.getLogger(TestBase.class);

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() {
      app.stop();
   }

   @BeforeMethod
   public void logTestStart(Method m){
      logger.info("Start test " + m.getName());
   }

   @AfterMethod(alwaysRun = true)
   public void logTestStop(Method m){
      logger.info("Stop test " + m.getName());
   }

   public void verifyGroupListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
         logger.info("Enabled verifyUI");
         Groups dbGroupList = app.db().groups();
         Groups uiGroupList = app.group().all();
         assertThat(uiGroupList, equalTo(dbGroupList.stream().map((g) -> new GroupData()
                 .withId(g.getId())
                 .withName(g.getName()))
                 .collect(Collectors.toSet())));
      }
   }

   public void verifyContactListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
         logger.info("Enabled verifyUI");
         Contacts dbContactList = app.db().contacts();
         Contacts uiContactsList = app.contacts().all();
         assertThat(uiContactsList, equalTo(dbContactList.stream().map((g) -> new ContactData()
                 .withId(g.getId())
                 .withFirstName(g.getFirstName())
                 .withLastName(g.getLastName())
                 .withAddress(g.getAddress())
                 .withAllEmails(g.getFirstEmail() + g.getSecondEmail() + g.getThirdEmail())
                 .withAllPhones(g.getHomePhone() + g.getWorkPhone() + g.getMobilePhone()))
                 .collect(Collectors.toSet())));
      }
   }
}
