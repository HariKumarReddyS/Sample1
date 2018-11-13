
package framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Events extends Data {
	/*
	 * public static void main(String[] args) { LaunchBrowser("ie"); }
	 */
	
	private static ThreadLocal<Events> ethread=new ThreadLocal<>();
	public static void set(Events data1) {
		ethread.set(data1);
	}
	public static Events get() {
		return ethread.get();
	}
	@Test
	@Parameters("Browser")
	public static void LaunchBrowser(@Optional String Browser) {

		switch (Browser.toLowerCase()) {
		case "chrome":
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--New-Window", "--start-maximized", "--disable-extensions", "--disable-infobars",
					"--dns-prefetch-disable", "lang=en_us.UTF-8", "--ignore-certificate-errors");
			co.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			/*
			 * HashMap<String, Object> prefes=new HashMap<String, Object>();
			 * prefes.put("credentials_enable_service", false);
			 * prefes.put("proFile.password_manager_enabled", false);
			 * co.setExperimentalOption("prefes", prefes);
			 */ System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			Data.driver = new ChromeDriver(co);
			System.out.println(Browser);
			break;

		case "ie":
			InternetExplorerOptions ieoptions = new InternetExplorerOptions();
			ieoptions.ignoreZoomSettings();
			ieoptions.destructivelyEnsureCleanSession();
			System.setProperty("webdriver.ie.driver", "Drivers/IEDriverServer.exe");
			Data.driver = new InternetExplorerDriver(ieoptions);
			break;

		case "firefox":
			try {
				Thread.sleep(5000);

				FirefoxOptions fo = new FirefoxOptions().setLogLevel(FirefoxDriverLogLevel.TRACE);
				System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
				Data.driver = new FirefoxDriver(fo);

			} catch (InterruptedException e) {
	
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Given Browser Does not Exist.");
			System.exit(0);
			break;

		}

		Data.driver.get(Data.alldata.get("url"));

	}

}
