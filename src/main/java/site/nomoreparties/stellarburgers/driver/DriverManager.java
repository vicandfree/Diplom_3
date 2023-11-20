package site.nomoreparties.stellarburgers.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public class DriverManager {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return createChromeDriver();
        }

        switch (browser) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", System.getenv("YANDEX_DRIVER_PATH"));
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        return new ChromeDriver(options);
    }
}