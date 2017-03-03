package com.missionsky.exceltool.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.missionsky.exceltool.model.AttendanceModel;



public class ReadExcel {
	
	private static final Logger logger = Logger.getLogger(ReadExcel.class);
	
	public static List<AttendanceModel> readXls(String path) throws IOException, ParseException {
        //System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<AttendanceModel> list = new ArrayList<AttendanceModel>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
        	XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            //注意rowNum=0从第一行读起   1从第2行
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null && xssfRow.getCell(0) != null) {
                	AttendanceModel checkWorkAttendance = new AttendanceModel();
                    checkWorkAttendance.setNameZH(getValue(xssfRow.getCell(1)));
                    checkWorkAttendance.setNameEN(getValue(xssfRow.getCell(2)));
                    if(null == checkWorkAttendance.getNameEN() || !checkWorkAttendance.getNameEN().contains(AttendanceConstant.SPACE)) {
                    	logger.error("第" + rowNum + "行，"+ checkWorkAttendance.getNameEN() + "英文名为空或者缺少空格，会导致发送不出邮件，请完善信息！");
                    	checkWorkAttendance.setNameEN(AttendanceConstant.INVALIE_FORMAT_ENMANE);
                    }
                    checkWorkAttendance.setSickLeaveHour(getValue(xssfRow.getCell(3)));
                    checkWorkAttendance.setCasualLeaveHour(getValue(xssfRow.getCell(4)));
                    checkWorkAttendance.setAnnualLeaveHour(getValue(xssfRow.getCell(5)));
                    checkWorkAttendance.setCompensatedLeaveHour(getValue(xssfRow.getCell(6)));
                    checkWorkAttendance.setOtherHour(getValue(xssfRow.getCell(7)));
                    checkWorkAttendance.setRemark(getValue(xssfRow.getCell(8)));
                    checkWorkAttendance.setRemainAnnualLeaveHour(getValue(xssfRow.getCell(9)));
                    checkWorkAttendance.setRemainOvertime(getValue(xssfRow.getCell(10)));
                    checkWorkAttendance.setExplain(getValue(xssfRow.getCell(11)));
        			//System.out.println(JSONObject.toJSONString(checkWorkAttendance));
                    list.add(checkWorkAttendance);
                }
            }
        }
        return list;
    }
	
	
	@SuppressWarnings("static-access")
	 private static String getValue(XSSFCell xssfRow) {
		 if(null == xssfRow) {
			 return null;
		 }
		 if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
           return String.valueOf(xssfRow.getBooleanCellValue());
		 } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
           return String.valueOf(xssfRow.getNumericCellValue());
		 } else {
           return String.valueOf(xssfRow.getStringCellValue());
		 }
	 }
	
	
}
