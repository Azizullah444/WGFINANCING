/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofthewest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForBankOfTheWest {
    private List<String> lines;
    private List<String> CheckBlockDate;
    private List<Double> CheckBlockAmount;
    private List<String> DepositBlockDate;
    private List<Double> DepositBlockAmount;
    private List<String> DebitBlockDate;
    private List<Double> DebitBlockAmount;
    private List<String> CreditBlockDate;
    private List<Double> CreditBlockAmount;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;                                

    DailyLedgerBalanceForBankOfTheWest(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        CaptureChecksBlock();
        CaptureDepositBlock();
        CaptureDebitBlock();
        CaptureCreditBlock();
        CalculateDailyLedgerBalance();
        this.noOfNegativeValues = findNoOfNegativeValues();
        this.minValue = findMinValue();

    }
    private void CalculateDailyLedgerBalance(){
            List<String> TotalDate= new ArrayList<>();
            List<Double> TotalAmount=new ArrayList<>();
            String DaT=captureDate();
            if (DaT.startsWith("NoMatch")) {
               DaT="02";
            }
            for(int i=1;i<32;i++)
            {
             TotalDate.add(DaT+"/"+String.format("%02d", i));
            }
            List<String> CheckDate= new ArrayList<>();
            List<Double> CheckAmount=new ArrayList<>();
            for (int C = 0; C < CheckBlockDate.size(); C++) 
              {     
                  int index=0;
                  
                  if(CheckDate.contains(CheckBlockDate.get(C)))
                  {  
                      index=CheckDate.indexOf(CheckBlockDate.get(C));
                      double total=CheckAmount.get(index)+ CheckBlockAmount.get(C);
                      CheckAmount.set(index, total);
                  }else
                  {
                      CheckDate.add(CheckBlockDate.get(C));
                      CheckAmount.add(CheckBlockAmount.get(C));
                  }                     
              }
            List<String> DepositDate= new ArrayList<>();
            List<Double> DepositAmount=new ArrayList<>();
            for (int C = 0; C < DepositBlockDate.size(); C++) 
              {     
                  int index=0;
                  
                  if(DepositDate.contains(DepositBlockDate.get(C)))
                  {  
                      index=DepositDate.indexOf(DepositBlockDate.get(C));
                      double total=DepositAmount.get(index)+ DepositBlockAmount.get(C);
                      DepositAmount.set(index, total);
                  }else
                  {
                      DepositDate.add(DepositBlockDate.get(C));
                      DepositAmount.add(DepositBlockAmount.get(C));
                  }                     
              }
            List<String> DebitDate= new ArrayList<>();
            List<Double> DebitAmount=new ArrayList<>();
            for (int C = 0; C < DebitBlockDate.size(); C++) 
              {  
                  int index=0;
                  if(DebitDate.contains(DebitBlockDate.get(C)))
                  {
                      index=DebitDate.indexOf(DebitBlockDate.get(C));
                      double total=DebitAmount.get(index)+ DebitBlockAmount.get(C);
                      DebitAmount.set(index, total);
                  }else
                  {
                      DebitDate.add(DebitBlockDate.get(C));
                      DebitAmount.add(DebitBlockAmount.get(C));
                  }

              }
            List<String> CreditDate= new ArrayList<>();
            List<Double> CreditAmount=new ArrayList<>();
            for (int C = 0; C < CreditBlockDate.size(); C++) 
              {  System.out.println("Credit "+CreditBlockDate.get(C));
                  int index=0;
                  if(CreditDate.contains(CreditBlockDate.get(C)))
                  {
                      index=CreditDate.indexOf(CreditBlockDate.get(C));
                      double total=CreditAmount.get(index)+ CreditBlockAmount.get(C);
                      CreditAmount.set(index, total);
                  }else
                  {
                      CreditDate.add(CreditBlockDate.get(C));
                      CreditAmount.add(CreditBlockAmount.get(C));
                  }

              }
           
            int index=0;
            double Amount=captureTotalPrevious();
            for(int i=0;i<TotalDate.size();i++)
            {
                if(CheckDate.contains(TotalDate.get(i)))
                {
                    index=CheckDate.indexOf(TotalDate.get(i));
                    Amount=Amount-CheckAmount.get(index);
                }
                if(DepositDate.contains(TotalDate.get(i)))
                {
                    index=DepositDate.indexOf(TotalDate.get(i));
                    Amount=Amount+DepositAmount.get(index);
                }
                if(DebitDate.contains(TotalDate.get(i)))
                {
                    index=DebitDate.indexOf(TotalDate.get(i));
                    Amount=Amount-DebitAmount.get(index);
                }
                if(CreditDate.contains(TotalDate.get(i)))
                {
                    index=CreditDate.indexOf(TotalDate.get(i));
                    Amount=Amount+CreditAmount.get(index);
                }
                System.out.println("Date "+TotalDate.get(i));
                System.out.println("Amount "+Amount);
                TotalAmount.add(Amount);
            }
           this.selectedBlock=TotalAmount;       
    }
    private double captureTotalPrevious() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("Beginning Balance")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[2]);
            d = FileUtils.convertStringToDouble(dd);
       }
       return d;
    }
    private String captureDate() {
         String dd = "z";
        for (int a = 0; a < lines.size(); a++) {
            String d = FileUtils.getResultFromPattern("-\\s+\\D+\\s+\\d{1,}", this.lines.get(a));
            if (!d.startsWith("NoMatch")) {
                String[] tmp = d.split("\\s+");
                dd = tmp[1];
            }
        }
        String M = dd;
        String month="02";
         switch (M) {
		case "January": month = "01"; break;
		case "February": month = "02"; break;
		case "March": month = "03"; break;
		case "April": month = "04"; break;
		case "May": month = "05"; break;
		case "June": month = "06"; break;
		case "July": month = "07"; break;
		case "August": month = "08"; break;
		case "September": month = "09"; break;
		case "October": month = "10"; break;
		case "November": month = "11"; break;
		case "December": month = "12"; break;
 
		}
        return month;
    }

    private double findMinValue() {
        return Collections.min(this.selectedBlock);
    }

    private int findNoOfNegativeValues() {
        int count = 0;
        for (double d : this.selectedBlock) {
            if (d < 0) {
                ++count;
            }
        }
        return count;
    }

private void CaptureChecksBlock() {
        String start = "Checks Paid";
        String end = "\\d+\\s+checks\\s+paid\\s+for\\s+a\\s+total\\s+of";
        List<String> CheckDate = new ArrayList<>();
        List<Double> CheckAmount = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\d+", s);
            if (!result.startsWith("NoMatch")) {
                temp.add(s);
            }

        }
         for (String s : temp) {
            String[] result = s.split(" ");
            for (int a = 0; a < result.length; a++) {
                if (result[a].matches("\\d{2}/\\d{2}")) {
                    CheckDate.add(result[a]);
                    String value = FileUtils.getDollarSignRemovedValue(result[a+1]);
                    double d = FileUtils.convertStringToDouble(value);
                    CheckAmount.add(d);
                }
            }
        }
         this.CheckBlockDate=CheckDate;
         this.CheckBlockAmount=CheckAmount;
        
    }
        private void CaptureDepositBlock() {
        
          int StartIndex = FileUtils.findStartIndex(lines,"^\\s+Deposits", "Date\\s+Amount\\s+Date\\s+Amount\\s+Date\\s+Amount");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"\\d+\\s+deposits\\s+for\\s+a\\s+total\\s+of");
        List<String> DepositDate = new ArrayList<>();
        List<Double> DepositAmount = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", s);
            if (!result.startsWith("NoMatch")) {
                temp.add(s);
            }

        }
         for (String s : temp) {
            String[] result = s.split(" ");
            for (int a = 0; a < result.length; a++) {
                if (result[a].matches("\\d{2}/\\d{2}")) {
                    DepositDate.add(result[a]);
                    String value = FileUtils.getDollarSignRemovedValue(result[a+1]);
                    double d = FileUtils.convertStringToDouble(value);
                    DepositAmount.add(d);
                }
            }
        }
         this.DepositBlockDate=DepositDate;
         this.DepositBlockAmount=DepositAmount;
        
    }

private void CaptureDebitBlock() {
          
          int StartIndex = FileUtils.findStartIndex(lines, "^\\s*Withdrawals", "Date\\s+Amount\\s+Description");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"\\d+\\s+withdrawals\\s+for\\s+a\\s+total\\s+of");
        List<String> DebitDate = new ArrayList<>();
        List<Double> DebitAmount = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", s);
            if (!result.startsWith("NoMatch")) { 
                temp.add(s);
            }
        }
         for (String s : temp) {
            String[] result = s.split("\\s+");
            DebitDate.add(result[0]);
            String value = FileUtils.getDollarSignRemovedValue(result[1]);
            double d = FileUtils.convertStringToDouble(value);
            DebitAmount.add(d);    
        }
         this.DebitBlockDate=DebitDate;
         this.DebitBlockAmount=DebitAmount;   
    }
private void CaptureCreditBlock() {
          
          int StartIndex = FileUtils.findStartIndex(lines, "^\\s*Credits", "Date\\s+Amount\\s+Description");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"\\d+\\s+credits\\s+for\\s+a\\s+total\\s+of");
        List<String> CreditDate = new ArrayList<>();
        List<Double> CreditAmount = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", s);
            if (!result.startsWith("NoMatch")) {
                temp.add(s);
            }
        }
         for (String s : temp) {
            String[] result = s.split("\\s+");
            CreditDate.add(result[0]);
            String value = FileUtils.getDollarSignRemovedValue(result[1]);
            double d = FileUtils.convertStringToDouble(value);
            CreditAmount.add(d);    
        }
         this.CreditBlockDate=CreditDate;
         this.CreditBlockAmount=CreditAmount;   
    }
    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public int getNoOfNegativeValues() {
        return noOfNegativeValues;
    }

    public void setNoOfNegativeValues(int noOfNegativeValues) {
        this.noOfNegativeValues = noOfNegativeValues;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public List<Double> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<Double> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }
    
     public List<String> getCheckBlockDate() {
        return CheckBlockDate;
    }

    public void setCheckBlockDate(List<String> CheckBlockDate) {
        this.CheckBlockDate = CheckBlockDate;
    }
     public List<Double> getCheckBlockAmount() {
        return CheckBlockAmount;
    }

    public void setCheckBlockAmount(List<Double> CheckBlockAmount) {
        this.CheckBlockAmount = CheckBlockAmount;
    }
     public List<String> getDepositBlockDate() {
        return DepositBlockDate;
    }

    public void setDepositBlockDate(List<String> DepositBlockDate) {
        this.DepositBlockDate = DepositBlockDate;
    }
     public List<Double> getDepositBlockAmount() {
        return DepositBlockAmount;
    }

    public void setDepositBlockAmount(List<Double> DepositBlockAmount) {
        this.DepositBlockAmount = DepositBlockAmount;
    }
    public List<String> getDebitBlockDate() {
        return DebitBlockDate;
    }

    public void setDebitBlockDate(List<String> DebitBlockDate) {
        this.DebitBlockDate = DebitBlockDate;
    }
     public List<Double> getDebitBlockAmount() {
        return DebitBlockAmount;
    }

    public void setDebitBlockAmount(List<Double> DebitBlockAmount) {
        this.DebitBlockAmount = DebitBlockAmount;
    }
     public List<String> getCreditBlockDate() {
        return CreditBlockDate;
    }

    public void setCreditBlockDate(List<String> CreditBlockDate) {
        this.CreditBlockDate = CreditBlockDate;
    }
     public List<Double> getCreditBlockAmount() {
        return CreditBlockAmount;
    }

    public void setCreditBlockAmount(List<Double> CreditBlockAmount) {
        this.CreditBlockAmount = CreditBlockAmount;
    }
    
}

