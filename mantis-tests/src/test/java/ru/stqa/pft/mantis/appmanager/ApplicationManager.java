package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    public WebDriver driver;

    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void stop() {
        driver.quit();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

        if (browser.equals(BrowserType.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        }
        else if (browser.equals(BrowserType.OPERA)){
            driver = new OperaDriver();
        }

        driver.get(properties.getProperty("web.baseURL"));
        driver.manage().window().maximize();
    }
}
