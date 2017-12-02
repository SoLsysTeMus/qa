package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

   @Test
   public void testContactModification(){

      app.getContactHelper().modificateContact();
      app.getContactHelper().fillContactForm(new ContactData("Dmitry2", "Volkovsky2", "Moscow2",
                                                                       "88005553535", "volkovsky@ros-it.ru"));
      app.getContactHelper().submintContactModification();
   }
}
