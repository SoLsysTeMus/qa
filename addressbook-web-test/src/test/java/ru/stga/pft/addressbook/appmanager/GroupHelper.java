package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stga.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

   public GroupHelper(FirefoxDriver wd) {
      super(wd);
   }

   public void returnGroupPage() {
      wd.findElement(By.linkText("group page")).click();
   }

   public void submintGroupCreation() {
      click(By.name("submit"));
   }

   public void fillGroupForm(GroupData groupData) {
      type(By.name("group_name"), groupData.getName());
      type(By.name("group_header"), groupData.getHeader());
      type(By.name("group_footer"), groupData.getFooter());
   }

   public void initGroupCreation() {
      click(By.name("new"));
   }

   public void selectGroup() {
       if (!wd.findElement(By.name("selected[]")).isSelected()) {
          click(By.name("selected[]"));
       }
   }

   public void deleteSelectedGroups() {
      click(By.name("delete"));
   }
}
