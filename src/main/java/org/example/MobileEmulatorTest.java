package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;



import java.util.Optional;

public class MobileEmulatorTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        //send command to CDP methods -> CDP methods wil invoke and get access to chrome dev tools
        devTools.send(Emulation.setDeviceMetricsOverride(
                600,            // width
                1000,                 // height
                50,                   // deviceScaleFactor
                true,                 // mobile
                Optional.empty(),     // screenWidth
                Optional.empty(),     // screenHeight
                Optional.empty(),     // positionX
                Optional.empty(),     // positionY
                Optional.empty(),     // dontSetVisibleSize
                Optional.empty(),     // screenOrientation
                Optional.empty(),     // viewport
                Optional.empty()      // displayFeature
        ));

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Library")).click();

    }
}