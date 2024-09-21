package frame;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class capabilities {
	
	public static String appactivity;
	public static String apppackage;
	public static String deviceName;
	
	public static void startemulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/test/resources/extension.bat");
		Thread.sleep(5000);
	}

	
	
	public static AndroidDriver<AndroidElement> cap(String appactivity, String apppackage, String deviceName) throws IOException, InterruptedException {
	
		FileReader gp = new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\global.properties");
		Properties pro = new Properties();
		pro.load(gp);
		
		if(deviceName.equals("my_emulator")) {
			startemulator();
			Thread.sleep(10000);
		}
		
		appactivity = pro.getProperty("appactivity");
		apppackage = pro.getProperty("apppackage");
		deviceName = pro.getProperty("deviceName");
		
		
		DesiredCapabilities dc = new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, apppackage);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appactivity);
		
		//for android we have android driver //  we have androidelement
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
		
		return driver;
		
	}

}
