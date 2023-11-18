import org.junit.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.driver.DriverManager;
import site.nomoreparties.stellarburgers.model.*;
import site.nomoreparties.stellarburgers.page.*;

import static org.junit.Assert.*;
import static site.nomoreparties.stellarburgers.constants.PageURLs.*;
import static site.nomoreparties.stellarburgers.constants.SeleniumElements.*;

public class CabinetTest {

    private final UserClient userClient = new UserClient();
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.createDriver();
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
    public void redirectFromCabinetToConstructorSuccess() {
        User user = UserGenerator.generateRandom();
        userClient.create(user);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.login();

        MainPage mainPage = new MainPage(driver);
        mainPage.openCabinetPage(TOP_CABINET_BUTTON);
        mainPage.waitForUrl(PROFILE_URL);

        CabinetPage cabinetPage = new CabinetPage(driver);
        cabinetPage.openConstructorPage();

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(MAIN_PAGE_URL);

        assertEquals(MAIN_PAGE_URL, currentPage.getPageUrl());
    }

    @Test
    public void redirectFromCabinetToMainPageSuccess() {
        User user = UserGenerator.generateRandom();
        userClient.create(user);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.login();

        MainPage mainPage = new MainPage(driver);
        mainPage.openCabinetPage(TOP_CABINET_BUTTON);
        mainPage.waitForUrl(PROFILE_URL);

        CabinetPage cabinetPage = new CabinetPage(driver);
        cabinetPage.openMainPage();

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(MAIN_PAGE_URL);

        assertEquals(MAIN_PAGE_URL, currentPage.getPageUrl());
    }

    @Test
    public void logoutFromCabinetSuccess() {
        User user = UserGenerator.generateRandom();
        userClient.create(user);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.login();

        MainPage mainPage = new MainPage(driver);
        mainPage.openCabinetPage(TOP_CABINET_BUTTON);
        mainPage.waitForUrl(PROFILE_URL);

        CabinetPage cabinetPage = new CabinetPage(driver);
        cabinetPage.logout();

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(LOGIN_URL);

        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.login();
    }
}