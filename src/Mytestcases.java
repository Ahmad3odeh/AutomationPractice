import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.AbstractParallelWorker.Arguments;

import net.bytebuddy.asm.Advice.Argument;
import net.bytebuddy.asm.Advice.Return;

public class Mytestcases {

	WebDriver driver = new ChromeDriver();

	String MyWibeSite = "https://codenboxautomationlab.com/practice/";

	Random rand = new Random();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeTest
	public void Mysetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(MyWibeSite);

	}

	@Test(priority = 1, enabled = true)

	public void RadioButton() {

		WebElement divforradiobutton = driver.findElement(By.id("radio-btn-example"));
		divforradiobutton.findElements(By.tagName("Input")).size();

		int RandomIndex = rand.nextInt(divforradiobutton.findElements(By.tagName("Input")).size());
		WebElement SelectedInput = divforradiobutton.findElements(By.tagName("Input")).get(RandomIndex);
		SelectedInput.click();
		boolean ActuleValue = SelectedInput.isSelected();
		boolean ExpectedValue = true;
		Assert.assertEquals(ActuleValue, ExpectedValue);

	}

	@Test(priority = 2, enabled = true)
	public void DynamicDropdown() throws InterruptedException {

		String[] MyRandomtwoCharacter = { "Ao", "Ma", "Ah", "aj", "Gh", "Ac", "Ty", "La" };
		int RandomIndex = rand.nextInt(MyRandomtwoCharacter.length);

		String MyInputData = MyRandomtwoCharacter[RandomIndex];
		WebElement DropdownValue = driver.findElement(By.id("autocomplete"));
		DropdownValue.sendKeys(MyInputData);
		Thread.sleep(1000);
		DropdownValue.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

		// js.executeScript("return arguments[0].value" دائما ثابت لترجيع الفيمه داخل
		// الداينمك دروب داون

		String DataInsitMyInput = (String) js.executeScript("return arguments[0].value", DropdownValue);

		String UpdateData = DataInsitMyInput.toLowerCase();
		boolean ActualResurt = UpdateData.contains(UpdateData.toLowerCase());

		Assert.assertEquals(ActualResurt, false);
	}

	@Test(priority = 3, enabled = true)
	public void SelectTag() {

		WebElement MySelectElemant = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(MySelectElemant);

		int RandomIndex = rand.nextInt(1, 4);
		selector.selectByIndex(RandomIndex);

		// selector.selectByVisibleText("Appium");
		// selector.deselectByValue("option1");
	}

	@Test(priority = 4, enabled = true)
	public void Checkbox() {
		WebElement DivCheckBox = driver.findElement(By.id("checkbox-example"));

		List<WebElement> MyCheckBox = DivCheckBox.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < MyCheckBox.size(); i++) {

			MyCheckBox.get(i).click();
		}

	}

	@Test(priority = 5, enabled = true)
	public void SwitchWindow() throws InterruptedException {

		driver.findElement(By.id("openwindow")).click();
		Thread.sleep(1000);
		List<String> WindowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(WindowHandles.get(1));
		// driver.findElement(By.id(MyWibeSite));
		Thread.sleep(1000);
		driver.switchTo().window(WindowHandles.get(0));

	}

	@Test(priority = 6, enabled = true)
	public void SwitchTab() throws InterruptedException {

		driver.findElement(By.id("opentab")).click();
		;

		List<String> WindowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(WindowHandles.get(1));
		Thread.sleep(5000);

		System.out.println(driver.getCurrentUrl());

		driver.switchTo().window(WindowHandles.get(0));

	}

	@Test(priority = 7, enabled = true)
	public void SwitchAlert() throws InterruptedException {

		// alert tip
		// driver.findElement(By.id("alertbtn")).click();
		// driver.switchTo().alert().accept();

		// cobfirm alert

		driver.findElement(By.id("name")).sendKeys("Ahmad");
		driver.findElement(By.id("confirmbtn")).click();

		Thread.sleep(2000);

		String Actualdata = driver.switchTo().alert().getText();
		String Exbecteddata = "Hello Ahmad, Are you sure you want to confirm?";

		Assert.assertEquals(Actualdata, Exbecteddata);

		driver.switchTo().alert().accept();

		// to cancel alert
		// driver.switchTo().alert().dismiss();

	}

	@Test(priority = 8, enabled = true)
	public void WebTable() {
		WebElement TheTable = driver.findElement(By.id("product"));

		List<WebElement> AllDataInTableList = TheTable.findElements(By.tagName("td"));

		for (int i = 1; i < AllDataInTableList.size(); i = i + 3) {
			System.out.println(TheTable.findElements(By.tagName("td")).get(i).getText());

		}

	}

	@Test(priority = 9, enabled = true)
	public void ElementDisplayed() {

		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		HideButton.click();

		boolean Actualresult = driver.findElement(By.id("displayed-text")).isDisplayed();

		Assert.assertEquals(Actualresult, false);

		WebElement ShowButton = driver.findElement(By.id("show-textbox"));
		ShowButton.click();

		boolean Actualresult1 = driver.findElement(By.id("displayed-text")).isDisplayed();

		Assert.assertEquals(Actualresult1, true);

	}

	@Test(priority = 10, enabled = true)
	public void Enabled_Disabled() {

		WebElement Disabled = driver.findElement(By.id("disabled-button"));
		Disabled.click();

		WebElement Thedisplayedtext = driver.findElement(By.id("displayed-text"));

		boolean Actualresult = Thedisplayedtext.isDisplayed();

		Assert.assertEquals(Actualresult, true);

		WebElement Enabled = driver.findElement(By.id("disabled-button"));
		Enabled.click();

		WebElement TheEnabledtext = driver.findElement(By.id("displayed-text"));

		boolean Actualresult1 = TheEnabledtext.isDisplayed();

		Assert.assertEquals(Actualresult1, true);

	}

	@Test(priority = 11, enabled = true)
	public void MouseHover() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1800)");

		Actions action = new Actions(driver);

		WebElement TargetMouseHover = driver.findElement(By.id("mousehover"));

		action.moveToElement(TargetMouseHover).perform();
		;

		driver.findElement(By.linkText("Top")).click();
	}

	@Test(priority = 12, enabled = true)
	public void Calender() throws InterruptedException {

		driver.findElement(By.linkText("Booking Calendar")).click();
		;

		List<String> WindowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(WindowHandles.get(1));

		driver.findElement(By.linkText("Home")).click();

		driver.switchTo().window(WindowHandles.get(0));

	}

	@Test(priority = 13, enabled = true)
	public void IFrame() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,2400)");

		WebElement myFrame = driver.findElement(By.id("courses-iframe"));

		Thread.sleep(3000);
		driver.switchTo().frame(myFrame);
		js.executeScript("window.scrollTo(0,1200)");

		

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"ct_button-20c391b5\"]/a/span[2]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"ct-pagetitle\"]/div/ul/li[1]/a")).click();

	}

}
