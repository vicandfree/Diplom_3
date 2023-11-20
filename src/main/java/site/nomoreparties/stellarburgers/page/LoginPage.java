package site.nomoreparties.stellarburgers.page;


import lombok.AllArgsConstructor;
import org.openqa.selenium.*;

import static site.nomoreparties.stellarburgers.constants.PageURLs.*;

@AllArgsConstructor
public class LoginPage {
    private static final By EMAIL_INPUT = By.xpath("//label[contains(text(),'Email')]/../input");
    private static final By PASSWORD_INPUT = By.xpath("//label[contains(text(),'Пароль')]/../input");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти']");

    private final WebDriver driver;

    public void open() {
        driver.get(LOGIN_URL);
    }

    public void enterEmail(String email) {
        driver.findElement(EMAIL_INPUT).clear();
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD_INPUT).clear();
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void login() {
        driver.findElement(LOGIN_BUTTON).click();
    }
}