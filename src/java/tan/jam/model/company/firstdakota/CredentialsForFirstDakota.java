/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstdakota;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForFirstDakota {
    private String[] pages;
    private List<String> pageLines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;

    public CredentialsForFirstDakota(String[] pages) {
        this.pages = pages;
        this.pageLines = FileUtils.fillLines(pages);
        startExecution();
        
    }

    private void startExecution() {
        this.companyName = "FirstDakota";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }
    private double captureTotalDeposits(){
        String res = "";
        for(String s: pageLines){
            if(s.contains("Deposits/Credits")){
                res = s;
                break;
            }
        }
        String[] temp = res.trim().split("\\s+");
        return FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[2]));
    }
    private String captureAccountNumber(){
        return FileUtils.getFourDigitAccountNumber(FileUtils.getResultFromPattern("Account\\s+Number\\s+\\d+", pages[0]));
       
    }
    private String captureDate(){
        String res = FileUtils.getResultFromPattern("Date\\s+\\d{1,2}/\\d{1,2}/\\d{1,2}", pages[0]);
        String[] temp = res.split("\\s+");
        return temp[temp.length-1];
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

    public List<String> getPageLines() {
        return pageLines;
    }

    public void setPageLines(List<String> pageLines) {
        this.pageLines = pageLines;
    }

    public double getTotalDeposits() {
        return totalDeposits;
    }

    public void setTotalDeposits(double totalDeposits) {
        this.totalDeposits = totalDeposits;
    }

    
    
    
}
