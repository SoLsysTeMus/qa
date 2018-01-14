package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

   private Contacts contactsCache = null;

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void submintContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
      contactsCache = null;
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("address"), contactData.getAddress());
      type(By.name("home"), contactData.getHomePhone());
      type(By.name("email"), contactData.getFirstEmail());
      type(By.name("email2"), contactData.getSecondEmail());
      type(By.name("email3"), contactData.getThirdEmail());

      if (contactData.getPhoto() != null) {
         attach(By.name("photo"), contactData.getPhoto());
      }
      if (creation) {
         if (contactData.getGroups().size() > 0) {
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
         }
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void initNewContact() {
      click(By.linkText("add new"));
   }

   public void selectContactById(int id) {
      wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
   }

   public void deleteSelectedContact() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
      wd.switchTo().alert().accept();
      contactsCache = null;
   }

   public void modificateContactById(int id) {
      wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
   }

   public void submintContactModification() {
      click(By.name("update"));
      contactsCache = null;
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public void create(ContactData contact, boolean creation) {
      initNewContact();
      fillContactForm(contact, creation);
      submintContactCreation();
      contactsCache = null;
   }

   public int count() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public Contacts all() {

      if (contactsCache == null) {
         contactsCache = new Contacts();

         List<WebElement> allRows = getAllRowsFromContactTable();

         for (WebElement row : allRows) {

            List<WebElement> cells = row.findElements(By.tagName("td"));

            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String email = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contactsCache.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmails(email));
         }
      }

      return contactsCache;
   }

   public List<WebElement> getAllRowsFromContactTable() {
      WebElement table = wd.findElement(By.id("maintable"));
      return table.findElements(By.name("entry"));
   }

   public ContactData infoFromEditForm(ContactData contact) {
      modificateContactById(contact.getId());
      String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
      String address = wd.findElement(By.name("address")).getAttribute("value");
      String home = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      String firstEmail = wd.findElement(By.name("email")).getAttribute("value");
      String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
      String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
      return new ContactData()
              .withId(contact.getId())
              .withFirstName(firstName)
              .withLastName(lastName)
              .withAddress(address)
              .withHomePhone(home)
              .withMobilePhone(mobile)
              .withWorkPhone(work)
              .withFirstEmail(firstEmail)
              .withSecondEmail(secondEmail)
              .withThirdEmail(thirdEmail);
   }

   public void submitSelectContactsToGroup() {
      click(By.xpath("//input[@value='Add to']"));
   }

   public boolean allContactsWithGroups(Contacts contacts) {
      boolean result = true;
      for (ContactData contactData : contacts) {
         if (contactData.getGroups().size() == 0) {
            result = false;
            break;
         }
      }
      return result;
   }

   public boolean allContactsWithoutGroups(Contacts contacts) {
      boolean result = true;
      for (ContactData contactData : contacts) {
         if (contactData.getGroups().size() > 0) {
            result = false;
            break;
         }
      }
      return result;
   }

   public void selectGroupFilter(String group) {
      new Select(wd.findElement(By.xpath("//select[@name='group']"))).selectByVisibleText(group);
   }

   public void removeFromGroup() {
      click(By.name("remove"));
   }
}
