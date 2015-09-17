/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.capitalonebank;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class CapitalOneBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForCapitalOneBank credentials;
    private DailyLedgerBalanceForCapitalOneBank dailyLedgerBalance;
    private DepositsForCapitalOneBank deposits;
    private DebitsForCapitalOneBank debits;
    private KeywordsForCapitalOneBank keywordsInput;
    
    public CapitalOneBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForCapitalOneBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForCapitalOneBank(lines);
        this.deposits = new DepositsForCapitalOneBank(lines);
        this.debits = new DebitsForCapitalOneBank(lines);
        this.keywordsInput = new KeywordsForCapitalOneBank(lines, words);
        System.out.println("CapitalOneBank In Progress");
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
        setMinValue(this.credentials.getMinValue());
        
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
    
    public CredentialsForCapitalOneBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForCapitalOneBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForCapitalOneBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForCapitalOneBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForCapitalOneBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForCapitalOneBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForCapitalOneBank getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForCapitalOneBank debits) {
        this.debits = debits;
    }
   
    public KeywordsForCapitalOneBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForCapitalOneBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
