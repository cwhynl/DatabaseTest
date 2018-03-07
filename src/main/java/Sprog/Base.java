package Sprog;

import static org.testng.Assert.assertTrue;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class Base {

	public static Boolean flag = true; // 判断控件是否存在
	public static Boolean isFail =false;//判断一条case是否执行成功
	@SuppressWarnings("rawtypes")
	public static AppiumDriver driver;
	public static Map<String,String> result=new HashMap<String, String>();

	
	final static int i=3;
	@SuppressWarnings("rawtypes")
	@BeforeClass
	public static void BeforeClass(){
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", getInfo(i).get("deviceName"));
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", getInfo(i).get("platformVersion"));
		cap.setCapability("unicodeKeyboard", "true");
		cap.setCapability("resetKeyBoard", "true");
		cap.setCapability("appPackage", "com.tencent.mm");
		cap.setCapability("appActivity",".ui.LauncherUI");
		 cap.setCapability("fastReset", "false");
		  cap.setCapability("fullReset", "false");
		  cap.setCapability("noReset", "true");
		// cap.setCapability("sessionOverride", true);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		try {
			
//			driver = webdriver.Remote("http://localhost:4723/wd/hub", desired_caps);
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					cap);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		 String ChromeDriver="D:\\Chromedriver\\chromedriver.exe";
//		  System.setProperty("webdriver.chrome.driver", ChromeDriver);
//		  driver=new ChromeDriver();
//		  String url="http://demo.www2.lieduoduo.com/login/login";
//		  driver.get(url);
		 
	}
	WebDriver driver1;
	@Test
	public void test() throws Exception{
		System.out.println("111");
//		driver.context("WEBVIEW_com.tencent.mm:appbrand2");
		Thread.sleep(3000);
		 String ChromeDriver="D:\\Chromedriver\\chromedriver.exe";
		  System.setProperty("webdriver.chrome.driver", ChromeDriver);
		  driver1=new ChromeDriver();
		driver1.get("http://demo2016.thetiger.com.cn/zikeweb");
		Thread.sleep(3000);
		
		
	}
	/**
	 * 获取安卓设备信息
	 */
	public static  Map<String,String> getInfo(int i){
		Map<String,String> maps=new HashMap<String, String>();
		switch (i) {
		case 1:
			maps.put("deviceName", "127.0.0.1:62001");
			maps.put("platformVersion","4.4.2");
			break;
        case 2:
        	maps.put("deviceName", "eddffa38");
			maps.put("platformVersion","5.1.1");
			break;	
        case 3:
        	maps.put("deviceName", "HMK7N17327014230");
			maps.put("platformVersion","7.0");
			break;	
		default:
			break;
		}
		return maps;
	}
//	@AfterClass
//	public void afterClass(){	
//		
//		driver.quit();
//	}
	/**
	 * 随机生成手机号码
	 */
	public static String phoneNum() {
		int i = 10000000 + (int) (Math.random() * 99999999);
		String pn = "131" + Integer.toString(i);
		return pn;
	}

	/**
	 * 处理数据
	 * @param s1操作的控件
	 * @param s2操作的类型
	 * @param s3断言的控件
	 */
//	public static void dealData(String s0,String s1,String s2,String s3){
//		readExcel.s=s0;
//		int i=0;
//		if(s2.equalsIgnoreCase("click")){
//			i=1;
//		}else if(s2.equalsIgnoreCase("input")){
//			i=2;
//		}else if(s2.equalsIgnoreCase("back")){
//			i=3;
//			
//		}else if(s2.equalsIgnoreCase("Strongclick")){
//			i=4;
//		}else if(s2.equalsIgnoreCase("restart")){
//			i=5;
//		}else if(s2.equalsIgnoreCase("home")){
//			i=6;
//		}
//		else if(s2.equalsIgnoreCase("ispopup")){
//			i=7;
//		}
//		else if(s2.equalsIgnoreCase("scrolldown")){
//			i=8;
//		}
//		else if(s2.equalsIgnoreCase("wait")){
//			i=9;
//		}
//		if(i==1){
//			findEl(s1).click();
//			result.put(s0,writeExcel.res(s3));
//			print("点击了按钮："+s1);
//			
//			}
//		else if(i==2){
//			if(s3.equalsIgnoreCase("随机")){
//				findEl(s1).sendKeys(phoneNum());
//				result.put(s0,"P");
//			}else{	
//				findEl(s1).sendKeys(s3);
//			result.put(s0,"P");
//			print("输入了内容");
//			}}
//		else if(i==3){
//			back();
//			result.put(s0,writeExcel.res(s3));
//			print("按了返回键");
//			if(isPlay("com.thetiger.ziwork:id/home_banner_sdv")){
//				findEl("com.thetiger.ziwork:id/close_iv").click();
//			}
//		}else if(i==4){
//			StringClick();
//			sleep(1500);
//			result.put(s0,writeExcel.res(s3));
//			print("通过坐标点击");
//		}
//		else if(i==0){
//			result.put(s0,"P");
//			print("输入有误");	
//		}else if(i==5){
//			driver.quit();
//			BeforeClass();
//			result.put(s0,"P");
//			print("重启了app");
//			sleep(2000);
//		}
//		else if(i==6){
//             while(true){
//				if(isPlay("com.thetiger.ziwork:id/main_tab_home")){
//					break;
//				}
//				back();
//			}
//			result.put(s0,"P");
//			print("输入有误");
//			
//		}
//		else if(i==7){
//			if(isPlay(s1)){
//				findEl(s1).click();
//				result.put(s0,writeExcel.res(s3));
//			}else{
//				result.put(s0,"P");
//			}
//		}
//		else if(i==8){
//			int t=0;
//			int x=(int) (0.5*driver.manage().window().getSize().width);
//			int y1=(int) (0.3*driver.manage().window().getSize().height);
//			int y2=(int) (0.6*driver.manage().window().getSize().height);
//			while(true){
//				t++;
//				driver.swipe(x, y2, x, y1, 500);
//				if(isPlay(s1)){
//					break;
//				}
//				if(t>=3){break;
//				}
//			}
//			result.put(s0,writeExcel.res(s3));
//		}	
//		}
//	@AfterMethod
//		public void failDeal(){
//		print("isFail的状态："+isFail);
//			if(isFail){
//				result.put(readExcel.s,"F");
//				isFail=false;
//			}
//		}
	
	public static void main(String[] args){

		
	}
}
