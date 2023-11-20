import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.page.*;

import static org.junit.Assert.*;
import static site.nomoreparties.stellarburgers.constants.PageURLs.*;
import static site.nomoreparties.stellarburgers.constants.SeleniumElements.*;
import static site.nomoreparties.stellarburgers.driver.DriverManager.*;

@RunWith(Parameterized.class)
public class SwitchToLoginPageTest {
    private final UserClient userClient = new UserClient();
    private final By button;
    private WebDriver driver;

    public SwitchToLoginPageTest(By button) {
        this.button = button;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {TOP_CABINET_BUTTON},
                {MIDDLE_CABINET_BUTTON},
        };
    }

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
    @DisplayName("Проверяем переход на страницу логина по разным кнопкам")
    public void burgerOpenAccountPageSuccessful() {
        MainPage page = new MainPage(driver);
        page.openMainPage();
        page.openCabinetPage(button);

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(LOGIN_URL);

        assertEquals(LOGIN_URL, currentPage.getPageUrl());
    }
}