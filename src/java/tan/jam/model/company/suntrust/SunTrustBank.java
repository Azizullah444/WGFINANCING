/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.suntrust;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class SunTrustBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> keywords;

    private CredentialsForSunTrustBank credentials;
    private DebitsForSunTrustBank debits;
    private DepositsForSunTrustBank deposits;
    private KeywordsForSunTrust keywordsInput;
    DailyLedgerBalanceForSunTrust dailyLedgerBalance;

    public SunTrustBank(String[] pages, List<String> keywords) {
        //System.out.println("in suntrust bank");
        this.pages = pages;
        this.keywords = keywords;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForSunTrustBank(pages);
        this.debits = new DebitsForSunTrustBank(lines);
        this.deposits = new DepositsForSunTrustBank(lines);
        this.keywordsInput = new KeywordsForSunTrust(lines, keywords);
        this.dailyLedgerBalance = new DailyLedgerBalanceForSunTrust(lines);
        System.out.println("Suntrust In Progress");
        startExecution();
    }

    private void startExecution() {
        executeCredentialsSection();
        executeDepositsSection();
        executeDebitsSection();
        executeDailyLedgerBalanceSection();
        executeKeywordsSection();
    }

    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
    }

    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFrom().getSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFromSum());
    }

    private void executeDebitsSection() {
        setOnlineTransferToAmount(this.debits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.debits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getOnlineTransferTo().getCheckNoDuplicate());
        setOnlineTransferToDate(this.debits.getOnlineTransferTo().getDate());
        setOnlineTransferToDescription(this.debits.getOnlineTransferTo().getDescription());
    }

    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        setAverageLedgerBalance(this.dailyLedgerBalance.getAverageLedgerBalance());
    }

    private void executeKeywordsSection() {
        setKeywordSum(this.keywordsInput.getSumForKeywords());
        setKeywordAmount(this.keywordsInput.getKeywordAmount());
        setKeywordDate(this.keywordsInput.getKeywordDate());
        setKeywordDescription(this.keywordsInput.getKeywordDescription());
        setDailypaymentKeywordDate(this.keywordsInput.getDailypaymentKeywordDate());
        setDailypaymentKeywordAmount(this.keywordsInput.getDailypaymentKeywordAmount());
        setDailypaymentKeywordDescription(this.keywordsInput.getDailypaymentKeywordDescription());
        
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

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public CredentialsForSunTrustBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForSunTrustBank credentials) {
        this.credentials = credentials;
    }

    public DebitsForSunTrustBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForSunTrustBank debits) {
        this.debits = debits;
    }

    public DepositsForSunTrustBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForSunTrustBank deposits) {
        this.deposits = deposits;
    }

    public KeywordsForSunTrust getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForSunTrust keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    public DailyLedgerBalanceForSunTrust getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForSunTrust dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
}
