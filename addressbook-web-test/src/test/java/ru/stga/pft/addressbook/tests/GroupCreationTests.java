package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() {
      app.goTo().GroupPage();
      Groups before = app.group().all();
      GroupData group = new GroupData().withName("test2");
      app.group().create(group);
      Groups after = app.group().all();

      assertThat(after.size(), equalTo(before.size() + 1));

      assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   }

}
