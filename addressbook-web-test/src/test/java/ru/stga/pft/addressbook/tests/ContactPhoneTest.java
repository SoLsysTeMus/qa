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

public class ContactPhoneTest extends TestBase {

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
                 .inGroup(groups.iterator().next());

         app.contacts().create(contact, true);

      }
   }

   @Test
   public void testContactPhones() {
      app.goTo().HomePage();
      ContactData contact = app.contacts().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
      System.out.println(contact);
      System.out.println(contactInfoFromEditForm);

      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

   }

   private String mergePhones(ContactData contact) {
      return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .map(this::cleaned)
              .filter((s) -> !s.equals(""))
              .collect(Collectors.joining("\n"));
   }

   private String cleaned(String prone) {
      return prone.replaceAll("\\s", "").replaceAll("[-()]", "");
   }
}
