package framework;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

public class Data {
	
	private static ThreadLocal<Data> dthread=new ThreadLocal<>();
	public static void set(Events data2) {
		dthread.set(data2);
	}
	public static Data get() {
		return dthread.get();
	}
	public static WebDriver driver;
	public static HashMap<String, String> alldata ;
	
	
	
	

}
