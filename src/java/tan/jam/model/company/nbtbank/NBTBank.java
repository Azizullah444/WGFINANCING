/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.nbtbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class NBTBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForNBTBank credentials;
    private DailyLedgerBalanceForNBTBank dailyLedgerBalance;
    private DepositsForNBTBank deposits;
    private DebitsForNBTBank debits;
    private KeywordsForNBTBank keywordsInput;
    
    public NBTBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForNBTBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForNBTBank(lines);
        this.deposits = new DepositsForNBTBank(lines);
        this.debits = new DebitsForNBTBank(lines);
        this.keywordsInput = new KeywordsForNBTBank(lines, words);
        System.out.println("NBT In Progress");
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
    
    public CredentialsForNBTBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForNBTBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForNBTBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForNBTBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForNBTBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForNBTBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForNBTBank getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForNBTBank debits) {
        this.debits = debits;
    }
   
    public KeywordsForNBTBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForNBTBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
