package StepDefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.cart;
import pages.checkout;
import pages.home;
import pages.login;

public class TrusTraceTestcaseSteps {
	
	WebDriver driver = null;
	login loginpage = new login();
	
	@Given("^user launch the (.*) in desired (.*)$")
	public void lauch_browser(String url,String browser) {
		String projectPath = System.getProperty("user.dir");
		if(browser.equals("chrome")) {
	    System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/drivers/chromedriver.exe");
	    driver = new ChromeDriver();
		}
		else if(browser.equals("edge")) {
		System.setProperty("webdriver.edge.driver", projectPath+"/src/test/resources/drivers/msedgedriver.exe");
		driver = new EdgeDriver();
		}
		else if(browser.equals("firefox")) {
		System.setProperty("webdriver.gecko.driver", projectPath+"/src/test/resources/drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		}
	    driver.manage().window().maximize();
	    driver.get(url);
	  }

	@And("^user login with (.*) and (.*)$")
	public void login_user(String username,String password) throws InterruptedException {
	    driver.findElement(By.xpath(login.txt_username)).sendKeys(username);
	    driver.findElement(By.xpath(login.txt_password)).sendKeys(password);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(login.btn_login)).click();
	    driver.findElement(By.xpath(home.txt_title)).isDisplayed();
	}

	@When("^user add the product to cart$")
	public void user_add_the_product_to_cart() throws InterruptedException {
	   driver.findElement(By.xpath(home.btn_addcart)).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath(home.icn_cart)).click();
	   driver.findElement(By.xpath(cart.txt_title)).isDisplayed();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath(cart.btn_checkout)).click();
	   }

	@And("^user provides the (.*),(.*),(.*) and checkout$")
	public void checkout_info(String firstname, String lastname, String zip) throws InterruptedException {
	    driver.findElement(By.xpath(checkout.txt_chkout)).isDisplayed();
	    driver.findElement(By.xpath(checkout.txt_firstname)).sendKeys(firstname);
	    driver.findElement(By.xpath(checkout.txt_lstname)).sendKeys(lastname);
	    driver.findElement(By.xpath(checkout.txt_zip)).sendKeys(zip);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(checkout.btn_continue)).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(checkout.btn_finish)).click();
	}

	@Then("^user should verify the success message$")
	public void user_should_verify_the_success_message() {
	    driver.findElement(By.xpath(checkout.txt_success)).isDisplayed();
	    driver.quit();
	}
	RequestSpecification request;
	private  static  Response response;

	@Given("^post the API (.*)$")
	public void post_api(String url) {
		RestAssured.baseURI  =  url;
		request  =  RestAssured.given();
		request.header("Content-Type",  "application/json"); 
	}

	@When("^provide the (.*),(.*),(.*)$")
	public void request_body(String body,String title,String userid) {
	    response =  request.body("{\"title\": \""+title+"\",\"body\": \""+body+"\",\"userId\": "+userid+"}").post();
	}

	@Then("^check the status code (.*)$")
	public void response_code(int statuscode) {
		Assert.assertEquals(statuscode, response.getStatusCode());    
	}

	@Then("^contains the response with (.*),(.*),(.*)$")
	public void response_body(String body,String title,int userid) {
		JsonPath j = response.jsonPath();
		Assert.assertEquals(title, j.get("title"));
		Assert.assertEquals(body, j.get("body"));
		Assert.assertEquals(userid,(int) j.get("userId"));
	}

}
