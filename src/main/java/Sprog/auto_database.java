package Sprog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class auto_database {
	
	/**
	 * 从数据库读取一行的数据
	 * @param key
	 * @param id
	 * @return
	 */
	public static Object[] getdata(int id) {
		Object[] ob=new Object[7];
		ResultSet res=null;
		try {
			res= mysql_con.Update("select*from AutoTest_Data where id="+id);
		} catch (SQLException e) {
			System.out.println("数据库查询出现错误,"+"select*from AutoTest_Data where id="+id);
			e.printStackTrace();
		}
		try {
			while(res.next()){
				ob[0]=res.getString("id");
				ob[1]=res.getString("modle");
				ob[2]=res.getString("step");
				ob[3]=res.getString("viewid");
				ob[4]=res.getString("handlestyle");
				ob[5]=res.getString("data");
				ob[6]=res.getString("result");	
//				System.out.println(ob[6]);
				
			}
		} catch (Exception e) {
			System.out.println("数据库查询出现错误,获取key的数据");
			e.printStackTrace();
		}
//		for(int k=0;k<7;k++){
//			if(ob[k]==null){
//				ob[k]="";
//			}
//		}
		return ob;
	}
	//转换为二维数组
	public static Object[][] changeObject(){
        int t=0;
        Object[][] data= null;
        Object[] str=new Object[7];
		List<Object[]> li=new ArrayList<Object[]>();
		try {
			t = Integer.parseInt(mysql_con.getData(
			mysql_con.Update("select count(*) from AutoTest_Data"), "count(*)"));
		} catch (Exception e) {
            Base.print("数据查询select count(*) from AutoTest_Data出现错误");
			e.printStackTrace();
		}
		for(int i=0;i<=t-1;i++){
			str=getdata(i);
			li.add(str);
		}
		data=new Object[li.size()][];
		for(int si=0;si<=li.size()-1;si++){
			data[si]=li.get(si);
		}
		return data;
	}
	
	
	
	public static void main(String[] args) {
//		getdata(1);
		System.out.println(changeObject()[110][0]);;
	}

}
