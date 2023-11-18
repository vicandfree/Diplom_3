import org.junit.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.*;
import site.nomoreparties.stellarburgers.page.*;

import static org.junit.Assert.*;
import static site.nomoreparties.stellarburgers.constants.PageURLs.*;
import static site.nomoreparties.stellarburgers.constants.SeleniumElements.*;
import static site.nomoreparties.stellarburgers.driver.DriverManager.*;

public class RegistrationTest {
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
    public void testRegistration() {
        User user = UserGenerator.generateRandom();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();

        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword(user.getPassword());
        registrationPage.confirmRegistration();

        registrationPage.waitForUrl(LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.login();

        MainPage mainPage = new MainPage(driver);
        mainPage.openCabinetPage(TOP_CABINET_BUTTON);
        mainPage.waitForUrl(PROFILE_URL);

        CabinetPage cabinetPage = new CabinetPage(driver);

        assertEquals(cabinetPage.getCabinetName(), user.getName());
        assertEquals(cabinetPage.getCabinetEmail(), user.getEmail().toLowerCase());
    }

    @Test
    public void testRegistrationWithInvalidPassword() {
        User user = UserGenerator.generateRandom();
        user.setPassword("12345");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();

        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword(user.getPassword());
        registrationPage.confirmRegistration();

        assertEquals("Некорректный пароль", registrationPage.checkValidationError());
    }
}