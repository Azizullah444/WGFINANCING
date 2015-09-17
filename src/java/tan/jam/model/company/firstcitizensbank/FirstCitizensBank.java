/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstcitizensbank;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;


public class FirstCitizensBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private CredentialsForFirstCitizensBank credentials;
    private DepositsFirstCitizensBank deposits;
    private DebitsForFirstCitizensBank debits;
    private DailyLedgerBalanceForFirstCitizensBank dailyLedgerBalance;
    private KeywordsForFirstCitizensBank keywordsInput;

    public FirstCitizensBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForFirstCitizensBank(pages, lines);
        this.deposits = new DepositsFirstCitizensBank(lines);
        this.debits = new DebitsForFirstCitizensBank(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForFirstCitizensBank(lines);
        this.keywordsInput = new KeywordsForFirstCitizensBank(lines, words);

        System.out.println("First Citizens Bank In Progess");
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

    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFrom().getSum());
        setSumOfReversalFrom(this.deposits.getOnlineTLRFrom().getSum());

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

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public CredentialsForFirstCitizensBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForFirstCitizensBank credentials) {
        this.credentials = credentials;
    }

    public DailyLedgerBalanceForFirstCitizensBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForFirstCitizensBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DepositsFirstCitizensBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsFirstCitizensBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForFirstCitizensBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForFirstCitizensBank debits) {
        this.debits = debits;
    }

    public KeywordsForFirstCitizensBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForFirstCitizensBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
