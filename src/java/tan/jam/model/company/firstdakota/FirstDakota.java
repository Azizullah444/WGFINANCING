/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstdakota;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class FirstDakota extends Company {
    
    private String[] pages;
    private List<String> lines;
    private List<String> keywords;
    
    private CredentialsForFirstDakota credentials;
    private DebitForFirstDakota credits;
    private DailyLedgerBalanceForFirstDakota dailyLedgerBalance;
    private DepositsForFirstDakota deposits;
    private KeywordsForFirstDakota keywordsInput;
    
    public FirstDakota(String[] pages, List<String> keywords) {
        this.pages = pages;
        this.keywords = keywords;
        this.lines = FileUtils.fillLines(pages);
        this.credits = new DebitForFirstDakota(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForFirstDakota(lines);
        this.credentials = new CredentialsForFirstDakota(pages);
        this.deposits = new DepositsForFirstDakota(lines);
        this.keywordsInput = new KeywordsForFirstDakota(lines, keywords);
        System.out.println("First Dakota In Progress");      
        startExecution();
        
    }
    
    private void startExecution() {
        
        executeDailyLedgerBalanceSection();
        executeCredentialsSection();
        executeCreditsSection();
        executeDepositsSection();
        executeKeywordsSection();
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

    private void executeCreditsSection() {
        setOnlineTransferToDate(this.credits.getDate());
        setOnlineTransferToAmount(this.credits.getAmount());
        setOnlineTransferToCheckNo(this.credits.getCheckNo());
        setOnlineTransferToDescription(this.credits.getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.credits.getCheckNoDuplicateRemoved());
    }
    
    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
    }
    
    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFromSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFromSum());
    }
    
    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        setAverageLedgerBalance(this.dailyLedgerBalance.getAverageLedgerBalance());
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
    
    public CredentialsForFirstDakota getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForFirstDakota credentials) {
        this.credentials = credentials;
    }
    
    public DebitForFirstDakota getCredits() {
        return credits;
    }
    
    public void setCredits(DebitForFirstDakota credits) {
        this.credits = credits;
    }
    
    public DailyLedgerBalanceForFirstDakota getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForFirstDakota dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForFirstDakota getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForFirstDakota deposits) {
        this.deposits = deposits;
    }
    
    public KeywordsForFirstDakota getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForFirstDakota keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
}
