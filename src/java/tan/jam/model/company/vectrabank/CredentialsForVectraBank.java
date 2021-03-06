package tan.jam.model.company.vectrabank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForVectraBank {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> selectedBlock;

    public CredentialsForVectraBank(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "VectraBank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }
    
    public CredentialsForVectraBank(String companyName) {
        this.companyName = companyName;
    }


    private double captureTotalDeposits() {
         String result = "";
         double d = 0;
       for(int a=0;a<lines.size();a++){
           if(lines.get(a).matches("Previous\\s+Balance\\s+Deposits/Credits\\s+Charges/Debits\\s+Checks\\s+Processed\\s+Ending\\s+Balance")){
               result = lines.get(a+1);
               break;
           }
       }
       if(result.length() > 1){
           String[] temp = result.trim().split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[1]);
            d = FileUtils.convertStringToDouble(dd);
           }
     
       return d;
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("This\\s+Statement:.+", pages[0]);
        String[] temp=result.split("\\s+");
        return temp[temp.length-3];
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Account\\s+\\d+", pages[0]);
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