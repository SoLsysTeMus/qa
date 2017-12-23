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

      app.goTo().HomePage();
      if (!app.getContactHelper().isThereAContact()) {
         app.goTo().GroupPage();
         if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
            app.goTo().HomePage();
            group = "test1";
         } else {
            group = app.group().getFirstGroup();
         }
         app.getContactHelper().createContact(new ContactData("Dmitry", "Volkovsky", "Moscow",
                 "88005553535", "volkovsky@ros-it.ru", group), true);
         app.goTo().HomePage();
      }

      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().deleteSelectedContact();
      app.goTo().HomePage();
      before.remove(before.size() - 1);
      List<ContactData> after = app.getContactHelper().getContactList();

      Assert.assertEquals(before, after);
   }


}
