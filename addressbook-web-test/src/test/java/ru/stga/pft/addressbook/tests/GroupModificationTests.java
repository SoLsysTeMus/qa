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
      app.goTo().GroupPage();
      if (app.group().all().size() == 0) {
         app.group().create(new GroupData().withName("test1"));
      }
   }

   @Test
   public void testGroupModification() {

      Groups before = app.group().all();
      GroupData modifyGroup = before.iterator().next();
      GroupData group = new GroupData().withId(modifyGroup.getId())
              .withName("test2")
              .withFooter("test3")
              .withHeader("test4");
      app.group().modify(group);
      Groups after = app.group().all();

      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));

   }


}
