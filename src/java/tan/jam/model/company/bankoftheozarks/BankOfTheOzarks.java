/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankoftheozarks;

import java.util.List;
import tan.jam.company.utils.FileUtils;
import tan.jam.company.Company;
/**
 *
 * @author Lenovo
 */
public class BankOfTheOzarks extends Company {
    
    private String[] pages;
    private List<String> lines;
    private CredentialsForBankOfTheOzarks credentials;
    private DailyLedgerBalanceForBankOfTheOzarks dailyLedgerBalance;
    private DepositsForBankOfTheOzarks deposits;
    private DebitsForBankOfTheOzarks debits;
    private KeywordsForBankOfTheOzarks keywordsInput;
    
    public BankOfTheOzarks(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForBankOfTheOzarks(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForBankOfTheOzarks(lines);
        this.deposits = new DepositsForBankOfTheOzarks(lines);
        this.debits = new DebitsForBankOfTheOzarks(lines);
        this.keywordsInput = new KeywordsForBankOfTheOzarks(lines, words);
        System.out.println("Bank Of Ozark In Progress");
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
    
    public CredentialsForBankOfTheOzarks getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForBankOfTheOzarks credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForBankOfTheOzarks getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForBankOfTheOzarks dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForBankOfTheOzarks getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForBankOfTheOzarks deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForBankOfTheOzarks getDebits() {
        return debits;
    }
    
    public void setDebitss(DebitsForBankOfTheOzarks debits) {
        this.debits = debits;
    }
   
    public KeywordsForBankOfTheOzarks getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForBankOfTheOzarks keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
    
}
