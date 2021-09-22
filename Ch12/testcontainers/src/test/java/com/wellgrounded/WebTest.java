package com.wellgrounded;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

@Testcontainers
public class WebTest {
    private static final File tmpDirectory = new File("tmp");

    @Container
    public static BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(RECORD_ALL, tmpDirectory, VncRecordingFormat.MP4);

    @BeforeAll
    public static void setup() {
        // Make sure we have our temporary spot for files
        tmpDirectory.mkdir();
    }

    @Test
    public void checkTheSiteOut() {
        RemoteWebDriver driver = chrome.getWebDriver();
        driver.get("https://github.com/well-grounded-java");

        WebElement title = driver.findElementByTagName("h1");
        assertEquals("well-grounded-java", title.getText());
    }

    @Test
    public void theList() throws IOException {
        RemoteWebDriver driver = chrome.getWebDriver();
        driver.get("https://jasonrclark.com/reading");

        File screen = driver.getScreenshotAs(OutputType.FILE);
        Files.copy(screen.toPath(), Path.of("tmp/screenshot.png"), StandardCopyOption.REPLACE_EXISTING);

        List<WebElement> items = driver.findElementsByTagName("li");
        assertFalse(items.isEmpty());
    }
}
