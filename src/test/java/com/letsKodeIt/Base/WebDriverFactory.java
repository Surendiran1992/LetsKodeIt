package com.letsKodeIt.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    // Singleton
    // Only one instance of the class can exist at a time
    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();

    /***
     * Get driver instance based on the browser type
     * @param browser
     * @return
     */
    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        setDriver(browser);
        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase("firefox")) {
                    FirefoxOptions ffOptions = setFFOptions();
                    driver = new FirefoxDriver(ffOptions);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions chromeOptions = setChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("iexplorer")) {
                    InternetExplorerOptions ieOptions = setIEOptions();
                    driver = new InternetExplorerDriver(ieOptions);
                    threadedDriver.set(driver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }

    /***
     * Quit driver instance
     */
    public void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }

    /***
     * Set system path for driver files
     * @param browser
     */
    private void setDriver(String browser) {
        String driverPath = "";
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        System.out.println("OS Name from system property :: " + os);
        String directory = System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\";
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase("firefox")) {
            driverKey = "webdriver.gecko.driver";
            driverValue = "geckodriver";
        } else if (browser.equalsIgnoreCase("chrome")) {
            driverKey = "webdriver.chrome.driver";
            driverValue = "chromedriver";
        } else if (browser.equalsIgnoreCase("ie")) {
            driverKey = "webdriver.ie.driver";
            driverValue = "IEDriverServer";
        } else {
            System.out.println("Browser type not supported");
        }

        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
        System.out.println("Driver Binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
    }

    /***
     * Set Chrome Options
     * @return options
     */
    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return options;
    }

    /***
     * Set Firefox Options
     * @return options
     */
    private FirefoxOptions setFFOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        return options;
    }

    /***
     * Set Internet Explorer Options
     * @return options
     */
    private InternetExplorerOptions setIEOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        options.setCapability(InternetExplorerDriver.
                INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        return options;
    }
}