/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.jsf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import tan.jam.company.Company;
import tan.jam.company.model.jsf.AdminController;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class OrignalFileDownloadBean {

    private List<Company> companies;
    @ManagedProperty(value = "#{orignalFileUploadBean}")
    private OrignalFileUploadBean orignalFileUploadBean;
    private String result;
    private StreamedContent file;
    @ManagedProperty(value = "#{adminController}")
    private AdminController adminController;

    public OrignalFileDownloadBean() {
        companies = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        this.companies = orignalFileUploadBean.getCompanies();
    }

    public String handleClick() {

        orignalFileUploadBean.endSession();
        return "orignal?faces-redirect=true";
    }

    public String handleLogout() {

        orignalFileUploadBean.endSession();
        adminController.endSession();

        return "login?faces-redirect=true";
    }

    public void downloadHandler(ActionEvent e) {

        System.out.println("download click handler");
        try {
            InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/demo/file/Table.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(OPCPackage.open(stream));
            XSSFSheet sheet = wb.getSheetAt(0);
            wb.setForceFormulaRecalculation(true);

            /* 
             */
            
            String name;
            String acc ;
            HashSet OnlineTransferToAccounts=new HashSet();
            int RowForOnlineTransferT0=105;
            int RowForRecurringDailyPayment=105;
            List<String> LenderDescription = new ArrayList<>();
            List<String> LenderDate= new ArrayList<>();
            List<Double> LenderAmount=new ArrayList<>();
            List<Double> UniqueDailypaymentkeywordAmount= new ArrayList<>();
            List<String> UniqueDailypaymentkeywordDate= new ArrayList<>();
            List<String> UniqueDailypaymentkeywordDescription= new ArrayList<>();
            
            for (int a = 0; a < companies.size(); a++) {
                Company c = companies.get(a);
                 name=c.getCompanyName();
                 acc=c.getAccountNumber();
                 String month = c.getDate().toLowerCase();
                 int R=3;
              if (month.startsWith(("jan")))
              {
                  R=3;
              }else if(month.startsWith("feb"))
              {
                  R=4;
              }else if(month.startsWith("mar"))
              {
                  R=5;
              }else if(month.startsWith("apr"))
              {
                  R=6;
              }else if(month.startsWith("may"))
              {
                  R=7;
              }else if(month.startsWith("jun"))
              {
                  R=8;
              }else if(month.startsWith("jul"))
              {
                  R=9;
              }else if(month.startsWith("aug"))
              {
                  R=10;
              }else if(month.startsWith("sep"))
              {
                  R=11;
              }else if(month.startsWith("oct"))
              {
                  R=12;
              }else if(month.startsWith("nov"))
              {
                  R=13;
              }else if(month.startsWith("dec"))
              {
                  R=14;
              }else if(month.startsWith("02")|month.startsWith("2"))
              {
                  R=4;
              }else if(month.startsWith("03")|month.startsWith("3"))
              {
                  R=5;
              }else if(month.startsWith("04")|month.startsWith("4"))
              {
                  R=6;
              }else if(month.startsWith("05")|month.startsWith("5"))
              {
                  R=7;
              }else if(month.startsWith("06")|month.startsWith("6"))
              {
                  R=8;
              }else if(month.startsWith("07")|month.startsWith("7"))
              {
                  R=9;
              }else if(month.startsWith("08")|month.startsWith("8"))
              {
                  R=10;
              }else if(month.startsWith("09")|month.startsWith("9"))
              {
                  R=11;
              }else if(month.startsWith("10")|month.startsWith("10"))
              {
                  R=12;
              }else if(month.toUpperCase().startsWith("11"))
              {
                  R=13;
              }else if(month.toUpperCase().startsWith("12"))
              { 
                  R=14;
              } 
               ////%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%////
              
              sheet.getRow(1).getCell(1).setCellValue("Bank Account**"+acc);
              if (a==0)
              { 
                  sheet.getRow(1).getCell(7).setCellValue(name+"**"+acc);
                  sheet.getRow(1).getCell(12).setCellValue(name+"**"+acc); 
                  sheet.getRow(R).getCell(7).setCellFormula("sum("+c.getTotalDeposits()+"-"+c.getSumOfReversalFrom()+"-"+c.getSumOfOnlineTransferFrom()+"-"+ c.getKeywordSum()+")");
                  // int Mov=Shifting.FindShift(wb, sheet); 
                 sheet.getRow(R).getCell(12).setCellValue(c.getAverageLedgerBalance());
                 sheet.getRow(R).getCell(13).setCellValue(c.getMinValue());
                 sheet.getRow(R).getCell(14).setCellValue(c.getNoOfNegativeValues());
                 // int Onl=Shifting.Online(wb, sheet);
              
              }else
              {      int Decision=0;
                     String Name=name+"**"+acc;
                  for(int s=0;s<Shifting.FindShift(wb, sheet)+1;s++)
                  {    
                     if(sheet.getRow(1).getCell(7+s).getStringCellValue().equals(Name))
                     {  Decision=1;
                        sheet.getRow(R).getCell(7+s).setCellFormula("sum("+c.getTotalDeposits()+"-"+c.getSumOfReversalFrom()+"-"+c.getSumOfOnlineTransferFrom()+"-"+ c.getKeywordSum()+")");
                     }
                  } 
                  if(Decision==0)
                  {   int Mov=Shifting.FindShift(wb, sheet);
                      Shifting.InsertColumn(wb, sheet);
                      sheet.getRow(1).getCell(8+Mov).setCellValue(name+"**"+acc);
                      sheet.getRow(R).getCell(8+Mov).setCellFormula("sum("+c.getTotalDeposits()+"-"+c.getSumOfReversalFrom()+"-"+c.getSumOfOnlineTransferFrom()+"-"+ c.getKeywordSum()+")");
                  }
                  int selectcolumn=Shifting.FindShift(wb, sheet)+12;
                  if(sheet.getRow(1).getCell(selectcolumn).getCellType()==3||sheet.getRow(1).getCell(selectcolumn).getStringCellValue().equals(Name))
                  {   
                      
                  }else if(sheet.getRow(1).getCell(selectcolumn+6).getCellType()==3||sheet.getRow(1).getCell(selectcolumn+6).getStringCellValue().isEmpty()||sheet.getRow(1).getCell(selectcolumn+6).getStringCellValue().equals(Name))
                  {    
                      selectcolumn=selectcolumn+6;
                  }else if(sheet.getRow(1).getCell(selectcolumn+12).getCellType()==3||sheet.getRow(1).getCell(selectcolumn+12).getStringCellValue().isEmpty()||sheet.getRow(1).getCell(selectcolumn+12).getStringCellValue().equals(Name))
                  {    
                      selectcolumn=selectcolumn+12;
                  }else if(sheet.getRow(1).getCell(selectcolumn+18).getCellType()==3||sheet.getRow(1).getCell(selectcolumn+18).getStringCellValue().isEmpty()||sheet.getRow(1).getCell(selectcolumn+18).getStringCellValue().equals(Name))
                  {    
                      selectcolumn=selectcolumn+18;
                  }else if(sheet.getRow(1).getCell(selectcolumn+24).getCellType()==3||sheet.getRow(1).getCell(selectcolumn+24).getStringCellValue().isEmpty()||sheet.getRow(1).getCell(selectcolumn+24).getStringCellValue().equals(Name))
                  {  
                      selectcolumn=selectcolumn+24;
                  }else if(sheet.getRow(1).getCell(selectcolumn+30).getCellType()==3||sheet.getRow(1).getCell(selectcolumn+30).getStringCellValue().isEmpty()||sheet.getRow(1).getCell(selectcolumn+30).getStringCellValue().equals(Name))
                  {  
                      selectcolumn=selectcolumn+30;
                  }else if(sheet.getRow(1).getCell(selectcolumn+36).getCellType()==3||sheet.getRow(1).getCell(selectcolumn+36).getStringCellValue().isEmpty()||sheet.getRow(1).getCell(selectcolumn+36).getStringCellValue().equals(Name))
                  {  
                      selectcolumn=selectcolumn+36;
                  }else if(sheet.getRow(1).getCell(selectcolumn+42).getCellType()==3||sheet.getRow(1).getCell(selectcolumn+42).getStringCellValue().isEmpty()||sheet.getRow(1).getCell(selectcolumn+42).getStringCellValue().equals(Name))
                  {  
                      selectcolumn=selectcolumn+42;
                  }                        

                      sheet.getRow(1).getCell(selectcolumn).setCellValue(name+"**"+acc);
                      sheet.getRow(R).getCell(selectcolumn).setCellValue(c.getAverageLedgerBalance());
                      sheet.getRow(R).getCell(selectcolumn+1).setCellValue(c.getMinValue());
                      sheet.getRow(R).getCell(selectcolumn+2).setCellValue(c.getNoOfNegativeValues()); 
              }
              /*
             // int Mov=Shifting.FindShift(wb, sheet);
              sheet.getRow(R).getCell(12).setCellValue(c.getAverageLedgerBalance());
              sheet.getRow(R).getCell(13).setCellValue(c.getMinValue());
              sheet.getRow(R).getCell(14).setCellValue(c.getNoOfNegativeValues());
             // int Onl=Shifting.Online(wb, sheet); */
              
              ///%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%      %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/////
              for (int b = 0; b < c.getOnlineTransferToDate().size(); b++)
              {
                  sheet.getRow(RowForOnlineTransferT0).getCell(9).setCellValue(c.getOnlineTransferToDate().get(b));
                  sheet.getRow(RowForOnlineTransferT0).getCell(10).setCellValue(c.getOnlineTransferToDescription().get(b));
                  sheet.getRow(RowForOnlineTransferT0).getCell(12).setCellValue(c.getOnlineTransferToAmount().get(b));
                  RowForOnlineTransferT0++;
              }
              for (int L = 0; L < c.getKeywordAmount().size(); L++) 
              {
                  int index=0;
                  if(LenderDescription.contains(c.getKeywordDescription().get(L)))
                  {
                      index=LenderDescription.indexOf(c.getKeywordDescription().get(L));
                      LenderDate.set(index, c.getKeywordDate().get(L));
                      LenderAmount.set(index, c.getKeywordAmount().get(L));
                  }else
                  {
                      LenderDescription.add(c.getKeywordDescription().get(L));
                      LenderDate.add(c.getKeywordDate().get(L));
                      LenderAmount.add(c.getKeywordAmount().get(L));
                  }

              } 
              for (int TT = 0; TT< c.getOnlineTransferToCheckNoDuplicateRemoved().size(); TT++) {
                  OnlineTransferToAccounts.add(c.getOnlineTransferToCheckNoDuplicateRemoved().get(TT));
                }
              if(!name.equals("BlackHills")){  
              for(int U=0;U<c.getDailypaymentKeywordDescription().size();U++)
              {   sheet.getRow(RowForRecurringDailyPayment).getCell(14).setCellValue(c.getDailypaymentKeywordDate().get(U));
                  sheet.getRow(RowForRecurringDailyPayment).getCell(15).setCellValue(c.getDailypaymentKeywordDescription().get(U));
                  sheet.getRow(RowForRecurringDailyPayment).getCell(17).setCellValue(c.getDailypaymentKeywordAmount().get(U));
                  RowForRecurringDailyPayment++; 
                  
                  if(!UniqueDailypaymentkeywordDescription.contains(c.getDailypaymentKeywordDescription().get(U)))
                  {
                      UniqueDailypaymentkeywordAmount.add(c.getDailypaymentKeywordAmount().get(U));
                      UniqueDailypaymentkeywordDate.add(c.getDailypaymentKeywordDate().get(U));
                      UniqueDailypaymentkeywordDescription.add(c.getDailypaymentKeywordDescription().get(U));
                  }
              }
              }
              
            }
            for(int LL=0;LL<LenderDescription.size();LL++)
                if(25+LL<31)
                {
            {   // if(25+LL > 29)
            {
                //Shifting.InsertRow(wb, sheet,25+LL,26+LL);
            }
                 sheet.getRow(LL+25).getCell(1).setCellValue(LenderDate.get(LL));
                 sheet.getRow(LL+25).getCell(2).setCellValue(LenderDescription.get(LL));
                 sheet.getRow(LL+25).getCell(5).setCellValue(LenderAmount.get(LL));
            }
                }
            List<String> OnlineTransferToUniqueAccounts = new ArrayList<>(OnlineTransferToAccounts);
            for (int TT = 0; TT< OnlineTransferToUniqueAccounts.size(); TT++) {
                  int Mov=Shifting.FindShift(wb, sheet);
                  Shifting.InsertColumn(wb, sheet);
                  sheet.getRow(1).getCell(8+Mov).setCellValue("**"+OnlineTransferToUniqueAccounts.get(TT));
            }
            //**************************  Daily Payment portion  ******************************************//
               
             for (int D=0;D<UniqueDailypaymentkeywordDescription.size();D++)
             {
             for (int r=25;r<31;r++)
             {  
                 if(sheet.getRow(r).getCell(2).getCellType()==3)
                 {
                     sheet.getRow(r).getCell(1).setCellValue(UniqueDailypaymentkeywordDate.get(D));
                     sheet.getRow(r).getCell(2).setCellValue(UniqueDailypaymentkeywordDescription.get(D));
                     sheet.getRow(r).getCell(9).setCellValue(UniqueDailypaymentkeywordAmount.get(D));
                     sheet.getRow(r).getCell(0).setCellValue("OPEN");
                     break;
                 }else if(sheet.getRow(r).getCell(2).getStringCellValue().equals(UniqueDailypaymentkeywordDescription.get(D)))
                 {
                     sheet.getRow(r).getCell(9).setCellValue(UniqueDailypaymentkeywordAmount.get(D));
                     sheet.getRow(r).getCell(0).setCellValue("OPEN");
                     break;
                 }
                 
             }
             }
              
               
            Shifting.InsertFormulas(wb, sheet);
            Shifting.MergeCells(wb, sheet);
            // Write output to a file
            
            FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
            wb.write(fileOut);
            fileOut.close();
            InputStream inp = new FileInputStream("workbook.xlsx");
            file = new DefaultStreamedContent(inp, "application/vnd.ms-excel", "Table_downloaded.xlsx");
        } catch (IOException ex) {
            Logger.getLogger(OrignalFileDownloadBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(OrignalFileDownloadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public OrignalFileUploadBean getOrignalFileUploadBean() {
        return orignalFileUploadBean;
    }

    public void setOrignalFileUploadBean(OrignalFileUploadBean orignalFileUploadBean) {
        this.orignalFileUploadBean = orignalFileUploadBean;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

}
