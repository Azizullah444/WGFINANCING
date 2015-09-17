/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bbvacompass;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class BBVACompass extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForBBVACompass credentials;
    private DailyLedgerBalanceForBBVACompass dailyLedgerBalance;
    private DepositsForBBVACompass deposits;
    private DebitsForBBVACompass debits;
    private KeywordsForBBVACompass keywordsInput;
    
    public BBVACompass(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForBBVACompass(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForBBVACompass(lines);
        this.deposits = new DepositsForBBVACompass(lines);
        this.debits = new DebitsForBBVACompass(lines);
        this.keywordsInput = new KeywordsForBBVACompass(lines, words);
        System.out.println("BBVACompass In Progress");
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
    
    public CredentialsForBBVACompass getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForBBVACompass credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForBBVACompass getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForBBVACompass dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForBBVACompass getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForBBVACompass deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForBBVACompass getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForBBVACompass debits) {
        this.debits = debits;
    }
   
    public KeywordsForBBVACompass getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForBBVACompass keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
