package site.nomoreparties.stellarburgers.page;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

@AllArgsConstructor
public class CurrentPage {
    private final WebDriver driver;

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getAccessToken() {
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        return localStorage.getItem("accessToken");
    }
}