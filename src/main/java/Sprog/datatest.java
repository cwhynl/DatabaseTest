package Sprog;

import java.sql.SQLException;

import org.testng.annotations.Test;

public class datatest {
	
  @Test
  public void f() {
	  try {
//		mysql_con.getData(mysql_con.Update("select*from wp_sell_apply_relation"),"id");
		  mysql_con.runscript();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
}
