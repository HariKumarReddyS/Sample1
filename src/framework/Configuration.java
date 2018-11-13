package framework;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Configuration {
	@BeforeSuite
	@Parameters("Environment")
	public static void close_Browsers(@Optional String Environment) {
		try {
			Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
			Runtime.getRuntime().exec("taskkill /f /im firefox.exe");
			Runtime.getRuntime().exec("taskkill /f /im microsoftedge.exe");
			Runtime.getRuntime().exec("taskkill /f /im iexplore.exe");
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im edgedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverserver.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		Environment=(Environment==null)?"qa":Environment;
		switch (Environment.toLowerCase()) {
		
		case "qa":
			Data.alldata=Utility.Readdata("TestData/ENV_Data.properties");
			break;
		default:
			break;
		}
		
	System.out.println(Environment);
	}
	
}

