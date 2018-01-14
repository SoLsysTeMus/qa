package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeleteFromGroupTest extends TestBase {
   @BeforeMethod
   public void ensurePreconditions() {

      if (app.db().groups().size() == 0) {
         app.goTo().GroupPage();
         app.group().create(new GroupData()
                 .withName("group0")
                 .withFooter("group0")
                 .withHeader("group0"));
      }

      Groups groups = app.db().groups();
      GroupData group = groups.iterator().next();

      Contacts contacts = app.db().contacts();

      if (contacts.size() == 0 || app.contacts().allContactsWithoutGroups(contacts)) {

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
                 .inGroup(group);

         app.contacts().create(contact, true);
         app.goTo().HomePage();
      }
   }

   @Test
   public void testDeleteContactFromGroup() {

      app.goTo().HomePage();
      Contacts before = app.db().contacts();
      int id = getIdForSomeContactWithGroup(before);
      ContactData contactBefore = app.db().getContactById(id);
      GroupData groupBefore = contactBefore.getGroups().iterator().next();
      app.contacts().selectGroupFilter(groupBefore.getName());
      app.contacts().selectContactById(id);
      app.contacts().removeFromGroup();
      app.goTo().HomePage();
      ContactData contactAfter = app.db().getContactById(id);
      System.out.println(contactBefore);
      System.out.println(contactAfter);

      assertThat(contactAfter.getGroups().size(), equalTo(contactBefore.getGroups().size() - 1));
   }

   private int getIdForSomeContactWithGroup(Contacts contacts) {
      int id = 0;
      for (ContactData contactData: contacts){
         if (contactData.getGroups().size() > 0){
               id = contactData.getId();
               break;
            }
      }
      return id;
   }
}
