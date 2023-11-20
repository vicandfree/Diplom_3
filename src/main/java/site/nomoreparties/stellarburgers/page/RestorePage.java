package site.nomoreparties.stellarburgers.page;

import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import site.nomoreparties.stellarburgers.constants.PageURLs;

@AllArgsConstructor
public class RestorePage {
    private static final By RETURN_TO_ACCOUNT_BUTTON = By.xpath(".//a[contains(@href,'login')]");

    private final WebDriver driver;

    public void open() {
        driver.get(PageURLs.FORGOT_PASSWORD_URL);
    }

    public void returnToAccountPage() {
        driver.findElement(RETURN_TO_ACCOUNT_BUTTON).click();
    }
}