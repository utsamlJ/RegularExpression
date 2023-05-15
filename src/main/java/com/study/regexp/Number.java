package com.study.regexp;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @USER: jys
 * @DATE: 2023/5/13
 * @TIME: 10:12
 * @DAY_NAME_FULL: 星期六
 */
public class Number {
//        public static void main(String[] args) {
//        try {
//            FileWriter writer = new FileWriter("D:\\aaaaaaaaaaaa\\number2.txt");
//            for (int i = 0; i < 100000; i++) {
////                不要前导0
////                writer.write(String.valueOf(i));
////                前导0
//                String str = String.format("%05d", i);
//                writer.write(str);
//                if (i != 99999) {
//                    writer.write(",");
//                }
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("numbers");

            for (int i = 0; i < 100000; i++) {
                XSSFRow row = sheet.createRow(i);
                XSSFCell cell = row.createCell(0);
//                不含前导0
//                cell.setCellValue(i);
//                含前导0
                String str = String.format("%05d", i);
                cell.setCellValue(str);
            }

            FileOutputStream outputStream = new FileOutputStream("D:\\aaaaaaaaaaaa\\number2.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
