package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UsersHelper extends HelperBase{
   public UsersHelper(ApplicationManager app) {
      super(app);
   }

   public void changePasswordForUserStart(int userId) {
      wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
      click(By.xpath(String.format("//a[contains(@href,'user_id=%s')]",userId)));
      click(By.cssSelector("input[value='Сбросить пароль']"));

   }

   public void changePasswordForUserFinish(String confirmationLink, String newPassword) {
      wd.get(confirmationLink);
      type(By.id("realname"), "test");
      type(By.id("password"), newPassword);
      type(By.id("password-confirm"), newPassword);
      click(By.cssSelector("button[type=submit]"));
   }
}
