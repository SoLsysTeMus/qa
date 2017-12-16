package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() {
      String group;

      app.getNavigationHelper().gotoHomePage();

      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isThereAGroup()) {
         app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
         group = "test1";
         app.getNavigationHelper().gotoHomePage();
      } else {
         group = app.getGroupHelper().getFirstGroup();
         app.getNavigationHelper().gotoHomePage();
      }

      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData contact = new ContactData("Dmitry", "Volkovsky", "Moscow",
              "88005553535", "volkovsky@ros-it.ru", group);
      app.getContactHelper().createContact(contact, true);
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();

      Assert.assertEquals(after.size(), before.size() + 1);

      before.add(contact);
      Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
      before.sort(byId);
      after.sort(byId);

      Assert.assertEquals(before, after);
   }
}
