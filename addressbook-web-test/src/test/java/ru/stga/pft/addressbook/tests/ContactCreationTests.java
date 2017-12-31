package ru.stga.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().GroupPage();

      if (app.group().all().size() == 0) {
         app.group().create(new GroupData()
                 .withName("group0")
                 .withFooter("group0")
                 .withHeader("group0"));
      }
   }

   @DataProvider
   public Iterator<Object[]> validContactsFromXml() throws IOException {
      try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.xml")))) {
         String xml = "";
         String line = reader.readLine();
         while (line != null) {
            xml += line;
            line = reader.readLine();
         }
         XStream xstream = new XStream();
         xstream.processAnnotations(ContactData.class);
         List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
         return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
      }
   }

   @DataProvider
   public Iterator<Object[]> validContactsFromJson() throws IOException {
      try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.json")))) {
         String json = "";
         String line = reader.readLine();
         while (line != null) {
            json += line;
            line = reader.readLine();
         }
         Gson gson = new Gson();
         List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
         }.getType());
         return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
      }
   }

   @Test(dataProvider = "validContactsFromJson")
   public void testContactCreation(ContactData contact) {
      app.goTo().GroupPage();
      Groups groups = app.group().all();
      GroupData group = groups.iterator().next();

      app.goTo().HomePage();
      Contacts before = app.contacts().all();
      app.contacts().create(contact, true);
      app.goTo().HomePage();
      assertEquals(app.contacts().count(), before.size() + 1);

      Contacts after = app.contacts().all();
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
   }
}
