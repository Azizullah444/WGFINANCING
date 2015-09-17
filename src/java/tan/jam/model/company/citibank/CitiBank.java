/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citibank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CitiBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private List<String> keywords;
    private CredentialsForCitiBank credentials;
    private DailyLedgerBalanceForCitiBank dailyLedgerBalance;
    private DepositsForCitiBank deposits;
    private DebitsForCitiBank debits;
    private KeywordsForCitiBank keywordsInput;
    
    public CitiBank(String[] pages, List<String> keywords) {
        this.pages = pages;
        this.keywords = keywords;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForCitiBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForCitiBank(lines);
        this.deposits = new DepositsForCitiBank(lines, keywords);
        this.debits = new DebitsForCitiBank(lines);
        this.keywordsInput = new KeywordsForCitiBank(lines, keywords);
        System.out.println("Citibank In Progress");
        startExecution();
        
    }
    
    private void startExecution() {
        executeCredentialsSection();
        executeDailyLedgerBalanceSection();
        executeDebitsSection();
        executeDepositsSection();
        executeKeywordsSection();
    }
    private void executeKeywordsSection(){
        setKeywordSum(this.keywordsInput.getSumForKeywords());
        setKeywordAmount(this.keywordsInput.getKeywordAmount());
        setKeywordDate(this.keywordsInput.getKeywordDate());
        setKeywordDescription(this.keywordsInput.getKeywordDescription());
        setDailypaymentKeywordDate(this.keywordsInput.getDailypaymentKeywordDate());
        setDailypaymentKeywordAmount(this.keywordsInput.getDailypaymentKeywordAmount());
        setDailypaymentKeywordDescription(this.keywordsInput.getDailypaymentKeywordDescription());
    }
    private void executeCredentialsSection() {
        setCompanyName("CitiBank");
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
    }
    
    private void executeDebitsSection() {
        setOnlineTransferToDate(this.debits.getOnlineTransferTo().getDate());
        setOnlineTransferToAmount(this.debits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.debits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToDescription(this.debits.getOnlineTransferTo().getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getOnlineTransferTo().getCheckNoDuplicateRemoved());
    }
    
    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.onlineTransferFrom.getSum());
        setSumOfReversalFrom(this.deposits.onlineReversalFrom.getSum());
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
    
    public CredentialsForCitiBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForCitiBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForCitiBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForCitiBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForCitiBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForCitiBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForCitiBank getDebits() {
        return debits;
    }
    
    public void setDebits(DebitsForCitiBank debits) {
        this.debits = debits;
    }

    public KeywordsForCitiBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForCitiBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
}
