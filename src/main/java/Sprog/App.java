package Sprog;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String USERNAME = "kobe";
	private static String PASSWORD= "123456";
	private static String HOST = "10.10.2.74";
	private static int PORT = 3306;
	private static String DATABASE = "test";
	private static String KEYSTORE_PATH = "file:/D:/key/keystore";
	private static String  KEYSTORE_PASSWORD = "zaq1xsw2";
	private static String TRUSTORE_PATH = "file:/D:/key/truststore";
	private static String  TRUSTORE_PASSWORD = "zaq1xsw2";
	
	public Connection connectMySqlDB() throws SQLException{
		
		
		
		MysqlDataSource mysqlDS=null;
		mysqlDS=new MysqlDataSource();
		mysqlDS.setUseSSL(true);
		mysqlDS.setRequireSSL(true);
		mysqlDS.setVerifyServerCertificate(false);
		mysqlDS.setLogger("com.mysql.jdbc.log.StandardLogger");
		mysqlDS.setClientCertificateKeyStoreType("JKS");
		mysqlDS.setClientCertificateKeyStoreUrl(KEYSTORE_PATH);
		mysqlDS.setClientCertificateKeyStorePassword(KEYSTORE_PASSWORD);
		mysqlDS.setTrustCertificateKeyStoreType("JKS");
		mysqlDS.setTrustCertificateKeyStoreUrl(TRUSTORE_PATH);
		mysqlDS.setTrustCertificateKeyStorePassword(TRUSTORE_PASSWORD);
		
		mysqlDS.setServerName(HOST);
		mysqlDS.setPort(PORT);
		mysqlDS.setUser(USERNAME);
		mysqlDS.setPassword(PASSWORD);
		mysqlDS.setDatabaseName(DATABASE);
		return (Connection) mysqlDS.getConnection();
	}
	
	
	

	
	public static void main(String[] args) throws SQLException{
		
	
		App  test=new App ();
	Connection conn=null;
	Statement stm = null;
	ResultSet res = null;
	conn=test.connectMySqlDB();
	stm = (Statement) conn.createStatement();
	res=stm.executeQuery("select * from t1");
	while(res.next()){
		System.out.println(res.getString(1));
		System.out.println(res.getString(2));
	}
	
	res.close();
	stm.close();
	conn.close();
	
	}
	

}
