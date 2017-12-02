package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() {

      app.getContactHelper().initNewContact();
      app.getContactHelper().fillContactForm(new ContactData("Dmitry", "Volkovsky", "Moscow",
                                                                        "88005553535", "volkovsky@ros-it.ru"));
      app.getContactHelper().submintContactCreation();
   }



}
