/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citynationalbank;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;


public class CityNationalBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private CredentialsForBankOfCityNationalBank credentials;
    private DepositsBankOfCityNationalBank deposits;
    private DebitsForBankOfCityNationalBank debits;
    private DailyLedgerBalanceForCityNationalBank dailyLedgerBalance;
    private KeywordsForBankOfCityNationalBank keywordsInput;

    public CityNationalBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForBankOfCityNationalBank(pages, lines);
        this.deposits = new DepositsBankOfCityNationalBank(lines);
        this.debits = new DebitsForBankOfCityNationalBank(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForCityNationalBank(lines);
        this.keywordsInput = new KeywordsForBankOfCityNationalBank(lines, words);

        System.out.println("City National Bank In Progess");
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

    public CredentialsForBankOfCityNationalBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForBankOfCityNationalBank credentials) {
        this.credentials = credentials;
    }

    public DailyLedgerBalanceForCityNationalBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForCityNationalBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DepositsBankOfCityNationalBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsBankOfCityNationalBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForBankOfCityNationalBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForBankOfCityNationalBank debits) {
        this.debits = debits;
    }

    public KeywordsForBankOfCityNationalBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForBankOfCityNationalBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
