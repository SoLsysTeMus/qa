package ru.stqa.rft.rest;

public class ApplicationManager {

   private IssuesHelper issuesHelper;

   public ApplicationManager() {
   }

   public IssuesHelper issues() {
      if (issuesHelper == null) {
         issuesHelper = new IssuesHelper();
      }
      return issuesHelper;
   }

}


