package tan.jam.model.company.bankofthewest;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForBankOfTheWest {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> selectedBlock;
    private double averageLedgerBalance;

    public CredentialsForBankOfTheWest(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "BOTW";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        this.averageLedgerBalance = captureAverageLedgerBalance();
    }

    public CredentialsForBankOfTheWest(String companyName) {
        this.companyName = companyName;
    }
    private double captureAverageLedgerBalance(){
        double d = 0;
        for(String s: lines){
            String result = FileUtils.getResultFromPattern("Average\\s+monthly\\s+balance", s);
            if(!result.startsWith("NoMatch")){
                String[] temp = s.split(" ");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
                break;
            }
        }
        return d;
    }

    private double captureTotalDeposits() {
         String result1 = "";
         String result2="";
         double d1=0;
         double d2=0;
         double d = 0;
       for(String s: lines){
           if(s.contains("Credits")){
               result1 = s;
               break;
           }
       }
       if(result1.length() > 1){
           String[] temp = result1.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[2]);
            d1 = FileUtils.convertStringToDouble(dd);
           }
       for(String s: lines){
           if(s.contains("Deposits")){
               result2 = s;
               break;
           }
       }
       
           if(result2.length()>1){
           String[] temp2 = result2.split("\\s+");
           String dd2 = FileUtils.getDollarSignRemovedValue(temp2[2]);
           d2 = FileUtils.convertStringToDouble(dd2);
           
       }
        d=d1+d2;
       return d;
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("-\\s+\\D+\\s+\\d{1,}", pages[0]);
        String[] temp = result.split(" ");
        return temp[1];
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("CLASSIC\\s+BUSINESS\\s+CHECKING\\s+\\d+-+\\d+", pages[0]);
          if (result.startsWith("NoMatch"))
          {
              result=FileUtils.getResultFromPattern("\\d{3}-\\d{6,}", pages[0]);
          }
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