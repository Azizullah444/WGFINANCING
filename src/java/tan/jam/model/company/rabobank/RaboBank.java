/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.rabobank;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;


public class RaboBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private CredentialsForRaboBank credentials;
    private DepositsForRaboBank deposits;
    private DebitsForRaboBank debits;
    private DailyLedgerBalanceForRaboBank dailyLedgerBalance;
    private KeywordsForRaboBank keywordsInput;

    public RaboBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForRaboBank(pages, lines);
        this.deposits = new DepositsForRaboBank(lines);
        this.debits = new DebitsForRaboBank(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForRaboBank(lines);
        this.keywordsInput = new KeywordsForRaboBank(lines, words);

        System.out.println("RaboBank In Progess");
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

    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFrom().getSum());
        setSumOfReversalFrom(this.deposits.getOnlineTLRFrom().getSum());
        
    }

    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        setAverageLedgerBalance(this.dailyLedgerBalance.getAverageLedgerBalance());
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

    public CredentialsForRaboBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForRaboBank credentials) {
        this.credentials = credentials;
    }

    public DailyLedgerBalanceForRaboBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForRaboBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DepositsForRaboBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForRaboBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForRaboBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForRaboBank debits) {
        this.debits = debits;
    }

    public KeywordsForRaboBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForRaboBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
