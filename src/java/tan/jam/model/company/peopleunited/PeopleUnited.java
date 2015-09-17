/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.peopleunited;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class PeopleUnited extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForPeopleUnited credentials;
    private DailyLedgerBalanceForPeopleUnited dailyLedgerBalance;
    private DepositsForPeopleUnited deposits;
    private DebitsForPeopleUnited debits;
    private KeywordsForPeopleUnited keywordsInput;
    
    public PeopleUnited(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForPeopleUnited(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForPeopleUnited(lines);
        this.deposits = new DepositsForPeopleUnited(lines);
        this.debits = new DebitsForPeopleUnited(lines);
        this.keywordsInput = new KeywordsForPeopleUnited(lines, words);
        System.out.println("People United In Progress");
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
    
    public CredentialsForPeopleUnited getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForPeopleUnited credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForPeopleUnited getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForPeopleUnited dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForPeopleUnited getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForPeopleUnited deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForPeopleUnited getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForPeopleUnited debits) {
        this.debits = debits;
    }
   
    public KeywordsForPeopleUnited getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForPeopleUnited keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
