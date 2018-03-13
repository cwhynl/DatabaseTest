package Sprog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class writeoutdata {
	
	//往Excel写入一行数据
	public static void writedata() {
		int counms=0;
		String path="C:\\Users\\Administrator\\Desktop\\222\\导出数据.xlsx";
		File excel=new File(path);
		XSSFWorkbook wb=new XSSFWorkbook();
		FileOutputStream out =null;
		Sheet sheet=null;
		ResultSet res;
		 try {
			 sheet=wb.createSheet("关系表");//创建excel之前要先创建一个sheet
			out = new FileOutputStream(path);
			sheet.setDefaultColumnWidth(12);
			//设置表头
			Row row=sheet.createRow(0);
			row.createCell(0).setCellValue("id");
			row.createCell(1).setCellValue("openid");
			row.createCell(2).setCellValue("sell_id");
			row.createCell(3).setCellValue("sell_apply_id");
			row.createCell(4).setCellValue("failure_time");
			row.createCell(5).setCellValue("status");
			row.createCell(6).setCellValue("create_time");
			row.createCell(7).setCellValue("update_time");
			res= mysql_con.Update("select count(*) from wp_sell_apply_relation");
			counms= Integer.valueOf(mysql_con.getData(res,"count(*)"));
			for(int i=1;i<=counms;i++){
				write(sheet,getdata(Integer.toString(i)),i);
			}
			wb.write(out);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//写一行数据
	public static void write(Sheet sh,List<String> li,int rows){
		Row r=sh.createRow(rows);
		int i=0;
		for(String str:li){
			Cell cell=r.createCell(i);
			sh.autoSizeColumn(i);
			cell.setCellValue(str);
			i++;
		}
	}
	//数据库读取一行数据，放在list<String>
	public static List<String> getdata(String id) {
		ResultSet res;
		List<String> li=new ArrayList<String>();
		int i=7;
		try {
			res = mysql_con.Update("select * from wp_sell_apply_relation where id='"+id+"'");
//			res = mysql_con.Update("select * from wp_sell_apply_relation where id=3");
			while (res.next()) {
			String comn1=res.getString("id");
			String comn2=res.getString("openid");
			String comn3=res.getString("sell_id");
			String comn4=res.getString("sell_apply_id");
			String comn5=res.getString("failure_time");
			String comn6=res.getString("status");
			String comn7=res.getString("create_time");
			String comn8=res.getString("update_time");
			li.add(comn1);
			li.add(comn2);
			li.add(comn3);
			li.add(comn4);
			li.add(comn5);
			li.add(comn6);
			li.add(comn7);
			li.add(comn8);}
		return li;	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;		
	}
   @Test
   public void f() {
	  writedata();
   }
  }
