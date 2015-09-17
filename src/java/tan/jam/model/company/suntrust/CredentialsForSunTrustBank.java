/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.suntrust;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForSunTrustBank {

    private String[] pages;
    private List<String> pageLines;
    private String accountNumber;
    private String companyName;
    private String date;
    private double totalDeposits;

    public CredentialsForSunTrustBank(String[] pages) {
        this.pages = pages;
        this.pageLines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "SunTrust";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}/\\d{4}", pages[0]);
        return result;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("\\d{11,}", pages[0]);
        return FileUtils.getFourDigitAccountNumber(result);
    }
    private double captureTotalDeposits(){
        String res = "";
        for(String s: pageLines){
            if(s.contains("Deposits/Credits")){
                res = s;
                break;
            }
        }
      
        String[] temp=res.trim().split("\\s+");
        String r = temp[1];
        r=FileUtils.getDollarSignRemovedValue(r);
        return FileUtils.convertStringToDouble(r);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getPages() {
        return pages;
    }

    public void setPages(String[] pages) {
        this.pages = pages;
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
