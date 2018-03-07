package Sprog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class mysql_con {
	/**
	 * 建立mysql连接
	 * @param args
	 */
	public static Connection con=null;
	public static void connection(){
		; //定义一个连接对象
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();  //mysql的驱动
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/test", "root", "mysql"); //连接本地mysql
//			smt=con.createStatement();//创建声明
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 创建、删除、更新一条数据
	 * @param args
	 * @throws SQLException 
	 */
    public static void Insert(String sql,int index) throws SQLException{
    	connection();
    	Statement smt=null;
    	smt=con.createStatement();
    	try {
    		if(index==0){
			smt.executeUpdate(sql);}else{
				smt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    /**
	 *查询数据数据
	 * @param args
     * @throws SQLException 
	 */	
	public static ResultSet Update(String sql) throws SQLException{   //0表示删除
		connection();
		Statement smt=null;
		smt=con.createStatement();
		ResultSet res=null;
		try {
		 res=smt.executeQuery(sql);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 根据res和字段查询到对应的数据
	 * @param args
	 * @throws Exception 
	 */
	public static String getData(ResultSet res,String code) throws Exception{
		String value="";
		   while (res.next()) {
			   value = res.getString(code);
//			   System.out.println(value);
			  
		   } return value;
	}
	public static String randomColmn(int t){//1为6位字母，2为1，2的随即数 3为1-30随机数 4为失败时间
		String s = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random r = new Random();
		String result="";
		for (int i =0; i < 6; i++ )
		{
		int n = r.nextInt(62);
		result += s.substring(n,n+1 );
		}
//		System.out.println(result);
		if(t==1){
			return result;
		}else if(t==2){
			int s1=1+r.nextInt(2);
			result=Integer.toString(s1);
//			System.out.println(s1);
			return result;
		}else if(t==3){
			int s1=1+r.nextInt(30);
			result=Integer.toString(s1);
			System.out.println(s1);
			return result;
		}else if(t==4){
			long ss=1520391321-1519786521;
			long time=System.currentTimeMillis();
			String t1=String.valueOf(time);
			String t2=t1.substring(0, 10);
			long s1=Long.parseLong(t2);
			long s2=s1+ss;
			System.out.println(s2);
			return String.valueOf(s2);
		}else if(t==5){
			long time=System.currentTimeMillis();
			String t1=String.valueOf(time);
			String t2=t1.substring(0, 10);
			return t2;
		}
		else{
			return null;
		}
		
	}
	
	public static void createData(){
		
		for(int i=101;i<=200;i++){
		try {	
		Insert("INSERT INTO wp_sell_apply_relation values("+i+",'"+randomColmn(1)+"','"
				+randomColmn(3)+"','"+randomColmn(3)+"','"
				+randomColmn(4)+"','"+randomColmn(2)+"','"
				+randomColmn(5)+"','"+randomColmn(5)+"')",0);
		
//			Insert("INSERT INTO wp_sell_apply_relation values('2','dsdfed','11','23','1518780781','1','1518175982','1518175982')",0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
	/**
	 * 根据创建时间，失败小于当前时间的，status非2的数据改为1
	 * @param args
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public static void runscript() throws SQLException, Exception{
		String t1=String.valueOf(System.currentTimeMillis());
		String t2=t1.substring(0, 10);  
		Long cur_time=Long.parseLong(t2);//当前时间
		//获取失败时间
		Long fail_time;
		//查询失败时间地数据
		ResultSet res=Update("select failure_time,id from wp_sell_apply_relation");
		//把status状态设置为1
		while (res.next()) {
			String  value1 = res.getString("failure_time");
			String value2=res.getString("id");
			 System.out.println(value1+"+"+value2);
			fail_time=Long.parseLong(value1);
			 if(fail_time>cur_time){
			Insert("update wp_sell_apply_relation set status='2' where id='"+value2+"'",0);
		} 
		   }
	}  
	
	
	
	public static void main(String[] args) {
		try {
//			System.out.println(getData(Update("select failure_time from wp_sell_apply_relation"),
//					"failure_time"));
			createData();
			runscript();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
