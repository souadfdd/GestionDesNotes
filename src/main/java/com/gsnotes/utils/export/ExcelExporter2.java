package com.gsnotes.utils.export;




import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelExporter2 {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private ArrayList<ArrayList<String>> columnNames;
    private ArrayList<ArrayList<String>> data;
    private String sheetName = "";
    private HashMap<String,Integer> merge;

    public ExcelExporter2(ArrayList<ArrayList<String>> columnNames, ArrayList<ArrayList<String>> data, String sheetName, HashMap<String,Integer> merge) {
        this.columnNames = columnNames;
        System.out.println(columnNames);
        this.data = data;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
        this.merge=merge;

    }

    public void writeHeaderLine() {
        sheet = workbook.createSheet(sheetName);

        for(int j=0;j<columnNames.size();j++) {
            System.out.println(columnNames.get(j));
            Row row = sheet.createRow(j);

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);

            int i = 0;
            for (String it : columnNames.get(j)) {
                createCell(row, (i++), it, style);
            }


        }
    }
  /*  private void mergeCell(int firstRow,int lastRow ,int firstCol, int lastCol){
        sheet.addMergedRegion(new CellRangeAddress(firstRow=0, lastRow=0, firstCol, lastCol));
    }*/

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    public void addCoulmn(String mods,int el){
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(1);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        int i = row.getLastCellNum();
        System.out.println(i);
        createCell(row, (i),mods, style);
        sheet.addMergedRegion(new CellRangeAddress(1, 1,i,i+el-1));
        createCell(row, (i+el-1),mods, style);
    }
    private void addQueu(){
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(1);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        int i = row.getLastCellNum();
        System.out.println(i);
        createCell(row, (i++),"Moyenne", style);
        createCell(row,i,"Rang",style);


    }



   private void writeDataLines() {
       sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum()+1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
       for(int j=0;j<data.size();j++) {
           Row row = sheet.createRow(rowCount++);
           int columnCount = 0;
           for(String column:data.get(j)){
               createCell(row, columnCount++,column, style);
           }
       }
    }
    public void copm(){
        writeHeaderLine();
        for(String key: this.merge.keySet()) {
            addCoulmn(key,this.merge.get(key));

        }
        addQueu();


    }

    public void export(HttpServletResponse response) throws IOException {
        copm();
        writeDataLines();


        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }


}

