/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.unionbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class UnionBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForUnionBank dailyLedgerBalance;
    private CredentialsForUnionBank credentials;
    private DepositsForUnionBank deposits;
    private DebitsForUnionBank debits;
    private double averageLedgerBalance;
    private KeywordsForUnionBank keywordsInput;

    public UnionBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForUnionBank(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForUnionBank(lines);
        this.debits = new DebitsForUnionBank(lines);
        this.deposits = new DepositsForUnionBank(lines);
        this.keywordsInput = new KeywordsForUnionBank(lines, words);
        System.out.println("Union Bank In Progress");
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
        setAverageLedgerBalance(this.credentials.getAverageLedgerBalance());
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
    public DailyLedgerBalanceForUnionBank getDailyLedgerBalance() {
     return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForUnionBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public CredentialsForUnionBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForUnionBank credentials) {
        this.credentials = credentials;
    }

    public DepositsForUnionBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForUnionBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForUnionBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForUnionBank debits) {
        this.debits = debits;
    }
    public KeywordsForUnionBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForUnionBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
