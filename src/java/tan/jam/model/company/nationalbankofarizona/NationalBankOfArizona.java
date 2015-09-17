/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.nationalbankofarizona;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class NationalBankOfArizona extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForNationalBankOfArizona dailyLedgerBalance;
    private CredentialsForNationalBankOfArizona credentials;
    private DepositsForNationalBankOfArizona deposits;
    private DebitsForNationalBankOfArizona debits;
    private KeywordsForNationalBankOfArizona keywordsInput;

    public NationalBankOfArizona(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForNationalBankOfArizona(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForNationalBankOfArizona(lines);
        this.debits = new DebitsForNationalBankOfArizona(lines);
        this.deposits = new DepositsForNationalBankOfArizona(lines);
        this.keywordsInput = new KeywordsForNationalBankOfArizona(lines, words);
        System.out.println("NationalBankOfArizona In Progress");
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

    public CredentialsForNationalBankOfArizona getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForNationalBankOfArizona credentials) {
        this.credentials = credentials;
    }

     public DailyLedgerBalanceForNationalBankOfArizona getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForNationalBankOfArizona dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForNationalBankOfArizona getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForNationalBankOfArizona deposits) {
        this.deposits = deposits;
    }

    public DebitsForNationalBankOfArizona getDebits() {
        return debits;
    }

    public void setDebits(DebitsForNationalBankOfArizona debits) {
        this.debits = debits;
    }
    
    public KeywordsForNationalBankOfArizona getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForNationalBankOfArizona keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
