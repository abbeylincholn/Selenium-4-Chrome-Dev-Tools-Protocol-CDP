//package org.example;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SetGeoLocationTest2 {
//    public static void main(String[] args) {
//
//        ChromeDriver driver = new ChromeDriver();
//        DevTools devTools = driver.getDevTools();
//        devTools.createSession();
//
//        // Set geolocation
//        Map<String, Object> coordinates = new HashMap<>();
//        coordinates.put("latitude", 40);
//        coordinates.put("longitude", 3);
//        coordinates.put("accuracy", 1);
//        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
//
//        // Load Google
//        driver.get("https://www.google.com");
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Handle cookie consent popup if present
//        try {
//            WebElement rejectBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//button[contains(., 'Reject all')]")));
//            rejectBtn.click();
//        } catch (TimeoutException ignored) {
//            System.out.println("Consent popup not found.");
//        }
//
//        // Wait for search box and search for "netflix"
//        try {
//            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
//            searchBox.sendKeys("netflix", Keys.ENTER);
//        } catch (TimeoutException e) {
//            System.out.println("Search box not found.");
//            driver.quit();
//            return;
//        }
//
//        // Wait for and click the first result
//        try {
//            WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h3")));
//            firstResult.click();
//        } catch (TimeoutException e) {
//            System.out.println("Search results not found or clickable.");
//            driver.quit();
//            return;
//        }
//
//        // Optionally get a title or headline from the Netflix page
//        try {
//            WebElement title = new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
//            System.out.println("Page title: " + title.getText());
//        } catch (TimeoutException e) {
//            System.out.println("Title element not found.");
//        }
//
//        driver.quit();
//    }
//}

package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import java.util.*;

public class SetGeoLocationTest2 {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");


        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Remove webdriver property
        driver.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        Map<String, Object> coordinates = new HashMap();
        coordinates.put("latitude", 40);
        coordinates.put("longitude", 3);
        coordinates.put("accuracy", 1);

        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.get("http://google.com");

        // Add random delay
        Thread.sleep(new Random().nextInt(2000) + 1000);

        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);

        // Add another random delay
        Thread.sleep(new Random().nextInt(2000) + 1000);

        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
        String title = driver.findElement(By.cssSelector(".default-ltr-cache-l1j3pp-StyledContainer.euy28770")).getText();
        System.out.println(title);

        driver.quit();
    }
}