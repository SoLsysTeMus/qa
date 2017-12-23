package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().GroupPage();

      if (app.group().all().size() == 0) {
         app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
      }
   }

   @Test
   public void testContactCreation() {
      app.goTo().GroupPage();
      Groups groups = app.group().all();
      GroupData group = groups.iterator().next();

      app.goTo().HomePage();
      Contacts before = app.contacts().all();

      ContactData contact = new ContactData().withFirstName("Dmitry")
              .withLastname("Volkovsky")
              .withAddress("Moscow")
              .withHomePhone("88005553535")
              .withEmail("volkovsky@ros-it.ru")
              .withGroup(group.getName());

      app.contacts().create(contact, true);
      app.goTo().HomePage();
      Contacts after = app.contacts().all();

      assertEquals(after.size(), before.size() + 1);

      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
   }
}
