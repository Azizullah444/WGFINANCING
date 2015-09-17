/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.wellsriver;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class WellsRiver extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForWellsRiver dailyLedgerBalance;
    private CredentialsForWellsRiver credentials;
    private DepositsForWellsRiver deposits;
    private DebitsForWellsRiver debits;
    private double averageLedgerBalance;
    private KeywordsForWellsRiver keywordsInput;

    public WellsRiver(String[] pages, List<String> words) {
      //  System.out.println("Aziz Ullah Khan");
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForWellsRiver(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForWellsRiver(lines);
       this.debits = new DebitsForWellsRiver(lines);
       this.deposits = new DepositsForWellsRiver(lines);
       this.keywordsInput = new KeywordsForWellsRiver(lines, words);
        System.out.println("Wells River In Progress");
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

    public DailyLedgerBalanceForWellsRiver getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForWellsRiver dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public CredentialsForWellsRiver getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForWellsRiver credentials) {
        this.credentials = credentials;
    }

    public DepositsForWellsRiver getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForWellsRiver deposits) {
        this.deposits = deposits;
    }

    public DebitsForWellsRiver getDebits() {
        return debits;
    }

    public void setDebits(DebitsForWellsRiver debits) {
        this.debits = debits;
    }
    public KeywordsForWellsRiver getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForWellsRiver keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
