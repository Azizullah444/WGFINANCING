/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.mtBank;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class MTBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForMTBank credentials;
    private DailyLedgerBalanceForMTBank dailyLedgerBalance;
    private DepositsForMTBank deposits;
    private DebitsForMTBank debits;
    private KeywordsForMTBank keywordsInput;
    
    public MTBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForMTBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForMTBank(lines);
        this.deposits = new DepositsForMTBank(lines);
        this.debits = new DebitsForMTBank(lines);
        this.keywordsInput = new KeywordsForMTBank(lines, words);
        System.out.println("M&T Bank In Progress");
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
    
    public CredentialsForMTBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForMTBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForMTBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForMTBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForMTBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForMTBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForMTBank getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForMTBank debits) {
        this.debits = debits;
    }
   
    public KeywordsForMTBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForMTBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
