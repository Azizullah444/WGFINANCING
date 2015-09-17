/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.wellsfargo;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class WellsNew extends WellsFargo {
    
    private String[] pages;
    private List<String> lines;
    private List<String> keywords;
    private CredentialsForWellsNew credentials;
    private DailyLedgerBalanceForWellsNew dailyLedgerBalance;
    private DebitsForWellsNew debits;
    private DepositsForWellsNew deposits;
    private KeywordsForWellsNew keywordsInput;
    
    public WellsNew(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        this.keywords = words;
        this.credentials = new CredentialsForWellsNew(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForWellsNew(lines);
        this.debits = new DebitsForWellsNew(pages,lines,words);
        this.deposits = new DepositsForWellsNew(pages);
        this.keywordsInput = new KeywordsForWellsNew(lines, words);
        System.out.println("WellsNew In Progress");
        startExecution();
    }
    
    private void startExecution() {
        executeCredentialsSection();
        executeDebitsSection();
        executeDailyLedgerBalanceSection();
        executeDepositsSection();
        executeKeywordsSection();
    }
    private void executeKeywordsSection(){
        setKeywordSum(this.keywordsInput.getSumForKeywords());
        setKeywordAmount(this.keywordsInput.getKeywordAmount());
        setKeywordDate(this.keywordsInput.getKeywordDate());
        setKeywordDescription(this.keywordsInput.getKeywordDescription());
        setDailypaymentKeywordDate(this.keywordsInput.getDailypaymentKeywordDate());
        setDailypaymentKeywordAmount(this.keywordsInput.getDailypaymentKeywordAmount());
        setDailypaymentKeywordDescription(this.keywordsInput.getDailypaymentKeywordDescription());
    }
    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
        setAverageLedgerBalance(this.credentials.getAverageLedgerBalance());
    }
    
    private void executeDebitsSection() {
        setOnlineTransferToDate(this.debits.getOnlineTransferTo().getDate());
        setOnlineTransferToDescription(this.debits.getOnlineTransferTo().getDescription());
        setOnlineTransferToAmount(this.debits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.debits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getOnlineTransferTo().getCheckNoDuplicateRemoved());
    }
    
    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFromSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFromSum());
    }
    
    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
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
    
    public List<String> getKeywords() {
        return keywords;
    }
    
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    
    public CredentialsForWellsNew getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForWellsNew credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForWellsNew getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForWellsNew dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DebitsForWellsNew getDebits() {
        return debits;
    }

    public void setDebits(DebitsForWellsNew debits) {
        this.debits = debits;
    }

    public DepositsForWellsNew getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForWellsNew deposits) {
        this.deposits = deposits;
    }

    public KeywordsForWellsNew getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForWellsNew keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
}
