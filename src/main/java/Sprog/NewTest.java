package Sprog;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({Listener.class})
public class NewTest extends Base{
	@Test(dataProvider ="testing")
	  public void testcase(String s1,String s2,String s3,String s4,String s5,String s6,String s7){
		dealData(s1,s4,s5,s6);
	
	  }
	
	  @DataProvider
	  public Object[][] testing(){
		  Object[][] j=auto_database.changeObject();
			return j;
	  }
}
