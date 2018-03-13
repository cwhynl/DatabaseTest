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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


public class Base {

	public static Boolean flag = true; // 判断控件是否存在
	public static Boolean isFail =false;//判断一条case是否执行成功
	@SuppressWarnings("rawtypes")
	public static AppiumDriver driver;
	public static Map<String,String> result=new HashMap<String, String>();

	/**
	 * 通过id判断控件存不存在，存在找控件的等待
	 */
	public static boolean isPlay(String id) {
		for (int i = 0; i <= 5; i++) {
			if (isPlayById(id)) {
				break;
			}
			sleep(500);
			if (i == 4) {
				break;
			}
		}
		if (isPlayById(id)) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isPlayXpath(String xpath) {
		for (int i = 0; i <= 5; i++) {
			if (isPlayBy(By.xpath(xpath))) {
				break;
			}
			sleep(500);
			if (i == 2) {
				break;
			}
		}
		if (isPlayBy(By.xpath(xpath))) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPlayById(String id) {
		try {
			driver.findElementById(id).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 通过by判断控件存不存在，判断完就立即返回
	 */
	public static boolean isPlayBy(By by) {
		try {
			driver.findElement(by).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void getActivity() {
		@SuppressWarnings("rawtypes")
		String s = ((AndroidDriver) driver).currentActivity();
		System.out.println(s);
	}

	/**
	 * 往控件输入信息
	 */
	public static void Input(WebElement el, String value) {
		el.sendKeys(value);
	}

	/**
	 * 点击某个控件
	 */
	public static void click(WebElement el) {
		el.click();
	}
    /**
     * 通过不同的方式点击
     */
	public static WebElement el(String id){
		if(isPlay(id)){
			return findEl(id);
		}else if(isPlayXpath(id)){
			return findByXpath(id);
		}else{
			print(id+"控件不存在");
			return findEl(id);
		}
	}
	
	/**
	 * 输出信息
	 */
	public static void print(String flag2) {
		if (flag2 == "") {
			Reporter.log("在：" + Time() + "输出了空字符串");
			System.out.println("在：" + Time() + "输出了空字符串");
		} else {
			Reporter.log("在：" + Time() + flag2);
			System.out.println("在：" + Time() + flag2);
		}
	}
	/**
	 * 按返回键
	 */
	@SuppressWarnings("rawtypes")
	public static void back() {
		((AndroidDriver) driver).pressKeyCode(0);
	}

	/**
	 * 隐式等待
	 */
	public void waitAuto(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/**
	 * 显式等待
	 */
	public static WebElement waitAuto(WebDriver driver, final By by,
			int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		for (int attempt = 1; attempt <= waitTime; attempt++) {
			try {
				driver.findElement(by);
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				print("等了" + attempt + "秒");
				if (attempt == waitTime) {
					break;
				}
			}
		}
		if (isPlayBy(by)) {
			flag = true;
			return wait
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		} else {
			flag = false;
			return null;
		}
	}
	@SuppressWarnings("rawtypes")
	public static WebElement waitAuto(String text, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		for (int attempt = 1; attempt <= waitTime; attempt++) {
			try {
				((AndroidDriver) driver).findElementByAndroidUIAutomator(text);
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				print("等了" + attempt + "秒");
				if (attempt == waitTime) {
					break;
				}
			}
		}
		if (isPlayByText(text)) {
			flag = true;
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.tagName(text)));
		} else {
			flag = false;
			return null;
		}
	}
	@SuppressWarnings("rawtypes")
	public static boolean isPlayByText(String text) {
		try {
			((AndroidDriver) driver).findElementByAndroidUIAutomator(text).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 存在控件的等待，找不到就报错
	 * @param id
	 * @return
	 */
	public static WebElement findEl(String id) {
		
		if (isPlay(id)) {
			return waitAuto(driver, By.id(id), 3);
		} else if(isPlayXpath(id)){
			return waitAuto(driver, By.xpath(id), 3);
		}
		else {
			print("控件：" + id + "不存在");
			assertTrue(false);
			return null;
		}
	}
	public static WebElement findByClass(String id) {
		WebElement el = waitAuto(driver, By.className(id), 3);
		if (flag) {
			return el;
		} else {
			print("控件：" + id + "不存在");
			assertTrue(false, "等了3秒还找不到控件");
			return null;
		}
	}
	public static WebElement findByXpath(String id) {
		WebElement el = waitAuto(driver, By.xpath(id), 3);
		if (flag) {
			return el;
		} else {
			print("控件：" + id + "不存在");
			assertTrue(false, "等了3秒还找不到控件");
			return null;
		}
	}
	public static WebElement findByText(String id) {
		WebElement el = waitAuto(id, 3);
		if (flag) {
			return el;
		
		} else {
			print("控件：" + id + "不存在");
			assertTrue(false, "等了3秒还找不到控件");
			return null;
		}
	}
	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public static String Time() {
		SimpleDateFormat formattime1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		long ctime = System.currentTimeMillis();
		String currenttime = formattime1.format(new Date(ctime));
		return currenttime;
	}
	public static void sleep(int s) {
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@BeforeSuite
	public void beforeSuite() {
//		
//			Sheet sheet=ExcelUtil.getSheet(0);
//			int j=sheet.getLastRowNum();
//			for(int t=1;t<=j;t++){
//				writeExcel.result.put(Integer.toString(t), "F");	
//			}
//			writeExcel.WriteFirstColumn(writeExcel.result);
		
		cmdCtrl.startServer("4723");
	}
	@AfterSuite
	public void afterSuite() {
		driver.quit();
		cmdCtrl.stopServer("4723");
		cmdCtrl.openHtml();
	}
	final static int i=2;
	@SuppressWarnings("rawtypes")
	@BeforeClass
	public static void BeforeClass(){
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", getInfo(i).get("deviceName"));
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", getInfo(i).get("platformVersion"));
		cap.setCapability("unicodeKeyboard", "true");
		cap.setCapability("resetKeyBoard", "true");
		cap.setCapability("appPackage", "com.thetiger.ziwork");
		cap.setCapability("appActivity",
				"com.thetiger.ziwork.ui.activity.SplashActivity");
		// cap.setCapability("sessionOverride", true);
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					cap);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	@AfterClass
	public void afterClass(){	
		
		driver.quit();
	}
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
	public static void dealData(String s0,String s1,String s2,String s3){
		
		int i=0;
		if(s2.equalsIgnoreCase("click")){
			i=1;
		}else if(s2.equalsIgnoreCase("input")){
			i=2;
		}else if(s2.equalsIgnoreCase("back")){
			i=3;
			
		}else if(s2.equalsIgnoreCase("Strongclick")){
			i=4;
		}else if(s2.equalsIgnoreCase("restart")){
			i=5;
		}else if(s2.equalsIgnoreCase("home")){
			i=6;
		}
		else if(s2.equalsIgnoreCase("ispopup")){
			i=7;
		}
		else if(s2.equalsIgnoreCase("scrolldown")){
			i=8;
		}
		else if(s2.equalsIgnoreCase("wait")){
			i=9;
		}
		if(i==1){
			findEl(s1).click();
			print("点击了按钮："+s1);
			
			}
		else if(i==2){
			if(s3.equalsIgnoreCase("随机")){
				findEl(s1).sendKeys(phoneNum());
				result.put(s0,"P");
			}else{	
				findEl(s1).sendKeys(s3);
			result.put(s0,"P");
			print("输入了内容");
			}}
		else if(i==3){
			back();
			print("按了返回键");
			if(isPlay("com.thetiger.ziwork:id/home_banner_sdv")){
				findEl("com.thetiger.ziwork:id/close_iv").click();
			}
		}else if(i==4){
			StringClick();
			sleep(1500);
			print("通过坐标点击");
		}
		else if(i==0){
			result.put(s0,"P");
			print("输入有误");	
		}else if(i==5){
			driver.quit();
			BeforeClass();
			result.put(s0,"P");
			print("重启了app");
			sleep(2000);
		}
		else if(i==6){
             while(true){
				if(isPlay("com.thetiger.ziwork:id/main_tab_home")){
					break;
				}
				back();
			}
			result.put(s0,"P");
			print("输入有误");
			
		}
		else if(i==7){
			if(isPlay(s1)){
				findEl(s1).click();
			}else{
				result.put(s0,"P");
			}
		}
		else if(i==8){
			int t=0;
			int x=(int) (0.5*driver.manage().window().getSize().width);
			int y1=(int) (0.3*driver.manage().window().getSize().height);
			int y2=(int) (0.6*driver.manage().window().getSize().height);
			while(true){
				t++;
				driver.swipe(x, y2, x, y1, 500);
				if(isPlay(s1)){
					break;
				}
				if(t>=3){break;
				}
			}
		}	
		}
	@AfterMethod
		public void failDeal(){
		print("isFail的状态："+isFail);
		}
	/**
	 * 处理web操作,只有chrome内核可以用
	 * @param args
	 */
	public static void webdeal(String s1,String s2,String s3){
		@SuppressWarnings("unchecked")
		Set<String> context = driver.getContextHandles();
        for(String contextname : context){
        System.out.println(contextname);//打印
           if(contextname.contains("WEBVIEW"))
           driver.context(contextname);
        }
        if(s2=="webclick"){
        	el(s1).click();
        }else if(s2=="webinput"){
        	el(s1).sendKeys(s3);
        }
        driver.context("NATIVE");
	}
	/**
	 * 强制点击
	 * @param args
	 */
	public static void StringClick(){
		int x=(int) (0.5*driver.manage().window().getSize().width);
		int y=(int) (0.95*driver.manage().window().getSize().height);
		System.out.println("横坐标："+x);
		System.out.println("纵坐标："+y);
		sleep(1500); 
		driver.tap(1, x, y, 0);
	}
	public static void main(String[] args){

		
	}
}
