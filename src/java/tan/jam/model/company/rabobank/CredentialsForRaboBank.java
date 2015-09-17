/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.rabobank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

public class CredentialsForRaboBank {

    private String[] pages;
    private List<String> lines;

    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;

    private List<String> selectedBlock;

    CredentialsForRaboBank(String[] pages, List<String> lines) {
        this.pages = pages;
        this.lines = lines;
        //System.out.println("in credentials bank of america");
        startExecution();
    }

    private void startExecution() {
        this.companyName = "RaboBank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();                        
        this.totalDeposits = captureTotalDeposits();

    }

    private double captureTotalDeposits() {
        String result = "";
        for (String s : lines) {
            if (s.contains("Total additions")) {
                result = s;
                break;
            }
        }
        if (!result.equals("")) {
            String[] temp = result.split(" ");
            return FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length - 1]));
        }
        return 0;
    }

    private String captureDate() {
        String result = FileUtils.getResultFromPattern("This\\s+Statement:.+", pages[0]);
        if (!result.equals("NoMatchFound")) {
            String[] temp = result.split("\\s+");
            return temp[temp.length - 3];
        } else {
            return result;
        }
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("Account\\s+Number:\\s+\\d+|Account\\s+number\\s+\\d+", pages[0]);
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

}
