package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AuthorizationHelper extends HelperBase{

   public AuthorizationHelper(ApplicationManager app) {
      super(app);
   }

   public void login(String username, String password) {
      wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
      type(By.name("username"), username);
      click(By.cssSelector("input[type='submit']"));
      type(By.name("password"), password);
      click(By.cssSelector("input[type='submit']"));
   }
}
