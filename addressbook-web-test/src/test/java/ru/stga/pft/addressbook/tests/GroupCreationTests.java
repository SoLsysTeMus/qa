package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() {
      app.goTo().GroupPage();
      Groups before = app.group().all();
      GroupData group = new GroupData().withName("test2");
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));

      Groups after = app.group().all();
      assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   }

}
