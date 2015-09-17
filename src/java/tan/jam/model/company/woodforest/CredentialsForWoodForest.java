package tan.jam.model.company.woodforest;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForWoodForest {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> selectedBlock;
    private double averageLedgerBalance;

    public CredentialsForWoodForest(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "WoodForest";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        this.averageLedgerBalance = captureAverageLedgerBalance();
    }
    private double captureAverageLedgerBalance(){
        double d = 0;
        for(String s: lines){
            String result = FileUtils.getResultFromPattern("^\\s*Average\\s+Balance", s);
            if(!result.startsWith("NoMatch")){
                String[] temp = s.split("\\s+");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[2]));
                break;
            }
        }
        return d;
    }
    
    public CredentialsForWoodForest(String companyName) {
        this.companyName = companyName;
    }


    private double captureTotalDeposits() {
         String result = "";
         double d = 0;
       for(int a=0;a<lines.size();a++){   
           if(lines.get(a).matches("^\\s*FORWARD\\s+DEBITS\\s+CREDITS\\s+.+")){
               result = lines.get(a+1); 
               break;
           }
       }
       if(result.length() > 1){
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[temp.length-2]);
            d = FileUtils.convertStringToDouble(dd);
           }
     
       return d;
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("\\D+\\s+\\d{2},\\s+\\d{4}", pages[0]);
        return result.substring(3);
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Checking\\s+\\d+", pages[0]);
        if (result.startsWith("NoMatch")){ result = FileUtils.getResultFromPattern("\\s+\\d{6,}", pages[0]); }
        
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