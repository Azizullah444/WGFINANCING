/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.americaneagle;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class AmericanEagle extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForAmericanEagle credentials;
    private DailyLedgerBalanceForAmericanEagle dailyLedgerBalance;
    private DepositsForAmericanEagle deposits;
    private DebitsForAmericanEagle debits;
    private KeywordsForAmericanEagle keywordsInput;
    
    public AmericanEagle(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForAmericanEagle(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForAmericanEagle(lines);
        this.deposits = new DepositsForAmericanEagle(lines);
        this.debits = new DebitsForAmericanEagle(lines);
        this.keywordsInput = new KeywordsForAmericanEagle(lines, words);
        System.out.println("American Eagle In Progress");
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
    
    public CredentialsForAmericanEagle getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForAmericanEagle credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForAmericanEagle getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForAmericanEagle dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForAmericanEagle getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForAmericanEagle deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForAmericanEagle getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForAmericanEagle debits) {
        this.debits = debits;
    }
   
    public KeywordsForAmericanEagle getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForAmericanEagle keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
