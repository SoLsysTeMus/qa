package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() {
      String group;

      app.getNavigationHelper().gotoHomePage();
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAGroup()){
         app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
         app.getNavigationHelper().gotoHomePage();
         group = "test1";
      } else {
         group = app.getGroupHelper().getFirstGroup();
      }
      app.getContactHelper().createContact(new ContactData("Dmitry", "Volkovsky", "Moscow",
              "88005553535", "volkovsky@ros-it.ru", group),true);
   }



}
