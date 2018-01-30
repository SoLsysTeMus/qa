package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import org.testng.Assert;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

   private ApplicationManager app;

   public SoapHelper(ApplicationManager app) {
      this.app = app;
   }

   public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = getMantisConnect();
      ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "12345");
      return Arrays.stream(projects).map((p)->new Project()
              .withId(p.getId().intValue())
              .withName(p.getName()))
              .collect(Collectors.toSet());
   }

   private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
      return new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("mantis.url")));
   }

   public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = getMantisConnect();
      String[] categories = mc.mc_project_get_categories("administrator", "12345", BigInteger.valueOf(issue.getProject().getId()));
      IssueData issueData = new IssueData();
      issueData.setSummary(issue.getSummary());
      issueData.setCategory(categories[0]);
      issueData.setDescription(issue.getDescription());
      issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),issue.getProject().getName()));

      BigInteger id = mc.mc_issue_add("administrator", "12345", issueData);
      IssueData createdIssueData = mc.mc_issue_get("administrator", "12345", id);
      return new Issue()
              .withId(createdIssueData.getId().intValue())
              .withSummary(createdIssueData.getSummary())
              .withDescription(createdIssueData.getDescription())
              .withProject(new Project()
                      .withId(createdIssueData.getProject().getId().intValue())
                      .withName(createdIssueData.getProject().getName()));

   }

   public Issue getIssue(int id) throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = getMantisConnect();
      IssueData issue = mc.mc_issue_get("administrator", "12345", BigInteger.valueOf(id));
      return new Issue()
              .withId(issue.getId().intValue())
              .withSummary(issue.getSummary())
              .withDescription(issue.getDescription())
              .withStatus(issue.getStatus().getName())
              .withProject(new Project()
                      .withId(issue.getProject().getId().intValue())
                      .withName(issue.getProject().getName()));
   }
}
