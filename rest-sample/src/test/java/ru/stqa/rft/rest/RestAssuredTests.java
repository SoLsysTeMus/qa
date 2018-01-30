package ru.stqa.rft.rest;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {

   @Test
   public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = app.issues().getIssues();
      Issue newIssue = new Issue().withSubject("Test subject").withDescription("Test description");
      int issueId = app.issues().createIssue(newIssue);
      oldIssues.add(newIssue.withId(issueId));
      Set<Issue> newIssues = app.issues().getIssues();
      oldIssues.retainAll(newIssues);

      assertEquals(newIssues, oldIssues);
   }

   @Test
   public void testSkipTest() throws IOException {
      skipIfNotFixed(750);
      System.out.println("test");
   }
}
