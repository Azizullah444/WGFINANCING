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
public class CredentialsForWellsOld {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private double averageLedgerBalance;

    CredentialsForWellsOld(String[] page,List<String> lines) {
        this.pages = page;
        this.lines = lines;
        //System.out.println("in credentials for wells old");
        startExecution();
    }

    private void startExecution() {
        this.companyName = "Wells Fargo";
        this.accountNumber = captureAccountNumber();
        //System.out.println("accno " + this.accountNumber);
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        this.averageLedgerBalance = captureAverageLedgerBalance();
        
    }
//     private double captureAverageLedgerBalance(){
//        double d = 0;
//        for(String s: lines){
//            String result = FileUtils.getResultFromPattern("Average\\s+ledger\\s+balance\\s+this\\s+period\\s+\\s\\$?\\d{1,}\\,?", s);
//            if(!result.startsWith("NoMatch")){
//                String[] temp = s.split(" ");
//                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length]));
//                break;
//            }
//        }
//        return d;
//    }
      private double captureAverageLedgerBalance(){
        double d = 0;
        for(String s: lines){
           // String result = FileUtils.getResultFromPattern("Average\\s+ledger\\s+balance\\s+this\\s+period\\s+\\s\\$?\\d{1,}\\,?", s);
            if(s.contains("Average ledger balance this period")){
                //System.out.println(s);
                String[] temp = s.split(" ");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
                break;
            }
        }
        return d;
    }
     private double captureTotalDeposits() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("Deposits/Credits")||s.contains("Deposits/Additions")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split(" ");
           String dd = FileUtils.getDollarSignRemovedValue(temp[1]);
            d = FileUtils.convertStringToDouble(dd);
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
        String result = FileUtils.getTargetByMatch(pages[0], "Account number:\\s+\\d{8,}|account\\s+number:\\s+\\d+");
        return FileUtils.getFourDigitAccountNumber(result);
    }
//    private String captureAccountNumber() {
//        String result = FileUtils.getTargetByMatch(pages[0], "\\d{10,13}");
//        return FileUtils.getFourDigitAccountNumber(result);
//    }

   
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

    public double getAverageLedgerBalance() {
        return averageLedgerBalance;
    }

    public void setAverageLedgerBalance(double averageLedgerBalance) {
        this.averageLedgerBalance = averageLedgerBalance;
    }

}
