package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.ConnectionType;


import java.util.Optional;

public class NetworkSpeed {
    public static void main(String[] args) {

        // waits -2-3
        ChromeDriver driver = new ChromeDriver();
        //log file ->
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000, Optional.of(ConnectionType.WIFI),
                Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            System.out.println(loadingFailed.getErrorText());
            System.out.println(loadingFailed.getTimestamp());
        });
        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Library")).click();
        long endTime = System.currentTimeMillis();
        System.out.println("long elapsedTime: " + (endTime - startTime));
        driver.quit();

    }
}
