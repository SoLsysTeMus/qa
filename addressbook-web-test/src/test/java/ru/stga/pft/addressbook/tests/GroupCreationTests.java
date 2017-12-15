package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() {
      app.getNavigationHelper().gotoGroupPage();
      List<GroupData> before = app.getGroupHelper().getGroupList();
      GroupData groupData = new GroupData("test1", "test2", "test3");
      app.getGroupHelper().createGroup(groupData);
      List<GroupData> after = app.getGroupHelper().getGroupList();

      Assert.assertEquals(after.size(), before.size() + 1);

      before.add(groupData);
      Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
      before.sort(byId);
      after.sort(byId);

      Assert.assertEquals(before, after);
   }

}
