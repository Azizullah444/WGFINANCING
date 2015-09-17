package tan.jam.model.company.paradisebank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForParadiseBank {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> selectedBlock;

    public CredentialsForParadiseBank(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }


    private void startExecution() {
        this.companyName = "ParediseBank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }

    public CredentialsForParadiseBank(String companyName) {
        this.companyName = companyName;
    }


    private double captureTotalDeposits() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("Deposits/Credits")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.trim().split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[2]);
            d = FileUtils.convertStringToDouble(dd);
       }
       return d;
    }

    private String captureDate() {
         String dd = "";
        for (int a = 0; a < lines.size(); a++) {
            String d = FileUtils.getResultFromPattern("Date\\s+\\d{1,2}/\\d{1,2}/\\d{2,4}", this.lines.get(a));
            if (!d.startsWith("NoMatch")) {
                String[] tmp = d.split("\\s+");
                dd = tmp[tmp.length-1];
            }
        }
        return dd;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Account\\s+Number\\s*\\d+", pages[0]);
        
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

    
}