package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase{

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
                 .withLastName("Volkovsky")
                 .withAddress("Moscow")
                 .withHomePhone("88005553535")
                 .withFirstEmail("volkovsky@ros-it.ru")
                 .withThirdEmail("volkovsky2@ros-it.ru")
                 .withGroup(group.getName());

         app.contacts().create(contact, true);

      }
   }

   @Test
   public void testContactEmails(){
      app.goTo().HomePage();
      ContactData contact = app.contacts().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

      assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
   }
}
