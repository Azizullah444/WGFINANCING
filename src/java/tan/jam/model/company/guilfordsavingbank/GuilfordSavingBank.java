/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.guilfordsavingbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class GuilfordSavingBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForGuilfordSavingBank dailyLedgerBalance;
    private CredentialsForGuilfordSavingBank credentials;
    private DepositsForGuilfordSavingBank deposits;
    private DebitsForGuilfordSavingBank debits;
    private double averageLedgerBalance;
    private KeywordsForGuilfordSavingBank keywordsInput;

    public GuilfordSavingBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForGuilfordSavingBank(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForGuilfordSavingBank(lines);
        this.debits = new DebitsForGuilfordSavingBank(lines);
        this.deposits = new DepositsForGuilfordSavingBank(lines);
        this.keywordsInput = new KeywordsForGuilfordSavingBank(lines, words);
        System.out.println("Guilford Saving Bank In progress");
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

    public DailyLedgerBalanceForGuilfordSavingBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForGuilfordSavingBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public CredentialsForGuilfordSavingBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForGuilfordSavingBank credentials) {
        this.credentials = credentials;
    }

    public DepositsForGuilfordSavingBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForGuilfordSavingBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForGuilfordSavingBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForGuilfordSavingBank debits) {
        this.debits = debits;
    }
    public KeywordsForGuilfordSavingBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForGuilfordSavingBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
