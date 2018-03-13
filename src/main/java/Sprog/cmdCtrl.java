package Sprog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cmdCtrl {
	/**
	 * 运行cmd
	 */
	public static void runCmd(String cmd){
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 运行cmd并返回结果
	 */
	 public static List<String> execCmd(String command) {
	        if (!command.isEmpty()) {
	            BufferedReader br = null;
	            try {
	                //执行cmd命令
	                Process process = Runtime.getRuntime().exec("cmd.exe /c " + command);
	                br = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
	                String line = "";
					List<String> content = new ArrayList<String>();
	                while ((line = br.readLine()) != null){
	                    if (!line.isEmpty()) {
	                        content.add(line);
	                    }
	                }
	                //process.destroy();
	                return content;
	               } catch (Exception e) {
	                System.out.println("execCmd执行命令错误!" + e.getMessage());
	               } finally {
	                if (br != null) {
	                    try {
	                        br.close();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
			return null;
	    }
	/**
	 * 获取进程号
	 * @param port appium的端口号
	 * @return
	 */
	public static String getPid(String port){
		String pid=null;
		 List<String> s = execCmd("netstat -aon | findstr " + port);
		 if (!s.isEmpty()) {
             for (String line :s) {
//            	 System.out.println(line);
                 //利用正则表达式来获取pid
                 Pattern p = Pattern.compile(" (\\d{2,5})$");
                 Matcher m = p.matcher(line);
                 if (m.find()) {
                     pid = m.group(m.groupCount());
                 }
             }}
		 return pid;
		 
	}
	/**
	 * 启动端口为4723的appium服务
	 */
	public static void startServer(String port){
		String cmd = "cmd.exe   /C start appium -p 4723 -bp 4724 --session-override -a 127.0.0.1";
		
		runCmd(cmd);
		while(true){
			String i=getPid(port);
			if(i!=null){
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String cmd2="cmd.exe   /C adb connect 127.0.0.1:62001";
		runCmd(cmd2);
		
	}
	/**
	 * 根据端口号结束进程
	 * @param args
	 */
	public static void stopServer(String port){
		String i=getPid(port);
		String cmd = "taskkill -f -pid "+i;
		String cmd1 = "taskkill /f /im cmd.exe";
		runCmd(cmd);
		runCmd(cmd1);
	}
	/**
	 * 打开index.html报告
	 * @param args
	 */
	public static void openHtml(){
		String cmd = "cmd.exe /C start D:\\Workspace\\Aut\\target\\surefire-reports\\html\\index.html";
		runCmd(cmd);
	}
	
	
	public static void main(String[] args) {
//		String i=getPid("4723");
//		System.out.println(getPid("4723"));
//		
		String cmd = "taskkill /f /im cmd.exe";
//		String cmd="";
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
