package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {


   @Test
   public void testGroupModification() {
      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isThereAGroup()) {
         app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
      }
      List<GroupData> before = app.getGroupHelper().getGroupList();
      app.getGroupHelper().selectGroup(before.size() - 1);
      app.getGroupHelper().initGroupModification();
      GroupData groupData = new GroupData(before.get(before.size() - 1).getId(), "test2", "test3", "test4");
      app.getGroupHelper().fillGroupForm(groupData);
      app.getGroupHelper().submintGroupModification();
      app.getGroupHelper().returnGroupPage();
      List<GroupData> after = app.getGroupHelper().getGroupList();

      Assert.assertEquals(after.size(), before.size());

      before.remove(before.size() - 1);
      before.add(groupData);

      Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
      before.sort(byId);
      after.sort(byId);

      Assert.assertEquals(before, after);

   }
}
