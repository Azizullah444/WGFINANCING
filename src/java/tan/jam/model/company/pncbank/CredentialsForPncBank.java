package tan.jam.model.company.pncbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.model.company.pncbank.DailyLedgerBalanceForPncBank;

/**
 *
 * @author Lenovo
 */
public class CredentialsForPncBank {

    private String[] pages;
    private List<String> lines;
    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private List<String> selectedBlock;
    private double averageLedgerBalance;

    public CredentialsForPncBank(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }
    private void startExecution() {
        this.companyName = "PNC Bank";
        this.accountNumber = captureAccountNumber();
        this.date = captureDate();
        this.selectedBlock = captureSelectedBlock();
        this.totalDeposits = captureTotalDeposits();
        this.averageLedgerBalance = captureAverageLedgerBalance();
    }
    private List<String> captureSelectedBlock() {
        String start = "^\\s*Balance\\s+Summary";
        String end = "^\\s*Deposits\\s+and\\s+Other\\s+Additions";
        int startIndex = 0;
        int endIndex = 0;
        for (int a = 0; a < lines.size(); a++) {
            String r = FileUtils.getResultFromPattern(start, lines.get(a));
            if (!r.startsWith("NoMatch")) {
                startIndex = a;
                break;           
            }
        }
        for (int a = 0; a < lines.size(); a++) {
            String r = FileUtils.getResultFromPattern(end, lines.get(a));
            if (!r.startsWith("NoMatch")) {

                endIndex = a;
                break;
            }
        }
        return lines.subList(startIndex, endIndex);

    }

    
    private double captureAverageLedgerBalance() {
        double d = 0; String result="";
        String text = "balance\\s+balance";
        for (int a = 0; a < selectedBlock.size(); a++) {
             result = FileUtils.getResultFromPattern(text, selectedBlock.get(a));
            if (!result.startsWith("NoMatch")) {
                String finalResult = selectedBlock.get(a+1);
                String[] temp = finalResult.split(" ");
                if (temp.length > 0) {
                   d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[0]));
                }
                break;
            }
        }
        if (result.startsWith("NoMatch")) {d= new  DailyLedgerBalanceForPncBank(lines).findAverageValues();}
        return d;

    }

    private double captureTotalDeposits() {
        double d = 0;
        String text = "balance\\s+other\\s+additions\\s+deductions\\s+balance";
        for (int a = 0; a < selectedBlock.size(); a++) {
            String result = FileUtils.getResultFromPattern(text, selectedBlock.get(a));
            if (!result.startsWith("NoMatch")) {
                String finalResult = selectedBlock.get(a+1);
                String[] temp = finalResult.split("\\s+");
                if (temp.length > 0) {
                   d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[1]));
                }
                break;
            }
        }
        return d;
    }

    private String captureDate() {
        String da = "";
        String result = FileUtils.getResultFromPattern("to\\s+\\d{1,2}/\\d{2}/\\d{4}", pages[0]);
        if (!result.startsWith("NoMatch")) {
            String[] temp = result.split(" ");
            da = temp[temp.length - 1];
        }

        return da;
    }

    private String captureAccountNumber() {
        String result = FileUtils.getResultFromPattern("\\d{2,}-\\d{3,}-\\d{4,}", pages[0]);
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
