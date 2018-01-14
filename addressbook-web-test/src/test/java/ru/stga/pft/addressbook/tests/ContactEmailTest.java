package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {
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
                 .withFirstEmail("volkovsky@ros-it.ru")
                 .withGroup(group.getName());

         app.contacts().create(contact, true);

      }
   }

   @Test
   public void testContactEmails() {
      app.goTo().HomePage();
      ContactData contact = app.contacts().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
   }

   private String mergeEmails(ContactData contact) {
      return Stream.of(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
              .filter((s) -> !s.equals(""))
              .collect(Collectors.joining("\n"));
   }

}
