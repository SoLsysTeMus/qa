package ru.stga.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().HomePage();
      if (app.contacts().all().size() == 0) {
         app.goTo().GroupPage();

         if (app.group().all().size() == 0) {
            app.group().create(new GroupData()
                    .withName("test1")
                    .withFooter("test2")
                    .withHeader("test3"));
         }

         Groups groups = app.group().all();
         GroupData group = groups.iterator().next();

         ContactData contact = new ContactData()
                 .withFirstName("Dmitry")
                 .withLastname("Volkovsky")
                 .withAddress("Moscow")
                 .withHomePhone("88005553535")
                 .withEmail("volkovsky@ros-it.ru")
                 .withGroup(group.getName());

         app.contacts().create(contact, true);

      }
   }

   @Test
   public void testContactModification() {
      app.goTo().HomePage();
      Contacts before = app.contacts().all();
      ContactData modifyContact = before.iterator().next();

      ContactData contact = new ContactData()
              .withId(modifyContact.getId())
              .withFirstName("Dmitry3")
              .withLastname("Volkovsky3")
              .withAddress("Moscow2")
              .withHomePhone("88005553535")
              .withEmail("volkovsky@ros-it.ru");

      app.contacts().modificateContactById(contact.getId());
      app.contacts().fillContactForm(contact, false);
      app.contacts().submintContactModification();
      app.goTo().HomePage();
      Contacts after = app.contacts().all();

      assertEquals(after.size(), before.size());

      MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifyContact).withAdded(contact)));
   }
}
