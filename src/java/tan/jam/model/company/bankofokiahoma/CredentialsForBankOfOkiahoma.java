/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofokiahoma;

import java.util.List;
import tan.jam.company.utils.FileUtils;

public class CredentialsForBankOfOkiahoma {

    private String[] pages;
    private List<String> lines;

    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private double averageLedgerBalance;
    
    private List<String> selectedBlock;

    CredentialsForBankOfOkiahoma(String[] pages, List<String> lines) {
        this.pages = pages;
        this.lines = lines;
        //System.out.println("in credentials bank of america");
        startExecution();
    }

    private void startExecution() {
        this.companyName = "BOfOkiahoma";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.selectedBlock = captureSelectedBlock();
        this.totalDeposits = captureTotalDeposits();
        this.averageLedgerBalance = captureAverageLedgerBalance();
    }

     
     private double captureAverageLedgerBalance(){
        double d = 0;
        for(String s: lines){
            String result = FileUtils.getResultFromPattern("AVG\\s+LEDGER\\s+BALANCE", s);
            if(!result.startsWith("NoMatch")){
                String[] temp = s.split("\\s+");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(temp[3])));
                break;
            }
        }
        return d;
    }
    private List<String> captureSelectedBlock() {
        String start = "Starting\\s+Balance";
        String end = "Ending\\s+Balance";
        return FileUtils.getSelectedBlock(lines, start, end);
    }

    private double captureTotalDeposits() {
        String result = "";
        double d=0;
        for (String s : selectedBlock) {
            if (s.contains("Deposits")) {
                result = s;
                break;
            }
        }
        if(!result.equals("")){
            String[] temp = result.split("\\s+");
            d= FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
        }
        
        return d;
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("through\\s+\\d{1,2}-\\d{1,2}-\\d{1,2}", pages[0]);
        if (!result.equals("NoMatchFound")) {
            String[] temp = result.split("\\s+");
            return temp[temp.length-1];
        } else {
            return result;
        }
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("ACCOUNT\\s*:\\s+\\d+", pages[0]);
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
