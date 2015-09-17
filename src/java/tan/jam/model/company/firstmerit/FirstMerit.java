/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstmerit;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class FirstMerit extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForFirstMerit credentials;
    private DailyLedgerBalanceForFirstMerit dailyLedgerBalance;
    private DepositsForFirstMerit deposits;
    private DebitsForFirstMerit debits;
    private KeywordsForFirstMerit keywordsInput;
    
    public FirstMerit(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForFirstMerit(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForFirstMerit(lines);
        this.deposits = new DepositsForFirstMerit(lines);
        this.debits = new DebitsForFirstMerit(lines);
        this.keywordsInput = new KeywordsForFirstMerit(lines, words);
        System.out.println("FirstMerit In Progress");
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
        setAverageLedgerBalance(this.dailyLedgerBalance.getAverageLedgerBalance());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        setMinValue(this.dailyLedgerBalance.getMinValue());
        
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
    
    public CredentialsForFirstMerit getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForFirstMerit credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForFirstMerit getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForFirstMerit dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForFirstMerit getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForFirstMerit deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForFirstMerit getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForFirstMerit debits) {
        this.debits = debits;
    }
   
    public KeywordsForFirstMerit getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForFirstMerit keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
