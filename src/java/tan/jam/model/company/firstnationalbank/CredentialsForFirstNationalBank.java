package tan.jam.model.company.firstnationalbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CredentialsForFirstNationalBank {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> selectedBlock;

    public CredentialsForFirstNationalBank(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.companyName = "FNB";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.totalDeposits = captureTotalDeposits();
    }

    public CredentialsForFirstNationalBank(String companyName) {
        this.companyName = companyName;
    }


    private double captureTotalDeposits() {
         String result1 = "";
         String result2="";
         double d1=0;
         double d2=0;
       for(String s: lines){
           if(s.contains("DEPOSITS")){
               result1 = s;
               break;
           }
       }
       if(result1.length() > 1){
           String[] temp = result1.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(temp[temp.length-1]);
            d1 = FileUtils.convertStringToDouble(dd);
           }
       for(String s: lines){
           if(s.contains("OTHER CREDITS")){
               result2 = s;
               break;
           }
       }
       
           if(result2.length()>1){
           String[] temp2 = result2.split("\\s+");
           String dd2 = FileUtils.getDollarSignRemovedValue(temp2[temp2.length-1]);
           d2 = FileUtils.convertStringToDouble(dd2);
           
       }
       return d1+d2;
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("TO\\s+\\d{2}/\\d{2}/\\d{4}", pages[0]);
        return FileUtils.getLastElementFromArray(result);
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("ACCOUNT\\s+\\d+", pages[0]);
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