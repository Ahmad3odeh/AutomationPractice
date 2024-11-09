import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

	@Test(priority = 1, enabled = false)

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

	@Test(priority = 2, enabled = false)
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

		Assert.assertEquals(ActualResurt, true);
	}

	@Test(priority = 3, enabled = true)
	public void SelectTag() {

		WebElement MySelectElemant = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(MySelectElemant);

		int NumberOfOption = 3;
		int RandomIndex = rand.nextInt(1,4);
		selector.selectByIndex(RandomIndex);

	//	selector.selectByVisibleText("Appium");
	//	selector.deselectByValue("option1");
	}

}
