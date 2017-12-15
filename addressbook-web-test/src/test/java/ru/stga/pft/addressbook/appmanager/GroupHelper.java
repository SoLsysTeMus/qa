package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stga.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

   public GroupHelper(WebDriver wd) {
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

   public void selectGroup(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
   }

   public void deleteSelectedGroups() {
      click(By.name("delete"));
   }

   public void initGroupModification() {
      click(By.name("edit"));
   }

   public void submintGroupModification() {
      click(By.name("update"));
   }

   public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
   }

   public void createGroup(GroupData group) {
      initGroupCreation();
      fillGroupForm(group);
      submintGroupCreation();
      returnGroupPage();
   }

   public String getFirstGroup() {
      return wd.findElement(By.xpath("//*[@id=\"content\"]/form/span")).getText();
   }

   public int getGroupCount() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public List<GroupData> getGroupList() {
      List<GroupData> groups = new ArrayList<>();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

      for (WebElement element : elements) {
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         groups.add(new GroupData(id, element.getText(), null, null));
      }

      return groups;
   }
}
