/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bmoharrisbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class BMOHarrisBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForBMOHarrisBank dailyLedgerBalance;
    private CredentialsForBMOHarrisBank credentials;
    private DepositsForBMOHarrisBank deposits;
    private DebitsForBMOHarrisBank debits;
    private double averageLedgerBalance;
    private KeywordsForBMOHarrisBank keywordsInput;

    public BMOHarrisBank(String[] pages, List<String> words) {
      //  System.out.println("Aziz Ullah Khan");
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForBMOHarrisBank(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForBMOHarrisBank(lines);
        this.debits = new DebitsForBMOHarrisBank(lines);
        this.deposits = new DepositsForBMOHarrisBank(lines);
        this.keywordsInput = new KeywordsForBMOHarrisBank(lines, words);
        System.out.println("BMO Harris Bank In Progress");
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

    public DailyLedgerBalanceForBMOHarrisBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForBMOHarrisBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public CredentialsForBMOHarrisBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForBMOHarrisBank credentials) {
        this.credentials = credentials;
    }

    public DepositsForBMOHarrisBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForBMOHarrisBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForBMOHarrisBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForBMOHarrisBank debits) {
        this.debits = debits;
    }
    public KeywordsForBMOHarrisBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForBMOHarrisBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
