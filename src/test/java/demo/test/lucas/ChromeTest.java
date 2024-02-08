package demo.test.lucas;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChromeTest {

    WebDriver driver;

    @BeforeEach
    void setupTest() {
        String useGrid = System.getProperty( "USE_GRID", "false" );

        if( useGrid.equals( "true" ) ) {
            setupRemoteDriver();
        } else {
            setupLocalDriver();
        }
    }

    private void setupLocalDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void setupRemoteDriver() {
        driver = WebDriverManager.chromedriver()
                .remoteAddress( "http://localhost:4444/wd/hub" ).create();
    }


//    @BeforeAll
//    static void setupClass() {
//        // Resolve driver for Selenium Grid
//        WebDriverManager.chromedriver().setup();
//
//        // Start Selenium Grid in standalone mode
//        Main.main(new String[] { "standalone", "--port", "4445" });
//    }
//
//    @BeforeEach
//    void setupTest() {
//        driver = WebDriverManager.chromedriver()
//                .remoteAddress("http://localhost:4445/wd/hub").create();
//    }
//    @BeforeEach
//    void setupTest() {
//        driver = new ChromeDriver();
//    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
        // Exercise
        driver.get( "https://bonigarcia.dev/selenium-webdriver-java/" );
        String title = driver.getTitle();

        // Verify
        assertThat( title ).contains( "Selenium WebDriver" );
    }

}
