package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{


   @Test
   public void testGroupModification(){
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAGroup()){
         app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
      }
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().initGroupModification();
      app.getGroupHelper().fillGroupForm(new GroupData("test2", "test3", "test4"));
      app.getGroupHelper().submintGroupModification();
      app.getGroupHelper().returnGroupPage();
   }
}
