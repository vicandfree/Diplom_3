import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.page.*;

import static org.junit.Assert.*;
import static site.nomoreparties.stellarburgers.constants.PageURLs.*;
import static site.nomoreparties.stellarburgers.driver.DriverManager.*;

public class RestorePageTest {
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
    @DisplayName("Проверяем переход со страницы сброса пароля на страницу логина")
    public void returnFromRecoveryToLoginSuccessful() {
        RestorePage recoveryPage = new RestorePage(driver);
        recoveryPage.open();
        recoveryPage.returnToAccountPage();

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(LOGIN_URL);

        assertEquals(LOGIN_URL, currentPage.getPageUrl());
    }
}