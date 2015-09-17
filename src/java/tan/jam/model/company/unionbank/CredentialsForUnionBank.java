package tan.jam.model.company.unionbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForUnionBank {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private double averageLedgerBalance;


    public CredentialsForUnionBank(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "UnionBank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
        this.averageLedgerBalance = captureAverageLedgerBalance();
    }

    public CredentialsForUnionBank(String companyName) {
        this.companyName = companyName;
    }
    
    private double captureAverageLedgerBalance(){
        double d = 0;
        for(String s: lines){
            String result = FileUtils.getResultFromPattern("Statement\\s+Average\\s+Ledger\\s+Balance", s);
            if(!result.startsWith("NoMatch")){
                String[] temp = s.split("\\s+");
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
           if(s.contains("Additions")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[temp.length-1]);
            d = FileUtils.convertStringToDouble(dd);
       }
       return d;
    }

    private String captureDate() {
         String dd = "";
        for (int a = 0; a < lines.size(); a++) {
            String d = FileUtils.getResultFromPattern("\\d{2}/\\d{2}/\\d{2}\\s+-\\s+\\d{2}/\\d{2}/\\d{2}", this.lines.get(a));
            if (!d.startsWith("NoMatch")) {
                String[] tmp = d.split(" ");
                dd = tmp[tmp.length-1];
            }
        }
        return dd;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Statement\\s+Number:\\s*\\d+", pages[0]);
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
    public double getAverageLedgerBalance() {
        return averageLedgerBalance;
    }

    public void setAverageLedgerBalance(double averageLedgerBalance) {
        this.averageLedgerBalance = averageLedgerBalance;
    }
}