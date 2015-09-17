/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstcitizensbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

public class CredentialsForFirstCitizensBank {

    private String[] pages;
    private List<String> lines;

    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private double averageLedgerBalance;
    
    private List<String> selectedBlock;

    CredentialsForFirstCitizensBank(String[] pages, List<String> lines) {
        this.pages = pages;
        this.lines = lines;
        //System.out.println("in credentials bank of america");
        startExecution();
    }

    private void startExecution() {
        this.companyName = "FirstCitizenB";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.selectedBlock = captureSelectedBlock();
        this.totalDeposits = captureTotalDeposits();
        this.averageLedgerBalance = captureAverageLedgerBalance();
    }

     
     private double captureAverageLedgerBalance(){
        double d = 0;
        for(String s: lines){
            String result = FileUtils.getResultFromPattern("Average Ledger Balance", s);
            if(!result.startsWith("NoMatch")){
                String[] temp = s.split(" ");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
                break;
            }
        }
        return d;
    }
    private List<String> captureSelectedBlock() {
        String start = "Beginning\\s+Balance";
        String end = "Ending\\s+balance";
        return FileUtils.getSelectedBlock(lines, start, end);
    }

    private double captureTotalDeposits() {
        String result = "";
        double d1=0;
        double d2=0;
        for (String s : selectedBlock) {
            if (s.contains("Deposits")) {
                result = s;
                break;
            }
        }
        if(!result.equals("")){
            String[] temp = result.split("\\s+");
            d1= FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[2]));
        }
        for (String s : selectedBlock) {
            if (s.contains("Other Credits")) {
                result = s;
                break;
            }
        }
        if(!result.equals("")){
            String[] temp = result.split("\\s+");
            d2= FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[3]));
        }
        return d1+d2;
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("Thru\\s+\\w+\\s+\\d{1,2}", pages[0]);
        if (!result.equals("NoMatchFound")) {
            String[] temp = result.split("\\s+");
            return temp[1];
        } else {
            return result;
        }
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Account\\s+Number\\s*:\\s+\\d+", pages[0]);
        return FileUtils.getFourDigitAccountNumber(result);
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

    public double getTotalDeposits() {
        return totalDeposits;
    }

    public void setTotalDeposits(double totalDeposits) {
        this.totalDeposits = totalDeposits;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public double getAverageLedgerBalance() {
        return averageLedgerBalance;
    }

    public void setAverageLedgerBalance(double averageLedgerBalance) {
        this.averageLedgerBalance = averageLedgerBalance;
    }

}
