// Generated by Selenium IDE
package acquistosistema;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class AcquistoCoinsTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver","test/profilosistema/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void testAcquistoCoinsCarta() {
    driver.get("http://localhost:8080/SocialNotes/");
    driver.manage().window().setSize(new Dimension(1920, 1040));
    driver.findElement(By.linkText("Accedi")).click();
    driver.findElement(By.id("inputEmail")).click();
    driver.findElement(By.id("inputEmail")).sendKeys("fry");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputPassword")).sendKeys("Despacit0");
    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Pricing")).click();
    driver.findElement(By.linkText("ACQUISTA COINS")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".bg-white"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void testAcquistoCoinsSenzaCarta() {
    driver.get("http://localhost:8080/SocialNotes/");
    driver.manage().window().setSize(new Dimension(1920, 1040));
    driver.findElement(By.linkText("Accedi")).click();
    driver.findElement(By.id("inputEmail")).click();
    driver.findElement(By.id("inputEmail")).sendKeys("Povero");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputPassword")).sendKeys("Povero1");
    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Pricing")).click();
    driver.findElement(By.linkText("ACQUISTA COINS")).click();
    assertThat(driver.findElement(By.cssSelector("h4:nth-child(2)")).getText(), is("Non hai nessun metodo di pagamento!"));
  }
}
