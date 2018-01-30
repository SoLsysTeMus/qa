package ru.stqa.rft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;

import java.io.IOException;
import java.util.Set;

public class IssuesHelper {


   public void init() {
      RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");
   }

   public Set<Issue> getIssues() throws IOException {
      init();
      String json = RestAssured.get("http://demo.bugify.com/api/filters/11/issues.json").asString();
      JsonElement parse = new JsonParser().parse(json);
      JsonElement issue = parse.getAsJsonObject().get("issues");
      return new Gson().fromJson(issue, new TypeToken<Set<Issue>>() {
      }.getType());
   }

   public Set<Issue> getIssue(int id) {
      init();
      String json = RestAssured.get(String.format("http://demo.bugify.com/api/issues/%s.json", id)).asString();
      JsonElement parse = new JsonParser().parse(json);
      JsonElement issue = parse.getAsJsonObject().get("issues");
      return new Gson().fromJson(issue, new TypeToken<Set<Issue>>() {
      }.getType());
   }

   public int createIssue(Issue newIssue) throws IOException {
      String json = RestAssured.given()
              .parameter("subject", newIssue.getSubject())
              .parameter("description", newIssue.getDescription())
              .post("http://demo.bugify.com/api/issues.json").asString();

      JsonElement parse = new JsonParser().parse(json);
      return parse.getAsJsonObject().get("issue_id").getAsInt();
   }
}
