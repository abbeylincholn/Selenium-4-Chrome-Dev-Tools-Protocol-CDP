package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.fetch.Fetch;
import org.openqa.selenium.devtools.v85.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v85.network.model.ErrorReason;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NetworkFailedRequest {
    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();
        //log file ->
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList (new RequestPattern(Optional.of("*GetBook*"), java.util.Optional.empty(), java.util.Optional.empty())));
        devTools.send(Fetch.enable(patterns, Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), request -> {
            devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("[routerlink*='/library']")).click();
    }
}
