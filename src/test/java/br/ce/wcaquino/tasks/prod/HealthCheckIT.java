package br.ce.wcaquino.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
    @Test
    public void healthCheck() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        URL gridUrl = new URL("http://localhost:4444/");
        WebDriver driver = new RemoteWebDriver(gridUrl, options);
        try {
            driver.navigate().to("http://10.0.2.15:9999/tasks");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            String version = driver.findElement(By.id("version")).getText();
            Assert.assertTrue(version.startsWith("build"));
        } finally {
            driver.quit();
        }
    }
}
