package com.missionsky.exceltool.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 操作Excel表格的功能类
 */
public class ExcelReadTool {
   // private InputStream fis;
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private Row row;
    

    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组
     */
    public String[] readExcelTitle(InputStream is) {
        try {
            wb = new XSSFWorkbook(is);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getLastCellNum();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            //title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, String> readExcelContent(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();//获取最后一个不为空的行的行数
        row = sheet.getRow(0);
    
        int colNum = row.getLastCellNum();//获取不为空的列的个数
     // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
            	  // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                str += getCellFormatValue(row.getCell((short) j)).trim() + " ";
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }

    
    /**
     * 根据Cell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
        	// 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case Cell.CELL_TYPE_NUMERIC:
            case Cell.CELL_TYPE_FORMULA: {
            	   // 判断当前的cell是否为Date
                if (DateUtil.isCellDateFormatted(cell)) {
                	 // 如果是Date类型则，转化为Data格式

                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();

                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);

                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值ֵ
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
    
    
    
    public static void main(String[] args) {
        try {
        	 // 对读取Excel表格标题测试
            InputStream is = new FileInputStream("d:\\员工考勤表201702.xlsx");
            ExcelReadTool excelReader = new ExcelReadTool();
            String[] title = excelReader.readExcelTitle(is);
            System.out.println("查询表格标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }

         // 对读取Excel表格内容测试
            InputStream is2 = new FileInputStream("d:\\员工考勤表201702.xlsx");
            Map<Integer, String> map = excelReader.readExcelContent(is2);
            System.out.println("查询表格内容");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
    }

    
}