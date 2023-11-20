package site.nomoreparties.stellarburgers.page;

import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.constants.PageURLs.*;

@AllArgsConstructor
public class RegistrationPage {
    private static final By NAME_INPUT = By.xpath(".//form[@class='Auth_form__3qKeq mb-20']/fieldset[1]/descendant::input");
    private static final By EMAIL_INPUT = By.xpath(".//form[@class='Auth_form__3qKeq mb-20']/fieldset[2]/descendant::input");
    private static final By PASSWORD_INPUT = By.xpath(".//form[@class='Auth_form__3qKeq mb-20']/fieldset[3]/descendant::input");
    private static final By REGISTRATION_BUTTON = By.xpath(".//form[@class='Auth_form__3qKeq mb-20']/button");
    private static final By VALIDATION_ERROR = By.xpath(".//p[text()='Некорректный пароль']");

    private final WebDriver driver;

    public void open() {
        driver.get(REGISTER_PAGE_URL);
    }

    public void enterName(String name) {
        driver.findElement(NAME_INPUT).clear();
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(EMAIL_INPUT).clear();
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD_INPUT).clear();
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void confirmRegistration() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    public String checkValidationError() {
        return driver.findElement(VALIDATION_ERROR).getText();
    }
}