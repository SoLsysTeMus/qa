package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeleteTests extends TestBase {

   @Test
   public void testContactDelete() {
      String group;

      app.getNavigationHelper().gotoHomePage();
      if (!app.getContactHelper().isThereAContact()) {
         app.getNavigationHelper().gotoGroupPage();
         if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
            app.getNavigationHelper().gotoHomePage();
            group = "test1";
         } else {
            group = app.getGroupHelper().getFirstGroup();
         }
         app.getContactHelper().createContact(new ContactData("Dmitry", "Volkovsky", "Moscow",
                 "88005553535", "volkovsky@ros-it.ru", group), true);
         app.getNavigationHelper().gotoHomePage();
      }

      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().deleteSelectedContact();
      app.getNavigationHelper().gotoHomePage();
      before.remove(before.size() - 1);
      List<ContactData> after = app.getContactHelper().getContactList();

      Assert.assertEquals(before, after);
   }


}
