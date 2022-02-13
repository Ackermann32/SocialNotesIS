// Generated by Selenium IDE
package chatsistema;

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
public class CreaChatTest {
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
  
  private boolean isAttributePresent(WebElement element, String attribute) {
  Boolean result = false;
  try {
  	String value = element.getAttribute(attribute);
  	if (value != null){
  		result = true;
  	}
  } catch (Exception e) {}
 	return result;
  }
  
  @Test
  public void testCreazioneChatAmiciNonSelezionati() {
    driver.get("http://localhost:8080/SocialNotes/");
    driver.manage().window().setSize(new Dimension(1936, 1056));
    driver.findElement(By.linkText("Accedi")).click();
    driver.findElement(By.id("inputEmail")).click();
    driver.findElement(By.id("inputEmail")).sendKeys("fry");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputPassword")).sendKeys("Despacit0");
    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
    driver.findElement(By.cssSelector(".bi-chat-text-fill > path")).click();
    driver.findElement(By.linkText("Crea Nuova Chat")).click();
    driver.findElement(By.id("exampleFormControlTextarea1")).click();
    driver.findElement(By.id("exampleFormControlTextarea1")).sendKeys("nuova chat");
    driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".alert"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void testCreazioneChatEffettuata() {
    driver.get("http://localhost:8080/SocialNotes/homepage.jsp");
    driver.manage().window().setSize(new Dimension(1936, 1056));
    driver.findElement(By.linkText("Accedi")).click();
    driver.findElement(By.id("inputEmail")).click();
    driver.findElement(By.id("inputEmail")).sendKeys("fry");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputPassword")).sendKeys("Despacit0");
    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
    driver.findElement(By.cssSelector(".bi-chat-text-fill > path")).click();
    driver.findElement(By.linkText("Crea Nuova Chat")).click();
    driver.findElement(By.id("exampleFormControlTextarea1")).click();
    driver.findElement(By.id("exampleFormControlTextarea1")).sendKeys("nuova chat");
    driver.findElement(By.id("inlineCheckbox1")).click();
    driver.findElement(By.cssSelector(".candidates-list:nth-child(2) #inlineCheckbox1")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
    assertThat(driver.getTitle(), is("Chat - SocialNotes"));
  }
  @Test
  public void testCreazioneChatTitoloNonInserito() {
    driver.get("http://localhost:8080/SocialNotes/");
    driver.manage().window().setSize(new Dimension(1936, 1056));
    driver.findElement(By.linkText("Accedi")).click();
    driver.findElement(By.id("inputEmail")).click();
    driver.findElement(By.id("inputEmail")).sendKeys("fry");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputPassword")).sendKeys("Despacit0");
    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
    driver.findElement(By.cssSelector(".bi-chat-text-fill")).click();
    driver.findElement(By.linkText("Crea Nuova Chat")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
    
    assertEquals(isAttributePresent(driver.findElement(By.id("exampleFormControlTextarea1")),"required"),true);
  }
}