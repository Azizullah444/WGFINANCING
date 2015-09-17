/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.capitalonebank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForCapitalOneBank{

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private double averageLedgerBalance;
    private double minValue;

    CredentialsForCapitalOneBank(String[] page,List<String> lines) {
        this.pages = page;
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.companyName = "CapitalOne";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        this.minValue = findMinValue();
        
    }

      private double findMinValue(){
        double d = 0;
        for(String s: lines){
            if(s.contains("Minimum Balance This Cycle")){
                String[] temp = s.split(" ");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(FileUtils.getBracketRemovedValue(temp[temp.length-1])));
                break;
            }
        }
        return d;
    }
     private double captureTotalDeposits() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("Deposits/Credits")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split("\\s+"); 
            d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[3]));
       }
       return d;
    }

     private String captureDate() {
        String dd = "";
        for (int a = 0; a < lines.size(); a++) {
            String d = FileUtils.getResultFromPattern("-\\s+\\D+\\s+\\d{1,2},\\s+\\d{4}", this.lines.get(a));
            if (!d.startsWith("NoMatch")) {
                String[] tmp = d.split("\\s+");
                dd = tmp[1];
            }
        }
        return dd;
    }
      private String captureAccountNumber() {
        String result = FileUtils.getTargetByMatch(pages[0], "Spark\\s+Cashback\\s+Checking\\s+\\d+|Rewards\\s+\\d+");
        if (!result.startsWith("NoMatch")){
        return FileUtils.getFourDigitAccountNumber(result);}
        return "Put Manually";
    }
   
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalDeposits() {
        return totalDeposits;
    }

    public void setTotalDeposits(double totalDeposits) {
        this.totalDeposits = totalDeposits;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String[] getPages() {
        return pages;
    }

    public void setPages(String[] pages) {
        this.pages = pages;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

}
