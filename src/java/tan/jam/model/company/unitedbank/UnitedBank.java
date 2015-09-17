/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.unitedbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class UnitedBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForUnitedBank credentials;
    private DailyLedgerBalanceForUnitedBank dailyLedgerBalance;
    private DepositsForUnitedBank deposits;
    private DebitsForUnitedBank debits;
    private KeywordsForUnitedBank keywordsInput;
    
    public UnitedBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForUnitedBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForUnitedBank(lines);
        this.deposits = new DepositsForUnitedBank(lines);
        this.debits = new DebitsForUnitedBank(lines);
        this.keywordsInput = new KeywordsForUnitedBank(lines, words);
        System.out.println("United Bank In Progress");
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
    
    public CredentialsForUnitedBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForUnitedBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForUnitedBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForUnitedBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForUnitedBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForUnitedBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForUnitedBank getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForUnitedBank debits) {
        this.debits = debits;
    }
   
    public KeywordsForUnitedBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForUnitedBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
