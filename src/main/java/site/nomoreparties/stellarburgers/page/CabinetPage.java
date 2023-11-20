package site.nomoreparties.stellarburgers.page;

import lombok.AllArgsConstructor;
import org.openqa.selenium.*;

@AllArgsConstructor
public class CabinetPage {
    private static final By CABINET_NAME_INPUT = By.xpath(".//input[@name='Name']");
    private static final By CABINET_EMAIL_INPUT = By.xpath(".//input[@name='name']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//ul[@class='AppHeader_header__list__3oKJj']/li[1]/a");
    private static final By BURGER_LOGO = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private static final By LOGOUT_BUTTON = By.xpath(".//button[text()='Выход']");

    private final WebDriver driver;

    public String getCabinetName() {
        return driver.findElement(CABINET_NAME_INPUT).getAttribute("value");
    }

    public String getCabinetEmail() {
        return driver.findElement(CABINET_EMAIL_INPUT).getAttribute("value");
    }

    public void openConstructorPage() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    public void openMainPage() {
        driver.findElement(BURGER_LOGO).click();
    }

    public void logout() {
        driver.findElement(LOGOUT_BUTTON).click();
    }
}