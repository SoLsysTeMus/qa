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

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void submintContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("address"), contactData.getAddress());
      type(By.name("home"), contactData.getHomePhone());
      type(By.name("email"), contactData.getEmail());

      if (creation) {
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void initNewContact() {
      click(By.linkText("add new"));
   }

   public void selectContactById(int id) {
      wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
   }

   public void deleteSelectedContact() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
      wd.switchTo().alert().accept();
   }

   public void modificateContactById(int id) {
      wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
   }

   public void submintContactModification() {
      click(By.name("update"));
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public void create(ContactData contact, boolean creation) {
      initNewContact();
      fillContactForm(contact, creation);
      submintContactCreation();
   }

   public Contacts all() {

      Contacts contacts = new Contacts();

      List<WebElement> allRows = getAllRowsFromContactTable();

      for (WebElement row : allRows) {

         List<WebElement> cells = row.findElements(By.tagName("td"));

         int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
         String lastName = cells.get(1).getText();
         String firstName = cells.get(2).getText();
         String address = cells.get(3).getText();
         String email = cells.get(4).getText();
         String phone = cells.get(5).getText().replaceAll("\\D", "");

         contacts.add(new ContactData()
                 .withId(id)
                 .withFirstName(firstName)
                 .withLastname(lastName)
                 .withAddress(address)
                 .withHomePhone(phone)
                 .withEmail(email));
      }

      return contacts;
   }

   public List<WebElement> getAllRowsFromContactTable() {
      WebElement table = wd.findElement(By.cssSelector("#maintable"));
      return table.findElements(By.name("entry"));
   }
}
