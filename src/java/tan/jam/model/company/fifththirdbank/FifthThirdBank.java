/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.fifththirdbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class FifthThirdBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForFifthThirdBank dailyLedgerBalance;
    private CredentialsForFifthThirdBank credentials;
    private DepositsForFifthThirdBank deposits;
    private DebitsForFifthThirdBank debits;
    private KeywordsForFifthThirdBank keywordsInput;

    public FifthThirdBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForFifthThirdBank(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForFifthThirdBank(lines);
        this.debits = new DebitsForFifthThirdBank(lines);
        this.deposits = new DepositsForFifthThirdBank(lines);
        this.keywordsInput = new KeywordsForFifthThirdBank(lines, words);
        System.out.println("Fifth Third Bank in progress");
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

    public CredentialsForFifthThirdBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForFifthThirdBank credentials) {
        this.credentials = credentials;
    }

     public DailyLedgerBalanceForFifthThirdBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForFifthThirdBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForFifthThirdBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForFifthThirdBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForFifthThirdBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForFifthThirdBank debits) {
        this.debits = debits;
    }
    
    public KeywordsForFifthThirdBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForFifthThirdBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
