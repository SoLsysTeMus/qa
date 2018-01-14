package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.db().groups().size() == 0) {
         app.goTo().GroupPage();
         app.group().create(new GroupData().withName("test1"));
      }
   }

   @Test
   public void testGroupModification() {

      Groups before = app.db().groups();
      GroupData modifyGroup = before.iterator().next();
      GroupData group = new GroupData()
              .withId(modifyGroup.getId())
              .withName("test2")
              .withFooter("test3")
              .withHeader("test4");
      app.goTo().GroupPage();
      app.group().modify(group);
      assertEquals(app.group().count(), before.size());

      Groups after = app.db().groups();
      assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));
      verifyGroupListInUI();
   }


}
