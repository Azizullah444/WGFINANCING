/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.regionsbank;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class RegionsBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private List<String> keywords;
    
    private CredentialsForRegionsBank credentials;
    private CreditsForRegionsBank credits;
    private DailyLedgerBalanceForRegionsBank dailyLedgerBalance;
    private DepositsForRegionsBank deposits;
    private KeywordsForRegionsBank keywordsInput;
    
    public RegionsBank(String[] pages, List<String> keywords) {
        this.pages = pages;
        this.keywords = keywords;
        this.lines = FileUtils.fillLines(pages);
        this.credits = new CreditsForRegionsBank(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForRegionsBank(lines);
        this.credentials = new CredentialsForRegionsBank(pages);
        this.deposits = new DepositsForRegionsBank(lines);
        this.keywordsInput = new KeywordsForRegionsBank(lines, keywords);
        System.out.println("Region Bank In Progress");        
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
    
    public CredentialsForRegionsBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForRegionsBank credentials) {
        this.credentials = credentials;
    }
    
    public CreditsForRegionsBank getCredits() {
        return credits;
    }
    
    public void setCredits(CreditsForRegionsBank credits) {
        this.credits = credits;
    }
    
    public DailyLedgerBalanceForRegionsBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForRegionsBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForRegionsBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForRegionsBank deposits) {
        this.deposits = deposits;
    }
    
    public KeywordsForRegionsBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForRegionsBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
}
