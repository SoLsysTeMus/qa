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

      app.goTo().HomePage();
      if (!app.getContactHelper().isThereAContact()) {
         app.goTo().GroupPage();
         if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
            group = "test1";
            app.goTo().HomePage();
         } else {
            group = app.group().getFirstGroup();
            app.goTo().HomePage();
         }
         app.getContactHelper().createContact(new ContactData("Dmitry", "Volkovsky", "Moscow",
                 "88005553535", "volkovsky@ros-it.ru", group), true);
         app.goTo().HomePage();
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Dmitry3", "Volkovsky3", "Moscow2",
              "88005553535", "volkovsky@ros-it.ru");
      app.getContactHelper().modificateContact(before.size() - 1);
      app.getContactHelper().fillContactForm(contact, false);
      app.getContactHelper().submintContactModification();
      app.goTo().HomePage();
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
