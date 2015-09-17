/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.regionsbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForRegionsBank {
    private String[] pages;
    private List<String> pageLines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;

    public CredentialsForRegionsBank(String[] pages) {
        this.pages = pages;
        this.pageLines = FileUtils.fillLines(pages);
        startExecution();
        
    }

    private void startExecution() {
        this.companyName = "Regions Bank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }
    private double captureTotalDeposits(){
        String res = "";
        for(String s: pageLines){
            if(s.contains("Deposits & Credits")){
                res = s;
                break;
            }
        }
        res = res.substring(0, res.indexOf('+'));
        String[] temp = res.trim().split(" ");
        String r = temp[temp.length-1];
        r=FileUtils.getDollarSignRemovedValue(r);
        return FileUtils.convertStringToDouble(r);
    }
    private String captureAccountNumber(){
        return FileUtils.getFourDigitAccountNumber(FileUtils.getResultFromPattern("ACCOUNT\\s+#\\s+\\d{7,}", pages[0]));
       
    }
    private String captureDate(){
        String res = FileUtils.getResultFromPattern("through\\s+\\w+\\s+\\d{1,2}\\,\\s?\\d{4}", pages[0]);
        String[] temp = res.split(" ");
        return temp[temp.length-3]+" "+temp[temp.length-2]+" "+temp[temp.length-1];
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
