/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.guarantybank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForGuarantyBank {

    private String[] pages;
    private List<String> pageLines;
    private String accountNumber;
    private String companyName;
    private String date;
    private double totalDeposits;

    public CredentialsForGuarantyBank(String[] pages) {
        this.pages = pages;
        this.pageLines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "GuarantyBank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}/\\d{2,4}", pages[0]);
        return result;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Account\\s+Number:\\s+\\d+", pages[0]);
        return FileUtils.getFourDigitAccountNumber(result);
    }
    private double captureTotalDeposits(){
        String res = "";
        for(String s: pageLines){
            if(s.contains("Deposits / Misc Credits")){
                res = s;
                break;
            }
        }
        String[] temp=res.split("\\s+");
        res=FileUtils.getDollarSignRemovedValue(temp[temp.length-1]);
        return FileUtils.convertStringToDouble(res);
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
