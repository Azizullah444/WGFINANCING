/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.tdbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForTdBank {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> selectedBlock;

    public CredentialsForTdBank(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "TD Bank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }
    private double captureTotalDeposits() {
         String result = "";
         double d1=0,d2=0,d3=0;
       for(String s: lines){
           if(s.matches("\\s*Deposits.+")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[1]);
           d1 = FileUtils.convertStringToDouble(dd);
       }
       result = "";
       for(String s: lines){
           if(s.matches("^\\s*Electronic\\s+Deposits.+")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[2]);
           d2 = FileUtils.convertStringToDouble(dd);
       }
       result = "";
       for(String s: lines){
           if(s.matches("^\\s*Other\\s+Credits.+")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[2]);
           d3 = FileUtils.convertStringToDouble(dd);
       }
       return d1+d2+d3;
    }

    private String captureDate() {
        String dateResult="NoMatchFound";
        String result = FileUtils.getResultFromPattern("Statement\\s+Period:.+", pages[0]);
        if(!result.startsWith("NoMatch")){
            String[] temp = result.split("\\s+");
            String one = temp[temp.length-3];
            String two = temp[temp.length-2];
            String three = temp[temp.length-1];
            dateResult = one +" "+two+" "+three;
            dateResult = dateResult.substring(dateResult.indexOf('-')+1, dateResult.length()-1);
            
        }
        return dateResult;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("\\d{3}-\\d{6,}", pages[0]);
        return FileUtils.getFourDigitAccountNumber(result);
    }

    public String[] getPages() {
        return pages;
    }

    public void setPages(String[] pages) {
        this.pages = pages;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
}
