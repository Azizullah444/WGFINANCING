/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.tdbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class TdBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForTdBank dailyLedgerBalance;
    private double averageLedgerBalance;
    private CredentialsForTdBank credentials;
    private DebitsForTdBank debits;
    private DepositsForTdBank deposits;
    private KeywordsForTdBank keywordsInput;
    
    public TdBank(String[] pages, List<String> words) {
        this.pages = pages;
       this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForTdBank(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForTdBank(lines);
        this.debits = new DebitsForTdBank(lines);
        this.deposits = new DepositsForTdBank(lines);
       this.keywordsInput = new KeywordsForTdBank(lines,words);
        System.out.println("TD Bank In Progress");
        startExecution();
    }
    
    private void startExecution() {
        executeCredentialsSection();
        executeDailyLedgerBalanceSection();
        executeDebitsSection();
        executeDepositsSection();
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
        setSumOfReversalFrom(this.deposits.getOnlineReversalFrom().getSum());
    }
    
    private void executeDebitsSection() {
        setOnlineTransferToDate(this.debits.getOnlineTransferTo().getDate());
        setOnlineTransferToAmount(this.debits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.debits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToDescription(this.debits.getOnlineTransferTo().getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getOnlineTransferTo().getCheckNoDuplicateRemoved());;
        
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
    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
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
    
    public CredentialsForTdBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForTdBank credentials) {
        this.credentials = credentials;
    }
    
    public DebitsForTdBank getDebits() {
        return debits;
    }
    
    public void setDebits(DebitsForTdBank debits) {
        this.debits = debits;
    }
    public DailyLedgerBalanceForTdBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForTdBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    public DepositsForTdBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForTdBank deposits) {
        this.deposits = deposits;
    }
    public KeywordsForTdBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForTdBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
}
