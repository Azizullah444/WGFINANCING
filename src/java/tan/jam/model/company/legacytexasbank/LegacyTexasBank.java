/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.legacytexasbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class LegacyTexasBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForLegacyTexasBank credentials;
    private DailyLedgerBalanceForLegacyTexasBank dailyLedgerBalance;
    private DepositsForLegacyTexasBank deposits;
    private DebitsForLegacyTexasBank debits;
    private KeywordsForLegacyTexasBank keywordsInput;
    
    public LegacyTexasBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForLegacyTexasBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForLegacyTexasBank(lines);
        this.deposits = new DepositsForLegacyTexasBank(lines);
        this.debits = new DebitsForLegacyTexasBank(lines);
        this.keywordsInput = new KeywordsForLegacyTexasBank(lines, words);
        System.out.println("LegacyTexas In Progress");
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
    
    public CredentialsForLegacyTexasBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForLegacyTexasBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForLegacyTexasBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForLegacyTexasBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForLegacyTexasBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForLegacyTexasBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForLegacyTexasBank getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForLegacyTexasBank debits) {
        this.debits = debits;
    }
   
    public KeywordsForLegacyTexasBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForLegacyTexasBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
