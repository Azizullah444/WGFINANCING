/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.unionbank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForUnionBank {
    private List<String> lines;
    private List<String> CheckBlockDate;
    private List<Double> CheckBlockAmount;
    private List<String> DebitBlockDate;
    private List<Double> DebitBlockAmount;
    private List<String> CreditBlockDate;
    private List<Double> CreditBlockAmount;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;                                

    DailyLedgerBalanceForUnionBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        CaptureChecksBlock();
        CaptureDebitBlock();
        CaptureCreditBlock();
        CalculateDailyLedgerBalance();
        this.noOfNegativeValues = findNoOfNegativeValues();
        this.minValue = findMinValue();

    }
    private void CalculateDailyLedgerBalance(){
            List<String> TotalDate= new ArrayList<>();
            List<Double> TotalAmount=new ArrayList<>();
            String DaTe=captureDate();
            String DaT=FileUtils.getResultFromPattern("\\d{2}",DaTe);
            if (DaT.startsWith("NoMatch")) {
               DaT="02";
            } if(FileUtils.convertStringToDouble(DaT)<10){DaT=DaT.substring(1, 2);}
            for(int i=1;i<32;i++)
            {
             TotalDate.add(DaT+"/"+i);
            }double Smm=0;
            List<String> CheckDate= new ArrayList<>();
            List<Double> CheckAmount=new ArrayList<>();
            for (int C = 0; C < CheckBlockDate.size(); C++) 
              {     Smm=Smm+CheckBlockAmount.get(C);
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
              { 
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
                TotalAmount.add(Amount);
            }
           this.selectedBlock=TotalAmount;       
    }
    private double captureTotalPrevious() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("Balance on")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[temp.length-1]);
            d = FileUtils.convertStringToDouble(dd);
       }
       return d;
    }
    private String captureDate() {
         String dd = "";
        for (int a = 0; a < lines.size(); a++) {
            String d = FileUtils.getResultFromPattern("-\\s+\\d{2}/\\d{2}/\\d{2}", this.lines.get(a));
            if (!d.startsWith("NoMatch")) {
                String[] tmp = d.split(" ");
                dd = tmp[tmp.length-1];
            }
        }
        return dd;
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
        String firstStart="^\\s*Checks";
        String firstEnd="Number\\s+Date\\s+Reference\\s+Amount";
        String end = "^\\s*Total";
        int startIndex = FileUtils.findStartIndex(lines, firstStart, firstEnd);
        int endIndex=FileUtils.findIndexBySingleValue(lines, startIndex, end);
        if (startIndex==0){ endIndex=0; }
        List<String> CheckDate = new ArrayList<>();
        List<Double> CheckAmount = new ArrayList<>();
        List<String> temp = new ArrayList<>(); 
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\d+", s);
            if (!result.startsWith("NoMatch")) {
                temp.add(s);
            }

        }
         for (String s : temp) { 
            String[] result = s.split("\\s+");
            for (int a = 0; a < result.length; a++) {
                if (result[a].matches("\\d{1,2}/\\d{1,2}")) { String value="";
                    if(result[a+2].equals("$")){ value = FileUtils.getDollarSignRemovedValue(result[a+3]);}else
                    { value = FileUtils.getDollarSignRemovedValue(result[a+2]);}
                    CheckDate.add(result[a]); 
                    double d = FileUtils.convertStringToDouble(value);
                    CheckAmount.add(d);
                }
            }
        }
         this.CheckBlockDate=CheckDate;
         this.CheckBlockAmount=CheckAmount;
        
    }
private void CaptureDebitBlock() {
        String firstStart="^\\s*Payments\\s+online\\s+and\\s+electronic\\s+banking";
        String firstEnd="Date\\s+Description/Location\\s+Reference\\s+Amount";
        String end = "^\\s*About\\s+Your\\s+Monthly\\s+Service\\s+Charge|^\\s*Information\\s+and\\s+Banking\\s+";
        int startIndex = FileUtils.findStartIndex(lines, firstStart, firstEnd);
        int endIndex=FileUtils.findIndexBySingleValue(lines, startIndex, end);
        if (endIndex==0){endIndex=lines.size();}
        List<String> DebitDate = new ArrayList<>();
        List<Double> DebitAmount = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}\\s+", s);
            if (!result.startsWith("NoMatch")) {
                temp.add(s);
            }
        }
         for (String s : temp) {
            String[] result = s.split("\\s+");
            DebitDate.add(result[1]);
            String value = FileUtils.getDollarSignRemovedValue(result[result.length-1]);
            double d = FileUtils.convertStringToDouble(value);
            DebitAmount.add(d);    
        }
         this.DebitBlockDate=DebitDate;
         this.DebitBlockAmount=DebitAmount;   
    }
private void CaptureCreditBlock() {
        String firstStart="^\\s*Additions";
        String firstEnd="Date\\s+Description/Location\\s+Reference\\s+Amount";
        String end = "^\\s*Total";
        int startIndex = FileUtils.findStartIndex(lines, firstStart, firstEnd);
        int endIndex=FileUtils.findIndexBySingleValue(lines, startIndex, end);
        List<String> CreditDate = new ArrayList<>();
        List<Double> CreditAmount = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}\\s+", s);
            if (!result.startsWith("NoMatch")) {
                temp.add(s);
            }
        }
         for (String s : temp) {
            String[] result = s.split("\\s+");
            CreditDate.add(result[0]);
            String value = FileUtils.getDollarSignRemovedValue(result[result.length-1]);
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

