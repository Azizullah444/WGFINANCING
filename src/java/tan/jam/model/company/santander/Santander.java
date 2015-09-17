/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.santander;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class Santander extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForSantander credentials;
    private DailyLedgerBalanceForSantander dailyLedgerBalance;
    private DepositsForSantander deposits;
    private DebitsForSantander debits;
    private KeywordsForSantander keywordsInput;
    
    public Santander(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForSantander(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForSantander(lines);
        this.deposits = new DepositsForSantander(lines);
        this.debits = new DebitsForSantander(lines);
        this.keywordsInput = new KeywordsForSantander(lines, words);
        System.out.println("Santander In Progress");
        startExecution();
    }
    
    private void startExecution() {
        
        executeCredentialsSection();
        executeDebitsSection();
        executeDepositsSection();
        executeDailyLedgerBalanceSection();
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
    
    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        
    }
    
    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFromSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFromSum());
    }
    
    private void executeDebitsSection() {
        setOnlineTransferToDate(this.debits.getDate());
        setOnlineTransferToAmount(this.debits.getAmount());
        setOnlineTransferToCheckNo(this.debits.getCheckNo());
        setOnlineTransferToDescription(this.debits.getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getCheckNoDuplicatesRemoved());
        
    }
    
    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
        setAverageLedgerBalance(this.credentials.getAverageLedgerBalance());
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
    
    public CredentialsForSantander getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForSantander credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForSantander getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForSantander dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForSantander getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForSantander deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForSantander getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForSantander debits) {
        this.debits = debits;
    }
   
    public KeywordsForSantander getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForSantander keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
