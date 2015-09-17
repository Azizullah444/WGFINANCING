/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstbank2;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class FirstBank2 extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForFirstBank2 dailyLedgerBalance;
    private CredentialsForFirstBank2 credentials;
    private DepositsForFirstBank2 deposits;
    private DebitsForFirstBank2 debits;
    private KeywordsForFirstBank2 keywordsInput;

    public FirstBank2(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForFirstBank2(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForFirstBank2(lines);
        this.debits = new DebitsForFirstBank2(lines);
        this.deposits = new DepositsForFirstBank2(lines);
        this.keywordsInput = new KeywordsForFirstBank2(lines, words);
        System.out.println("FirstBank2 In Progress");
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

    private void executeKeywordsSection() {
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

    public CredentialsForFirstBank2 getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForFirstBank2 credentials) {
        this.credentials = credentials;
    }

    public DailyLedgerBalanceForFirstBank2 getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForFirstBank2 dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DepositsForFirstBank2 getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForFirstBank2 deposits) {
        this.deposits = deposits;
    }

    public DebitsForFirstBank2 getDebits() {
        return debits;
    }

    public void setDebits(DebitsForFirstBank2 debits) {
        this.debits = debits;
    }

    public KeywordsForFirstBank2 getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForFirstBank2 keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
