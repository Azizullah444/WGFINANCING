/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.wellsfargo;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
class CredentialsForWellsNew {

    private String[] pages;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private double averageLedgerBalance;
    private List<String> lines;

    public CredentialsForWellsNew(String[] pages,List<String> lines) {
        this.pages = pages;
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.companyName = "Wells Fargo";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        this.averageLedgerBalance = captureAverageLedgerBalance();
                
    }
     private double captureAverageLedgerBalance(){
        double d = 0;
        for(String s: lines){
            //String result = FileUtils.getResultFromPattern("Average daily ledger balance\\s+\\s\\$?\\d{1,}\\,?", s);
            if(s.contains("Average daily ledger balance")){
                String[] temp = s.split(" ");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
                break;
            }
        }
        return d;
    }
//    private double captureAverageLedgerBalance(){
//        double d = 0;
//        for(String s: lines){
//            String result = FileUtils.getResultFromPattern("Average daily ledger balance\\s+\\s\\$?\\d{1,}\\,?", s);
//            if(!result.startsWith("NoMatch")){
//                String[] temp = s.split(" ");
//                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
//                break;
//            }
//        }
//        return d;
//    }
    private double captureTotalDeposits() {
        String result = FileUtils.getResultFromPattern("\\d{10,}\\s+-?\\$.+", pages[0]);
        double d = 0;
        if (!result.startsWith("NoMatch")) {
            String[] temp = result.split(" ");
            String value = FileUtils.getDollarSignRemovedValue(temp[2]);
             d = FileUtils.convertStringToDouble(value);
        }
        return d;
    }

    private String captureDate() {
        String dd = "";
        for (int a = 0; a < lines.size(); a++) {
            // String d = FileUtils.getResultFromPattern("(\\w)+(\\s)+(\\d{1,2})(\\,)(\\s)+(\\d{4})(\\s)+(-)(\\s)+(\\w)+(\\s)+(\\d{1,2})(\\,)(\\s)+(\\d{4})", this.lines.get(a));
            String d = FileUtils.getResultFromPattern("(-)(\\s)+(\\w)+(\\s)+(\\d{1,2})", this.lines.get(a));
            if (!d.startsWith("NoMatch")) {
                String[] tmp = d.split(" ");
                dd = tmp[1].trim() + " " + tmp[2];
            }
        }
        return dd.trim();
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("\\d{10,}", pages[0]);
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

    public double getAverageLedgerBalance() {
        return averageLedgerBalance;
    }

    public void setAverageLedgerBalance(double averageLedgerBalance) {
        this.averageLedgerBalance = averageLedgerBalance;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
