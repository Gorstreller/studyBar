package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        driver.get(app.getProperty("web.baseURL") + "/signup_page.php");
        type(By.id("username"), username);
        type(By.id("email-field"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }
}