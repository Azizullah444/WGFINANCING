/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.blackhills;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForBlackHills{

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;

    CredentialsForBlackHills(String[] page,List<String> lines) {
        this.pages = page;
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.companyName = "BlackHills";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        
    }

     private double captureTotalDeposits() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("NEW BALANCE")){
               result = s;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getNegativeSignDetectedValue(temp[temp.length-1]);
            d = FileUtils.convertStringToDouble(dd);
       }
       return d;
    }

     private String captureDate() {
        String dd = "";
        for (int a = 0; a < lines.size(); a++) {
            String d = FileUtils.getResultFromPattern("\\d{2}-\\d{2}-\\d{2}", this.lines.get(a));
            if (!d.startsWith("NoMatch")) {
                dd=d;
            }
        }
        return dd;
    }
      private String captureAccountNumber() {
        String result = FileUtils.getTargetByMatch(pages[0], "\\d{8,}");
        return FileUtils.getFourDigitAccountNumber(result);
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

}
