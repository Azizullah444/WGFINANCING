/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.academybank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForAcademyBank {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;

    public CredentialsForAcademyBank(String[] pages,List<String> lines) {
        this.pages = pages;
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.companyName = "AcademyBank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        
    }

    private double captureTotalDeposits() {
      String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("Total credits this period")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split("\\s+"); 
            d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[4]));
       }
       return d;
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("\\D+\\s+\\d{4}", pages[0]);
        if (!result.startsWith("NoMatch"))
        {
            String[] tmp = result.split("\\s+");
            result=tmp[tmp.length-2];
        }
        return result;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("XXXXX+\\d+", pages[0]);
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
