package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      Contacts contacts = app.db().contacts();

      if (contacts.size() == 0 || app.contacts().allContactsWithGroups(contacts)) {

         ContactData contact = new ContactData()
                 .withFirstName("Dmitry")
                 .withLastName("Volkovsky")
                 .withAddress("Moscow")
                 .withHomePhone("88005553535")
                 .withWorkPhone("")
                 .withMobilePhone("")
                 .withFirstEmail("volkovsky@ros-it.ru")
                 .withSecondEmail("")
                 .withThirdEmail("");

         app.contacts().create(contact, true);
         app.goTo().HomePage();
      }

      if (app.db().groups().size() == 0) {
         app.goTo().GroupPage();
         app.group().create(new GroupData()
                 .withName("group0")
                 .withFooter("group0")
                 .withHeader("group0"));
      }
   }

   @Test
   public void testContactAddToGroup() {

      app.goTo().HomePage();
      Contacts before = app.db().contacts();
      int id = getIdForSomeContactWithoutGroup(before);
      ContactData contactBefore = app.db().getContactById(id);
      app.contacts().selectContactById(id);
      app.contacts().submitSelectContactsToGroup();
      app.goTo().HomePage();
      ContactData contactAfter = app.db().getContactById(id);

      assertThat(contactAfter.getGroups().size(), equalTo(contactBefore.getGroups().size() + 1));
   }

   private int getIdForSomeContactWithoutGroup(Contacts contacts) {
      int id = 0;
      for (ContactData contactData : contacts) {
         if (contactData.getGroups().size() == 0) {
            id = contactData.getId();
            break;
         }
      }
      return id;
   }


}
