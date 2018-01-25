package ru.stqa.pft.mantis.test;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

   @BeforeTest
   public void preconditions() throws javax.mail.MessagingException, IOException {
      Users users = app.db().users();
      if (users.size() == 0){
         createTestUser();
      }
   }


   @Test
   public void testChangePassword() throws IOException, MessagingException, javax.mail.MessagingException {
      String login = app.getProperty("login.admin");
      String password = app.getProperty("login.adminPassword");
      UserData user = app.db().users().iterator().next();
      String userPassword = "password";

      app.authorization().login(login, password);
      app.users().changePasswordForUserStart(user.getId());
      app.james().doesUserExsist(user.getUsername());
      List<MailMessage> mailMessages = app.james().waitForMail(user.getUsername(),userPassword,60000);
      String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
      app.users().changePasswordForUserFinish(confirmationLink, userPassword);
      assertTrue(app.newSession().login(user.getUsername(), userPassword));
      app.james().drainEmail(user.getUsername(),userPassword);
   }

   private void createTestUser() throws javax.mail.MessagingException, IOException {
      long now = System.currentTimeMillis();
      String user = String.format("user%s", now);
      String password = "password";
      String email = String.format("user%s@localhost", now);
      app.james().createUser(user,password);
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.james().waitForMail(user,password,60000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      assertTrue(app.newSession().login(user, password));
      app.james().drainEmail(user,password);
   }

   private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
   }

}
