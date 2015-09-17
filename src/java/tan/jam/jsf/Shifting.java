/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.jsf;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Inam
 */
public class Shifting {
    public static int FindShift(XSSFWorkbook workbook, XSSFSheet worksheet)
    {   String s="TOTAL BANKS";
        XSSFRow Row=worksheet.getRow(2);
        for(int i=9;i<26;i++)
        {  
           XSSFCell cell=Row.getCell(i);
           String g=cell.getStringCellValue();
           if (g.equals("TOTAL BANKS"))
            {  
                return i-9;
            }
        }
        return 0;
    }
    public static int Online(XSSFWorkbook workbook, XSSFSheet worksheet)
    {
        for(int i=104;i<300;i++)
        {   XSSFCell c=worksheet.getRow(i).getCell(9);
            if(c== null || c.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK)
            {
                return i;
            }
        }
        return 104;
    }
    public static void InsertRow(XSSFWorkbook workbook, XSSFSheet worksheet, int sourceRowNum, int destinationRowNum) {
        
        worksheet.shiftRows(destinationRowNum,worksheet.getLastRowNum(), 1);
        XSSFRow newRow = worksheet.getRow(destinationRowNum);
        XSSFRow sourceRow = worksheet.getRow(sourceRowNum);

        if (newRow != null) {
            worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
        } else {
            newRow = worksheet.createRow(destinationRowNum);
        }

        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            // Grab a copy of the old/new cell
            XSSFCell oldCell = sourceRow.getCell(i);
            XSSFCell newCell = newRow.createCell(i);

            if (oldCell == null) {
                newCell = null;
                continue;
            }

            XSSFCellStyle newCellStyle = workbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            ;
            newCell.setCellStyle(newCellStyle);

            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }
            
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            newCell.setCellType(oldCell.getCellType());

            switch (oldCell.getCellType()) {
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula("+"+"F"+destinationRowNum+"*G"+destinationRowNum);
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
                    //newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
                    newCell.setCellValue("");
                    break;
            }
        }

        for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
            CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
            if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(newRow.getRowNum(),
                        (newRow.getRowNum() +
                                (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow()
                                        )),
                        cellRangeAddress.getFirstColumn(),
                        cellRangeAddress.getLastColumn());
                worksheet.addMergedRegion(newCellRangeAddress);
            }
        } int inc=destinationRowNum+1;
        worksheet.getRow(destinationRowNum).getCell(7).setCellFormula("+F"+inc+"*G"+inc);
    }
    public static void InsertColumn(XSSFWorkbook workbook, XSSFSheet worksheet)
    {   int Mov=FindShift(workbook, worksheet);
        for(int i=1;i<16;i++)
        {
            CopyData(workbook, worksheet, i,i,Mov);
        }
        char Ch='i';
        int Asc=Ch+Mov+1;
        worksheet.getRow(15).getCell(8+Mov+1).setCellFormula("AVERAGE("+(char)Asc+"4:"+(char)Asc+"15)");
    }
    private static void CopyData(XSSFWorkbook workbook, XSSFSheet worksheet, int sourceRowNum, int destinationRowNum,int Mov) {
        XSSFRow newRow = worksheet.getRow(destinationRowNum);
        XSSFRow sourceRow = worksheet.getRow(sourceRowNum);
        
        for (int i = sourceRow.getLastCellNum(); i > 8+Mov; i--) {
          
            int d=i-1;
            XSSFCell oldCell = sourceRow.getCell(d);
            XSSFCell newCell = newRow.createCell(i);

            if (oldCell == null) {
                newCell = null;
                continue;
            }

            XSSFCellStyle newCellStyle = workbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            ;
            newCell.setCellStyle(newCellStyle);

            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            newCell.setCellType(oldCell.getCellType());

            switch (oldCell.getCellType()) {
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
            }
        }
    }
    public static void InsertFormulas(XSSFWorkbook workbook, XSSFSheet worksheet)
    {   int Mov=FindShift(workbook, worksheet);
        char Chr='i';
        int Asci=Chr+Mov;
        for(int i=3;i<15;i++)
        {      int I=i+1; //System.out.println("Cell Type "+worksheet.getRow(i).getCell(7).getCellType());
               for (int s=7;s<=7+Mov;s++)
               {
               if(worksheet.getRow(i).getCell(s).getCellType()==2 )
               {  
               worksheet.getRow(i).getCell(9+Mov).setCellFormula("sum(H"+I+":"+(char)Asci+I+")");
               break;
              // System.out.println("sum(H"+I+":"+(char)Asci+I+")");
               }
               }
        }
        char ch='j';
        int C=ch+Mov;
        worksheet.getRow(15).getCell(9+Mov).setCellFormula("AVERAGE("+(char)C+"4:"+(char)C+"15)"); 
        worksheet.getRow(31).getCell(8).setCellFormula("+(J32*21+H24)/"+(char)C+"16");
    }
    public static void MergeCells(XSSFWorkbook workbook, XSSFSheet worksheet)
    { int Mov=FindShift(workbook, worksheet); 
      worksheet.addMergedRegion(new CellRangeAddress(
            1,1,12+Mov,13+Mov        
    ));
      worksheet.addMergedRegion(new CellRangeAddress(
            1,1,18+Mov,19+Mov
    ));   
      worksheet.addMergedRegion(new CellRangeAddress(
            1,1,24+Mov,25+Mov      
    ));
      worksheet.addMergedRegion(new CellRangeAddress(
            1,1,30+Mov,31+Mov      
    ));
      worksheet.addMergedRegion(new CellRangeAddress(
            1,1,36+Mov,37+Mov      
    ));
      worksheet.addMergedRegion(new CellRangeAddress(
            1,1,42+Mov,43+Mov      
    ));
      worksheet.addMergedRegion(new CellRangeAddress(
            1,1,48+Mov,49+Mov      
    ));
      worksheet.addMergedRegion(new CellRangeAddress(
            1,1,54+Mov,55+Mov      
    ));
    }
}
