package com.missionsky.exceltool;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.mail.AuthenticationFailedException;
import org.apache.log4j.Logger;
import com.missionsky.exceltool.model.AttendanceModel;
import com.missionsky.exceltool.util.AttendanceConstant;
import com.missionsky.exceltool.util.EmailIncludeTxt;
import com.missionsky.exceltool.util.ReadExcel;

public class App {
	
	private static final Logger logger = Logger.getLogger(App.class);

	public static void main(String args[]){
		
		try{
			
			ResourceBundle msgBundle = ResourceBundle.getBundle("emailConfig", Locale.CHINA);
			String documentPath = msgBundle.getString("document.path");
			
			// 读取excel内容到model  
			logger.info("开始读取Excel......");
			List<AttendanceModel> attendanceList = ReadExcel.readXls(documentPath);
			// 遍历信息，发送邮件
			if(null != attendanceList && attendanceList.size() > 0){
				logger.info("读取Excel成功......");
				int i = 1;
				logger.info("开始发送邮件......");
				for(AttendanceModel attendance : attendanceList){
					
					//判断英文名格式
					String nameEN = attendance.getNameEN();
					if(null == nameEN || AttendanceConstant.INVALIE_FORMAT_ENMANE.equals(nameEN)){
						//logger.error(nameEN + ": 收件人姓名为空或者英文姓名缺少空格");
						i++;
						continue;
					}
					String to = attendance.getNameEN().toLowerCase().replace(AttendanceConstant.SPACE, AttendanceConstant.DOT) + AttendanceConstant.EMAIL_SUFFIX;
					EmailIncludeTxt.sendEmail(to, attendance.getNameEN().replace(" ", ".") + AttendanceConstant.ATTENDANCE_SUBJECT, attendance.toString());
					logger.info(attendance.getNameEN().toLowerCase() + "发送成功" + i );
					i++;
					
					//间隔2秒发送
					Thread.sleep(2000);
				}
				logger.info("发送邮件完毕......");
			}else{
				
				//读取Excel失败
				logger.info("读取Excel失败");
			}
			
		}catch(IOException e){
			logger.error("IO读写错误", e);
		}catch(AuthenticationFailedException e){
			//发送邮件失败
			logger.error("邮件服务器验证失败，请检查配置信息", e);
		}catch(Exception e){
			logger.error("发送邮件失败", e);
		}
		finally {
			//
		}
		
	}
	
	

//	public static void main (String args[]){
//	    try{
//			InputStream is = new FileInputStream("D:\\test.xlsx");  
//		    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);  
//		    XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);  
//		    //总行数
//		    int trLength = xssfSheet.getLastRowNum();
//		    //获取一行
//		    Row row = xssfSheet.getRow(0);
//		    //获取总列数
//		    int tdLength = row.getLastCellNum(); 
//		    
//		   // System.out.println("No " + "Name "+ "Content "+ "Amount");
//		    for (int i = 0; i < trLength; i++) {
//		    	
//		    	Row row1 = xssfSheet.getRow(i);
//		    	
//		    	for(int j = 0; j < tdLength; j++){
//		    		
//		    		Cell cell = row1.getCell(j);
//		    		/** 
//		             * 为了处理：Excel异常Cannot get a text value from a numeric cell 
//		             * 将所有列中的内容都设置成String类型格式 
//		             */  
//		            if(cell!=null){  
//		                  cell.setCellType(Cell.CELL_TYPE_STRING);  
//		             }  
//		            
//		            System.out.print(cell +"  	");
//		    	}
//		    	System.out.println();
//		        
//		    }  
//	    }catch(Exception e){
//	    	
//	    	e.printStackTrace();
//	    	
//	    }
//	}

}
