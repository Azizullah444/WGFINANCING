/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.blackhills;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class BlackHills extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForBlackHills credentials;
    private DailyLedgerBalanceForBlackHills dailyLedgerBalance;
    private DepositsForBlackHills deposits;
    private DebitsForBlackHills debits;
    private KeywordsForBlackHills keywordsInput;
    
    public BlackHills(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForBlackHills(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForBlackHills(lines);
        this.deposits = new DepositsForBlackHills(lines);
        this.debits = new DebitsForBlackHills(lines);
        this.keywordsInput = new KeywordsForBlackHills(lines, words);
        System.out.println("BlackHills In Progress");
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
    }
    
    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        setAverageLedgerBalance(this.dailyLedgerBalance.getAverageLedgerBalance());
        
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
    
    public CredentialsForBlackHills getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForBlackHills credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForBlackHills getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForBlackHills dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForBlackHills getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForBlackHills deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForBlackHills getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForBlackHills debits) {
        this.debits = debits;
    }
   
    public KeywordsForBlackHills getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForBlackHills keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
   
    
}
