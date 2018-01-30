package ru.stqa.rft.rest;

import org.testng.SkipException;

import java.io.IOException;

public class TestBase {
   public static final ApplicationManager app = new ApplicationManager();

   private boolean isIssueOpen(int issueId) throws IOException {
      Issue issue = app.issues().getIssue(issueId).iterator().next();
      return !issue.getStatename().equals("Resolved");
   }

   void skipIfNotFixed(int issueId) throws IOException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }
}
