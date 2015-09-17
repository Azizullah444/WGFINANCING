/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citibank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForCitiBank {

    private String[] pages;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> lines;

    public CredentialsForCitiBank(String[] pages, List<String> lines) {
        this.pages = pages;
        this.lines = lines;
        
        startExecution();
    }

    private void startExecution() {
     this.companyName = "CitiBank";
     this.accountNumber = captureAccountNumber();
     this.date = captureDate();
     this.totalDeposits = captureTotalCredits();
    }
    private double  captureTotalCredits(){
        String result="";
        for(String s: lines){
            String str = FileUtils.getResultFromPattern("^\\s*Total\\s+Debits/Credits", s);
            if(!str.startsWith("NoMatch")){
                String value = s;            
                result = value;
                break;
            }
        }
        String[] temp = result.split(" ");
        System.out.println(temp[temp.length-1]);
        return FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
    }
    private String captureDate(){
        String result = FileUtils.getResultFromPattern("-\\s+?\\w+\\s+\\d{1,2}\\,\\s+?\\d{2,4}", pages[0]);
        return result.substring(1, result.length()).trim();
    }
    private String captureAccountNumber(){
        String result = FileUtils.getResultFromPattern("\\d{8,}", pages[0]);
        result=FileUtils.getFourDigitAccountNumber(result);
        return result;
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

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public double getTotalDeposits() {
        return totalDeposits;
    }

    public void setTotalDeposits(double totalDeposits) {
        this.totalDeposits = totalDeposits;
    }
}
