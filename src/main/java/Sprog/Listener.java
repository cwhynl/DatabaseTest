package Sprog;


import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listener extends TestListenerAdapter{
	 @Override
	    public void onTestFailure(ITestResult tr) {
		 Base.isFail=true;
	 }
}
