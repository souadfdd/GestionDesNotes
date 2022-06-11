package com.gsnotes.utils.export;




import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelExporter3 {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private ArrayList<ArrayList<String>> columnNames;
    private ArrayList<ArrayList<Object>> data;
    private String sheetName = "";
    //hashMap  des modules avec nombres des elements necessaire pour faire le merge
    private HashMap<String,Integer> merge;
    //attribut pour stocker le numero de row apres creation de header
    private int DataRow;

    /**
     *
     * @param columnNames
     * @param data
     * @param sheetName
     * @param merge     hashMap contient les modules avec nombres des elements necessaire pour faire le merge      C
     */
    public ExcelExporter3(ArrayList<ArrayList<String>> columnNames, ArrayList<ArrayList<Object>> data, String sheetName, HashMap<String,Integer> merge) {
        this.columnNames = columnNames;
        System.out.println(columnNames);
        this.data = data;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
        this.merge=merge;

    }

    /**
     * Creation de head
     */

    public void writeHeaderLine() {
        sheet = workbook.createSheet(sheetName);

        for(int j=0;j<columnNames.size();j++) {
            System.out.println(columnNames.get(j));
            Row row = sheet.createRow(j);

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(14);
            style.setFont(font);

            int i = 0;
            for (String it : columnNames.get(j)) {
                createCell(row, (i++), it, style);
            }


        }
    }


    /**
     *
     * @param row
     * @param columnCount
     * @param value
     * @param style
     */

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

    /**
     * permet d'ajouter un cell avec une merge
     * @param mods le nom de model
     * @param el le nombre de sous cell dans le model
     */
    public void addCoulmn(String mods,int el){
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(1);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        //recuper  le numero le dernier cell
        int i = row.getLastCellNum();
        System.out.println(i);
        createCell(row, (i),mods, style);
        //permet ajouter une merge
        sheet.addMergedRegion(new CellRangeAddress(1, 1,i,i+el-1));
        //creation de cell pour l'autre module
        createCell(row, (i+el-1),mods, style);
    }

    /**
     * permet de creation de formulaire pour calculer la moyenne de module
     * @param row
     * @return
     */
    private String createFormula(int row ){
        String moyenneFormula = "";
        //indice de premier column  de ecrire la formule pour calculer la moyenne (G6+I6+K6+M6)/4
        char colName = 'D';
        int size = merge.size();
        for(String key: merge.keySet()) {
            colName+=merge.get(key)-1;
            moyenneFormula += (colName) + "" +(row) ;
            if ((--size) > 0) {
                moyenneFormula += "+";
            }
            colName++;
        }
        System.out.println( "("+moyenneFormula+")/"+ merge.size());
        return "("+moyenneFormula+")/"+ merge.size();

    }


    /**
     * Ajouter le formulaire pour chaque ligne
     */
    void addformulaMoyen(){
       sheet = workbook.getSheet(sheetName);
       for(int i=DataRow;i<sheet.getLastRowNum()+1;i++) {
           Row row = sheet.getRow(i);
           int lastColumn = row.getLastCellNum();
           row.createCell(lastColumn).setCellFormula(createFormula(i+1));
           System.out.println("last cell" + row.getLastCellNum());
       }
    }

    /**
     * ajout de queu de fichier excel
     */
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


    /**
     * insere les donneés dans le fichier excel
     */
    private void writeDataLines() {
        System.out.println("tableau"+data);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum()+1;
        //ajouter la valeur rowCount a l'attribut DataRow pour avoir la lighne dans laquelle ona commence a insere les donnees
        this.DataRow=rowCount;
        System.out.println("rowCountaaaa"+rowCount);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for(int j=0;j<data.size();j++) {
            Row row = sheet.createRow(rowCount);
            int columnCount = 0;
            for(Object column:data.get(j)){
                createCell(row, columnCount++,column, style);
            }
            System.out.println("rowColumn"+rowCount);
            rowCount++;
        }
    }

    /**
     * comp cette fonction permet la creation de header ansi que l'ajout de merge et le queu
     */
    public void copm(){
        writeHeaderLine();
        for(String key: this.merge.keySet()) {
            addCoulmn(key,this.merge.get(key));

        }
        addQueu();



    }

    /**
     * permet ajout de formulaire de rang pour chaque ligne
     */
    public void sort() {
        sheet = workbook.getSheet(sheetName);
        //parcours de ligne qui contient le données
        for (int i =DataRow ; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);

            int lastColumn = row.getLastCellNum();
            Cell cell=row.createCell(lastColumn);
            System.out.println(lastColumn);
            String beforeLast=sheet.getRow(i).getCell(lastColumn-1).getReference();
            String first=sheet.getRow(sheet.getFirstRowNum()+DataRow).getCell(lastColumn-1).getReference();
          //  String formula="RANG("+beforeLast+",$"+beforeLast.charAt(0)+"$"+beforeLast.charAt(1)+":$"+beforeLast.charAt(0)+"$"+(sheet.getLastRowNum()+1)+")";
            String formula="RANG("+beforeLast+",$"+first.charAt(0)+"$"+first.charAt(1)+":$"+beforeLast.charAt(0)+"$"+(sheet.getLastRowNum()+1)+")";

            cell.setCellFormula(formula);
            System.out.println(formula);
            System.out.println("first"+first);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        copm();
        writeDataLines();
        addformulaMoyen();
        sort();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }


}

