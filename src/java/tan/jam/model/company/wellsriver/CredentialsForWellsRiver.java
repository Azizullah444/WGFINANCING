package tan.jam.model.company.wellsriver;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForWellsRiver {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> selectedBlock;

    public CredentialsForWellsRiver(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "WellsRiver";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }

    public CredentialsForWellsRiver(String companyName) {
        this.companyName = companyName;
    }


    private double captureTotalDeposits() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.matches("^\\s*Deposits\\s+and\\s+other\\s+credits.+")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split(" "); 
           String dd = FileUtils.getDollarSignRemovedValue(temp[temp.length-1]);
            d = FileUtils.convertStringToDouble(dd);
       }
       return d;
    }

    private String captureDate() {
         String dd = "";
        for (int a = 0; a < lines.size(); a++) {
            String d = FileUtils.getResultFromPattern("Date\\s+\\d{1,2}/\\d{1,2}/\\d{1,2}", this.lines.get(a));
            if (!d.startsWith("NoMatch")) {
                String[] tmp = d.split(" ");
                dd = tmp[tmp.length-1];
            }
        }
        return dd;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Account\\s+\\d+", pages[0]);
        if (result.startsWith("NoMatch")){result = FileUtils.getResultFromPattern("\\d{7,}", pages[0]);}
        
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