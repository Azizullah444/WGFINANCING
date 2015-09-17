/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.mechanicsbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class MechanicsBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private DailyLedgerBalanceForMechanicsBank dailyLedgerBalance;
    private CredentialsForMechanicsBank credentials;
    private DepositsForMechanicsBank deposits;
    private DebitsForMechanicsBank debits;
    private double averageLedgerBalance;
    private KeywordsForMechanicsBank keywordsInput;

    public MechanicsBank(String[] pages, List<String> words) {
      //  System.out.println("Aziz Ullah Khan");
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForMechanicsBank(pages);
        this.dailyLedgerBalance = new DailyLedgerBalanceForMechanicsBank(lines);
       this.debits = new DebitsForMechanicsBank(lines);
       this.deposits = new DepositsForMechanicsBank(lines);
       this.keywordsInput = new KeywordsForMechanicsBank(lines, words);
        System.out.println("Mechanics Bank In Progress");
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

    public DailyLedgerBalanceForMechanicsBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForMechanicsBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public CredentialsForMechanicsBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForMechanicsBank credentials) {
        this.credentials = credentials;
    }

    public DepositsForMechanicsBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForMechanicsBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForMechanicsBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForMechanicsBank debits) {
        this.debits = debits;
    }
    public KeywordsForMechanicsBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForMechanicsBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
