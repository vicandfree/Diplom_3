package site.nomoreparties.stellarburgers.page;

import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import site.nomoreparties.stellarburgers.constants.PageURLs;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@AllArgsConstructor
public class MainPage {
    private static final By BUN_CONSTRUCTOR_BUTTON = By.xpath(".//span[text()='Булки']");
    private static final By SAUCE_CONSTRUCTOR_BUTTON = By.xpath(".//span[text()='Соусы']");
    private static final By INGREDIENT_CONSTRUCTOR_BUTTON = By.xpath(".//div/span[text()='Начинки']");

    private static final By BUNS_TAB = By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]");
    private static final By SAUCES_TAB = By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]");
    private static final By INGREDIENTS_TAB = By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]");

    private final WebDriver driver;

    public void openMainPage() {
        driver.get(PageURLs.MAIN_PAGE_URL);
    }

    public void openCabinetPage(By button) {
        driver.findElement(button).click();
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void clickBunButton() {
        driver.findElement(BUN_CONSTRUCTOR_BUTTON).click();
    }

    public void clickSauceButton() {
        driver.findElement(SAUCE_CONSTRUCTOR_BUTTON).click();
    }

    public void clickIngredientButton() {
        driver.findElement(INGREDIENT_CONSTRUCTOR_BUTTON).click();
    }

    public void checkBunsTabDisplayed() {
        assertThat(true, equalTo(driver.findElement(BUNS_TAB).isDisplayed()));
    }

    public void checkSaucesTabDisplayed() {
        assertThat(true, equalTo(driver.findElement(SAUCES_TAB).isDisplayed()));
    }

    public void checkIngredientTabDisplayed() {
        assertThat(true, equalTo(driver.findElement(INGREDIENTS_TAB).isDisplayed()));
    }
}