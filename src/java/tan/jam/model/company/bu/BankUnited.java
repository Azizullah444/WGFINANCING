/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bu;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class BankUnited extends Company {
    
    private String[] pages;
    private List<String> lines;
    private List<String> keywords;
    private CredentialsForBankUnited credentials;
    private DepositsForBankUnited deposits;
    private CreditsForBankUnited credits;
    private DailyLedgerBalanceForBankUnited dailyLedgerBalance;
    private KeywordsForBankUnited keywordsInput;
    
    public BankUnited(String[] pages, List<String> keywords) {
        this.pages = pages;
        this.keywords = keywords;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForBankUnited(pages);
        this.deposits = new DepositsForBankUnited(lines, keywords);
        this.credits = new CreditsForBankUnited(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForBankUnited(lines);
        this.keywordsInput = new KeywordsForBankUnited(lines, keywords);
        System.out.println("Bank United In Progress");
        startExecution();
        
    }
    
    private void startExecution() {
        executeCredentialsSection();
        executeDailyLedgerBalanceSection();
        executeDebitsSection();
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
    
    private void executeDebitsSection() {
        setOnlineTransferToDate(this.credits.getOnlineTransferTo().getDate());
        setOnlineTransferToAmount(this.credits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.credits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToDescription(this.credits.getOnlineTransferTo().getDescription());
       setOnlineTransferToCheckNoDuplicateRemoved(this.credits.getOnlineTransferTo().getCheckNoDuplicateRemoved());
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
    
    public CredentialsForBankUnited getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForBankUnited credentials) {
        this.credentials = credentials;
    }
    
    public DepositsForBankUnited getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForBankUnited deposits) {
        this.deposits = deposits;
    }
    
    public CreditsForBankUnited getCredits() {
        return credits;
    }
    
    public void setCredits(CreditsForBankUnited credits) {
        this.credits = credits;
    }
    
    public DailyLedgerBalanceForBankUnited getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForBankUnited dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public KeywordsForBankUnited getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForBankUnited keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
}
