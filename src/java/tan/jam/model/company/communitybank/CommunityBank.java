/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.communitybank;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class CommunityBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForCommunityBank credentials;
    private DailyLedgerBalanceForCommunityBank dailyLedgerBalance;
    private DepositsForCommunityBank deposits;
    private DebitsForCommunityBank debits;
    private KeywordsForCommunityBank keywordsInput;
    
    public CommunityBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForCommunityBank(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForCommunityBank(lines);
        this.deposits = new DepositsForCommunityBank(lines);
        this.debits = new DebitsForCommunityBank(lines);
        this.keywordsInput = new KeywordsForCommunityBank(lines, words);
        System.out.println("CommunityBank In Progress");
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
    
    public CredentialsForCommunityBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForCommunityBank credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForCommunityBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForCommunityBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForCommunityBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForCommunityBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForCommunityBank getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForCommunityBank debits) {
        this.debits = debits;
    }
   
    public KeywordsForCommunityBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForCommunityBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
