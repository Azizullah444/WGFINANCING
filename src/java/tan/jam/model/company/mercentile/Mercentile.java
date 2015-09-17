/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.mercentile;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class Mercentile extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForMercentile dailyLedgerBalance;
    private CredentialsForMercentile credentials;
    private DepositsForMercentile deposits;
    private DebitsForMercentile debits;
    private double averageLedgerBalance;
    private KeywordsForMercentile keywordsInput;

    public Mercentile(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForMercentile(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForMercentile(lines);
        this.debits = new DebitsForMercentile(lines);
       this.deposits = new DepositsForMercentile(lines);
       this.keywordsInput = new KeywordsForMercentile(lines, words);
        System.out.println("Mercentile Bank In Progress");
        startExecution();
    }

    private void startExecution() {
        executeCredentialsSection(); 
        executeDailyLedgerBalanceSection();
        executeDebitsSection();
        executeDepositsSection();
        executeKeywordsSection();
    }

    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
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

    private void executeDebitsSection() {
        setOnlineTransferToDate(this.debits.getOnlineTransferTo().getDate());
        setOnlineTransferToAmount(this.debits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.debits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToDescription(this.debits.getOnlineTransferTo().getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getOnlineTransferTo().getCheckNoDuplicateRemoved());

    }

    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        setAverageLedgerBalance(this.dailyLedgerBalance.getAverageLedgerBalance());
    }

    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFrom().getSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFrom().getSum());
       
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

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public DailyLedgerBalanceForMercentile getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForMercentile dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public CredentialsForMercentile getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForMercentile credentials) {
        this.credentials = credentials;
    }

    public DepositsForMercentile getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForMercentile deposits) {
        this.deposits = deposits;
    }

    public DebitsForMercentile getDebits() {
        return debits;
    }

    public void setDebits(DebitsForMercentile debits) {
        this.debits = debits;
    }
    public KeywordsForMercentile getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForMercentile keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
