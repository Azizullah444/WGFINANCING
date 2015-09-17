/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.astoriabank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class AstoriaBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForAstoriaBank dailyLedgerBalance;
    private CredentialsForAstoriaBank credentials;
    private DepositsForAstoriaBank deposits;
    private DebitsForAstoriaBank debits;
    private double averageLedgerBalance;
    private KeywordsForAstoriaBank keywordsInput;

    public AstoriaBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForAstoriaBank(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForAstoriaBank(lines);
        this.debits = new DebitsForAstoriaBank(lines);
        this.deposits = new DepositsForAstoriaBank(lines);
        this.keywordsInput = new KeywordsForAstoriaBank(lines, words);
        System.out.println("AstoriaBank In Progress");
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

    public CredentialsForAstoriaBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForAstoriaBank credentials) {
        this.credentials = credentials;
    }

     public DailyLedgerBalanceForAstoriaBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForAstoriaBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForAstoriaBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForAstoriaBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForAstoriaBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForAstoriaBank debits) {
        this.debits = debits;
    }
    
    public KeywordsForAstoriaBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForAstoriaBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
