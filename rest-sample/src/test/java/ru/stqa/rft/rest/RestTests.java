package ru.stqa.rft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests {

   @Test
   public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = getIssues();
      Issue newIssue = new Issue().withSubject("Test subject").withDescription("Test description");
      int issueId = createIssue(newIssue);
      oldIssues.add(newIssue.withId(issueId));
      Set<Issue> newIssues = getIssues();
      oldIssues.retainAll(newIssues);

      assertEquals(newIssues, oldIssues);
   }

   private Set<Issue> getIssues() throws IOException {
      String json = getExecuter().execute(Request.Get("http://demo.bugify.com/api/filters/11/issues.json")).returnContent().asString();
      JsonElement parse = new JsonParser().parse(json);
      JsonElement issue = parse.getAsJsonObject().get("issues");
      return new Gson().fromJson(issue, new TypeToken<Set<Issue>>() {
      }.getType());
   }

   private Executor getExecuter() {
      return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
   }

   private int createIssue(Issue newIssue) throws IOException {
      String json = getExecuter().execute(Request.Post("http://demo.bugify.com/api/issues.json")
              .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                      new BasicNameValuePair("description", newIssue.getDescription())))
              .returnContent()
              .asString();
      JsonElement parse = new JsonParser().parse(json);
      return parse.getAsJsonObject().get("issue_id").getAsInt();
   }
}
