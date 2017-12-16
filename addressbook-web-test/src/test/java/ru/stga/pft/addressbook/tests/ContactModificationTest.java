package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

   @Test
   public void testContactModification() {
      String group;

      app.getNavigationHelper().gotoHomePage();
      if (!app.getContactHelper().isThereAContact()) {
         app.getNavigationHelper().gotoGroupPage();
         if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
            group = "test1";
            app.getNavigationHelper().gotoHomePage();
         } else {
            group = app.getGroupHelper().getFirstGroup();
            app.getNavigationHelper().gotoHomePage();
         }
         app.getContactHelper().createContact(new ContactData("Dmitry", "Volkovsky", "Moscow",
                 "88005553535", "volkovsky@ros-it.ru", group), true);
         app.getNavigationHelper().gotoHomePage();
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Dmitry3", "Volkovsky3", "Moscow2",
              "88005553535", "volkovsky@ros-it.ru");
      app.getContactHelper().modificateContact(before.size() - 1);
      app.getContactHelper().fillContactForm(contact, false);
      app.getContactHelper().submintContactModification();
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();

      Assert.assertEquals(after.size(), before.size());

      before.remove(before.size() - 1);
      before.add(contact);

      Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
      before.sort(byId);
      after.sort(byId);

      Assert.assertEquals(before, after);
   }
}
