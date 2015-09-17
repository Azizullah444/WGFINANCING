/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstbankandtrust;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class FirstBankAndTrust extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForFirstBankAndTrust dailyLedgerBalance;
    private CredentialsForFirstBankAndTrust credentials;
    private DepositsForFirstBankAndTrust deposits;
    private DebitsForFirstBankAndTrust debits;
    private KeywordsForFirstBankAndTrust keywordsInput;

    public FirstBankAndTrust(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForFirstBankAndTrust(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForFirstBankAndTrust(lines);
        this.debits = new DebitsForFirstBankAndTrust(lines);
        this.deposits = new DepositsForFirstBankAndTrust(lines);
        this.keywordsInput = new KeywordsForFirstBankAndTrust(lines, words);
        System.out.println("FirstBankAndTrust In Progress");
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

    public CredentialsForFirstBankAndTrust getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForFirstBankAndTrust credentials) {
        this.credentials = credentials;
    }

     public DailyLedgerBalanceForFirstBankAndTrust getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForFirstBankAndTrust dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForFirstBankAndTrust getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForFirstBankAndTrust deposits) {
        this.deposits = deposits;
    }

    public DebitsForFirstBankAndTrust getDebits() {
        return debits;
    }

    public void setDebits(DebitsForFirstBankAndTrust debits) {
        this.debits = debits;
    }
    
    public KeywordsForFirstBankAndTrust getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForFirstBankAndTrust keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
