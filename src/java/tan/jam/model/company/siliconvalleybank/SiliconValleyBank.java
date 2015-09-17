/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.siliconvalleybank;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class SiliconValleyBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForSiliconValleyBank credentials;
    private DailyLedgerBalanceForSiliconValleyBank dailyLedgerBalance;
    private DepositsForSiliconValleyBank deposits;
    private DebitsForSiliconValleyBank debits;
    private KeywordsForSiliconValleyBank keywordsInput;
    
    public SiliconValleyBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForSiliconValleyBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForSiliconValleyBank(lines);
        this.deposits = new DepositsForSiliconValleyBank(lines);
        this.debits = new DebitsForSiliconValleyBank(lines);
        this.keywordsInput = new KeywordsForSiliconValleyBank(lines, words);
        System.out.println("SeliconValley In Progress");
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
    
    public CredentialsForSiliconValleyBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForSiliconValleyBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForSiliconValleyBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForSiliconValleyBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForSiliconValleyBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForSiliconValleyBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForSiliconValleyBank getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForSiliconValleyBank debits) {
        this.debits = debits;
    }
   
    public KeywordsForSiliconValleyBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForSiliconValleyBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
