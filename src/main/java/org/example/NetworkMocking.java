package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v85.fetch.Fetch;

import java.util.Optional;

public class NetworkMocking {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        //log file ->
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            if (requestPaused.getRequest().getUrl().contains("shetty")){
                String mockedUrl = requestPaused.getRequest().getUrl().replace("=shetty","=BadGuy");
                System.out.println(mockedUrl);

                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(mockedUrl), Optional.of(requestPaused.getRequest().getMethod()),
                        requestPaused.getRequest().getPostData(), requestPaused.getResponseHeaders()));
            }
            else {
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(requestPaused.getRequest().getUrl()), Optional.of(requestPaused.getRequest().getMethod()),
                        requestPaused.getRequest().getPostData(), requestPaused.getResponseHeaders()));
            }
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("[routerlink*='/library']")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.cssSelector("body > app-root > app-library-dashboard > p")).getText());


    }
}
