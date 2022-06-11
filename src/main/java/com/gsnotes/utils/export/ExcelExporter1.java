package com.gsnotes.utils.export;



import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelExporter1 {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private ArrayList<ArrayList<String>> columnNames;
    private String[][] data;
    private String sheetName = "";


    public ExcelExporter1(ArrayList<ArrayList<String>> columnNames, String[][] data, String sheetName) {
        this.columnNames = columnNames;
        System.out.println(columnNames);
        this.data = data;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();

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
    private void mergeCell(int firstRow,int lastRow ,int firstCol, int lastCol){
        sheet.addMergedRegion(new CellRangeAddress(firstRow=0, lastRow=0, firstCol, lastCol));
    }

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
   public void addCoulmn(int columnCount, String columnName, ArrayList<String> elements){
            Row row = sheet.getRow(1);
            Row row1 = sheet.getRow(2);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);
            createCell(row,columnCount,columnName, style);
            sheet.addMergedRegion(new CellRangeAddress(1,1,columnCount,elements.size()));
            int i = 0;
            for (String it :elements) {
                createCell(row1, (i++), it, style);

            }
        }



  /*  private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            for (int j = 0; j < data[i].length; j++) {
                createCell(row, columnCount++, data[i][j], style);
            }
        }

    }*/

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
       sheet.addMergedRegion(new CellRangeAddress(1,1,4,7));

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}

