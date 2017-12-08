package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stga.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void submintContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void fillContactForm(ContactData contactData) {
      type(By.name("firstname"),contactData.getFirstName());
      type(By.name("lastname"),contactData.getLastname());
      type(By.name("address"),contactData.getAddress());
      type(By.name("home"),contactData.getHomePhone());
      type(By.name("email"),contactData.getEmail());
   }

   public void initNewContact() {
      click(By.linkText("add new"));
   }

   public void selectContact() {
      click(By.name("selected[]"));
   }

   public void deleteSelectedContact() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
      wd.switchTo().alert().accept();
   }

   public void modificateContact() {
      click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
   }

   public void submintContactModification() {
      click(By.name("update"));
   }
}
