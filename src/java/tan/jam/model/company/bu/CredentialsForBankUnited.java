/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bu;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForBankUnited {

    private String[] pages;
    private List<String>  lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;

    public CredentialsForBankUnited(String[] pages) {
        this.pages = pages;
        this.lines=FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "Bank United";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        
    }

    private double captureTotalDeposits() {
      String result = "";
         double d = 0;
       for(String s: lines){
           if(s.matches(".+Deposits\\s+and\\s+Other\\s+Credit.+")){
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
        String result = FileUtils.getResultFromPattern("Statement Date:.+", pages[0]);
        if (!result.startsWith("NoMatch"))
        {
            String[] tmp = result.split("\\s+");
            result=tmp[2];
        }
        return result;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Account\\s+Number:\\s+\\*+\\d+", pages[0]);
        return FileUtils.getFourDigitAccountNumber(result);
    }

    public String[] getPages() {
        return pages;
    }

    public void setPages(String[] pages) {
        this.pages = pages;
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

}
