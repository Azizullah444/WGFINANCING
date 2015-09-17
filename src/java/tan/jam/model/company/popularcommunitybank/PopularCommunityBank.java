/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.popularcommunitybank;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class PopularCommunityBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForPopularCommunityBank credentials;
    private DailyLedgerBalanceForPopularCommunityBank dailyLedgerBalance;
    private DepositsForPopularCommunityBank deposits;
    private DebitsForPopularCommunityBank debits;
    private KeywordsForPopularCommunityBank keywordsInput;
    
    public PopularCommunityBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForPopularCommunityBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForPopularCommunityBank(lines);
        this.deposits = new DepositsForPopularCommunityBank(lines);
        this.debits = new DebitsForPopularCommunityBank(lines);
        this.keywordsInput = new KeywordsForPopularCommunityBank(lines, words);
        System.out.println("Popular Community Bank In Progress");
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
    
    public CredentialsForPopularCommunityBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForPopularCommunityBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForPopularCommunityBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForPopularCommunityBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForPopularCommunityBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForPopularCommunityBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForPopularCommunityBank getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForPopularCommunityBank debits) {
        this.debits = debits;
    }
   
    public KeywordsForPopularCommunityBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForPopularCommunityBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
