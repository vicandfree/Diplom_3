import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.page.*;

import static site.nomoreparties.stellarburgers.driver.DriverManager.*;

public class ConstructorTest {
    private final UserClient userClient = new UserClient();
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = createDriver();
    }

    @After
    public void tearDown() {
        CurrentPage currentPage = new CurrentPage(driver);
        String accessToken = currentPage.getAccessToken();

        if (accessToken != null) {
            userClient.delete(accessToken);
        }

        driver.quit();
    }

    @Test
    @DisplayName("Проверяем открытие вкладки с булочками")
    public void bunsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        mainPage.checkBunsTabDisplayed();
    }

    @Test
    @DisplayName("Проверяем открытие вкладки с соусами")
    public void saucesTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickSauceButton();
        mainPage.checkSaucesTabDisplayed();
    }

    @Test
    @DisplayName("Проверяем открытие вкладки с ингредиентами")
    public void ingredientsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickIngredientButton();
        mainPage.checkIngredientTabDisplayed();
    }
}