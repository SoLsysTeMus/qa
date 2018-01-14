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

      if (app.db().contacts().size() == 0) {

         if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData()
                    .withName("group0")
                    .withFooter("group0")
                    .withHeader("group0"));
         }

         Groups groups = app.db().groups();
         GroupData group = groups.iterator().next();

         ContactData contact = new ContactData()
                 .withFirstName("Dmitry")
                 .withLastName("Volkovsky")
                 .withAddress("Moscow")
                 .withHomePhone("88005553535")
                 .withWorkPhone("")
                 .withMobilePhone("")
                 .withFirstEmail("volkovsky@ros-it.ru")
                 .withSecondEmail("")
                 .withThirdEmail("")
                 .withGroup(group.getName());

         app.contacts().create(contact, true);

      }
   }

   @Test
   public void testContactModification() {
      app.goTo().HomePage();
      Contacts before = app.db().contacts();
      ContactData modifyContact = before.iterator().next();

      ContactData contact = new ContactData()
              .withId(modifyContact.getId())
              .withFirstName("Dmitry3")
              .withLastName("Volkovsky3")
              .withAddress("Moscow2")
              .withHomePhone("88005553535")
              .withWorkPhone("")
              .withMobilePhone("")
              .withFirstEmail("volkovsky@ros-it.ru")
              .withSecondEmail("")
              .withThirdEmail("");

      app.contacts().modificateContactById(contact.getId());
      app.contacts().fillContactForm(contact, false);
      app.contacts().submintContactModification();
      app.goTo().HomePage();
      assertEquals(app.contacts().count(), before.size());

      Contacts after = app.db().contacts();
      MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifyContact).withAdded(contact)));
   }
}
